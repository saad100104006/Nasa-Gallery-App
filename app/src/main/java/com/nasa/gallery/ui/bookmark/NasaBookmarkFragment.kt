package com.nasa.gallery.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import coil.ImageLoader
import com.nasa.gallery.R
import com.nasa.gallery.databinding.FragmentBookmarkBinding
import com.nasa.gallery.ui.list.PictureListAdapter
import com.nasa.gallery.util.EventObserver
import com.nasa.gallery.util.Origin
import com.nasa.gallery.util.Result
import com.nasa.gallery.util.showMessage
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class NasaBookmarkFragment : Fragment() {

    private var _binding: FragmentBookmarkBinding? = null
    private val binding get() = _binding!!
    private val viewModel: BookmarkViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nasaAdapter = PictureListAdapter(
            imageLoader,
            { position ->
                val directions =
                    NasaBookmarkFragmentDirections.actionBookmarkFragmentToPictureDetailsFragment(
                        position,
                        Origin.BOOKMARK_FRAGMENT
                    )
                findNavController().navigate(directions)
            },
            { likedNasaItem ->
                viewModel.addBookmark(likedNasaItem)
            },
            { unlikedNasaItem ->
                viewModel.removeBookmark(unlikedNasaItem)
            }, { likeButton, nasaItem ->
                viewModel.isBookmark(nasaItem).observe(viewLifecycleOwner) {
                    likeButton.isLiked = it
                }
            })

        viewModel.allBookmarks.observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Success -> {
                    nasaAdapter.submitList(result.data)
                    binding.emptyBox.isVisible = result.data.isNullOrEmpty()
                }
                is Result.Error -> { Timber.e(result.error) }
            }
        }
        viewModel.bookmarkMessage.observe(viewLifecycleOwner, EventObserver {
            binding.root.showMessage(it)
        })
        binding.pictureListRecyclerView.apply {
            adapter = nasaAdapter
            layoutManager = GridLayoutManager(requireContext(), resources.getInteger(R.integer.span_count))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}