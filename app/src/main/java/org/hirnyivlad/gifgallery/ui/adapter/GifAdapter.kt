package org.hirnyivlad.gifgallery.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.hirnyivlad.gifgallery.R
import org.hirnyivlad.gifgallery.data.domain.Gif

class GifAdapter(
    private val context: Context,
    diffCallback: DiffUtil.ItemCallback<Gif>,
    private var onItemClick: (gif: Gif) -> Unit
) : PagingDataAdapter<Gif, GifAdapter.GifViewHolder>(diffCallback) {


    override fun onBindViewHolder(holder: GifViewHolder, position: Int) {
        holder.bind(item = getItem(position)!!, context)
        holder.ivGif.setOnClickListener {
            onItemClick(getItem(position)!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifViewHolder {
        return GifViewHolder.getInstance(parent)
    }

    class GifViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        companion object {
            fun getInstance(parent: ViewGroup): GifViewHolder {
                val inflater = LayoutInflater.from(parent.context)
                val view = inflater.inflate(R.layout.gif_item, parent, false)
                return GifViewHolder(view)
            }
        }

        var ivGif: ImageView = view.findViewById(R.id.gifItem)

        fun bind(item: Gif, context: Context) {
            Glide.with(context).asGif().load(item.gifUrl).into(ivGif)
        }
    }
}