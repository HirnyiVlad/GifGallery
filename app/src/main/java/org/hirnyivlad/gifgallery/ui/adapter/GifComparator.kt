package org.hirnyivlad.gifgallery.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import org.hirnyivlad.gifgallery.data.domain.Gif

object GifComparator : DiffUtil.ItemCallback<Gif>() {
    override fun areItemsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        // Id is unique.
        return oldItem.gifId == newItem.gifId
    }

    override fun areContentsTheSame(oldItem: Gif, newItem: Gif): Boolean {
        return oldItem == newItem
    }
}