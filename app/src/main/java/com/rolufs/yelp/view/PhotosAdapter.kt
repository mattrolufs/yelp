package com.rolufs.yelp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rolufs.yelp.R
import kotlinx.android.synthetic.main.photos_item.view.*

class PhotosAdapter(val urls : List<String>) : RecyclerView.Adapter<PhotosAdapter.PhotoHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.photos_item, parent, false)
        return PhotoHolder(view)
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        val url = urls.get(position)
        holder.setData(url)
    }

    inner class PhotoHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun setData(url : String?) {

            Glide
                .with(itemView.context)
                .load(url)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(itemView.image_item_header);

        }

    }
}