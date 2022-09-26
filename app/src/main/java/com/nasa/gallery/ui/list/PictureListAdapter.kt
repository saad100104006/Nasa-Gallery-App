package com.nasa.gallery.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import coil.ImageLoader
import com.like.LikeButton
import com.nasa.gallery.R
import com.nasa.gallery.databinding.ItemPictureBinding
import com.nasa.gallery.ui.util.PictureDiffUtil
import com.nasa.gallery.model.NasaItem

class PictureListAdapter(
    private val imageLoader: ImageLoader,
    private val click: (position: Int) -> Unit,
    private val like: (NasaItem) -> Unit,
    private val unlike: (NasaItem) -> Unit,
    private val lb: (LikeButton, NasaItem) -> Unit
) :
    ListAdapter<NasaItem, PictureListViewHolder>(PictureDiffUtil()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PictureListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_picture, parent, false)
        return PictureListViewHolder(ItemPictureBinding.bind(view), imageLoader, click, like, unlike, lb)
    }

    override fun onBindViewHolder(holder: PictureListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}
