package com.nasa.gallery.ui.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import com.nasa.gallery.R
import com.nasa.gallery.databinding.ItemPictureDetailsBinding
import com.nasa.gallery.model.NasaItem
import com.nasa.gallery.ui.util.PictureDiffUtil
import com.nasa.gallery.util.loadImage
import com.nasa.gallery.util.makeGone

class DetailsPagerAdapter(
    private val imageLoader: ImageLoader
) :
    ListAdapter<NasaItem, DetailsPagerAdapter.DetailsPagerViewHolder>(PictureDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsPagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_picture_details, parent, false)
        return DetailsPagerViewHolder(ItemPictureDetailsBinding.bind(view), imageLoader)
    }

    override fun onBindViewHolder(holder: DetailsPagerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DetailsPagerViewHolder(
        private val binding: ItemPictureDetailsBinding,
        private val imageLoader: ImageLoader
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NasaItem) = with(binding) {
            titleText.text = item.title
            dateText.text = item.date
            explanationText.text = item.explanation
            detailPhoto.loadImage(imageLoader, item.url)
            copyrightText.text = item.copyright
            if (item.copyright == null) {
                copyrightSymbol.makeGone()
            }
        }
    }
}
