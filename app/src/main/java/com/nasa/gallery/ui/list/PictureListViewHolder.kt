package com.nasa.gallery.ui.list

import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.like.LikeButton
import com.like.OnLikeListener
import com.nasa.gallery.databinding.ItemPictureBinding
import com.nasa.gallery.model.NasaItem
import com.nasa.gallery.util.loadImage

class PictureListViewHolder(
    private val binding: ItemPictureBinding,
    private val imageLoader: ImageLoader,
    private val click: (position: Int) -> Unit,
    private val like: (NasaItem) -> Unit,
    private val unlike: (NasaItem) -> Unit,
    private val lb: (LikeButton, NasaItem) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: NasaItem) = with(binding) {
        photoTitle.text = item.title
        photoThumbnail.loadImage(imageLoader, item.url)
        root.setOnClickListener {
            click(adapterPosition)
        }
        lb(bookmarkIcon, item)
        bookmarkIcon.setOnLikeListener(object : OnLikeListener {
            override fun liked(likeButton: LikeButton?) {
                like(item)
            }

            override fun unLiked(likeButton: LikeButton?) {
                unlike(item)
            }
        })
    }
}