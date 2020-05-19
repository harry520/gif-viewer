package com.example.gifviewer.view

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.gifviewer.R
import com.tenor.android.core.constant.MediaCollectionFormats
import com.tenor.android.core.model.impl.Result


class GifsAdapter(private val gifObjects: ArrayList<Result>) :
    RecyclerView.Adapter<GifsAdapter.GifsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GifsViewHolder {
        return GifsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.gif_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return gifObjects.size
    }

    override fun onBindViewHolder(holder: GifsViewHolder, position: Int) {
        val gifUrl = gifObjects[position].medias[0].get(MediaCollectionFormats.GIF_TINY).url
        Glide.with(holder.itemView.context)
            .load(gifUrl)
            .placeholder(R.drawable.transparent)
            .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
            .into((holder.gif))
        holder.gif.setOnClickListener {
            val intent = Intent(holder.itemView.context, FullScreenGif::class.java)
            intent.putExtra("gif_url", gifUrl)
            holder.itemView.context.startActivity(intent)
        }
    }

    class GifsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val gif: ImageView = itemView.findViewById(R.id.gif)
    }

}