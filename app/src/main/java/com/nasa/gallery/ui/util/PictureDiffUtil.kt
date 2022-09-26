package com.nasa.gallery.ui.util

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.nasa.gallery.model.NasaItem

/**
 *
 * [DiffUtil] class for [ListAdapter]s
 */

class PictureDiffUtil : DiffUtil.ItemCallback<NasaItem>() {

    override fun areItemsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
        return oldItem.explanation == newItem.explanation
    }


    override fun areContentsTheSame(oldItem: NasaItem, newItem: NasaItem): Boolean {
        return oldItem == newItem
    }

}