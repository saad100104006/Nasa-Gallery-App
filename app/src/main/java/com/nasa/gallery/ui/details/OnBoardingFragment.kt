package com.nasa.gallery.ui.details

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.nasa.gallery.databinding.FragmentOnboardingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OnBoardingFragment : DialogFragment() {

    private var _binding: FragmentOnboardingBinding? = null
    private val binding get() = _binding!!
    private val viewModel: PictureDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOnboardingBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lottieAnimationView.playAnimation()
        binding.okButton.setOnClickListener { requireDialog().dismiss() }
    }

    override fun onDismiss(dialog: DialogInterface) {
        viewModel.setUserHasSeenOnBoarding(true)

        super.onDismiss(dialog)
    }

    override fun onDestroyView() {
        super.onDestroyView()

        _binding = null
    }
}