package com.example.globoplay.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.marginBottom
import androidx.core.view.marginEnd
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.globoplay.database.models.MyList
import com.example.globoplay.databinding.MediaItemBinding
import com.squareup.picasso.Picasso

class MediaAdapter(private val medias:List<MyList>, private val listener: ClickItemMediaDetails): RecyclerView.Adapter<MediaAdapter.MediaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MediaViewHolder {
        val itemList = MediaItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MediaViewHolder(itemList,listener)
    }

    override fun onBindViewHolder(holder: MediaViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + medias[position].posterImage).into(holder.itemBinding.moviePoster)
    }

    override fun getItemCount(): Int =  medias.size

    inner class MediaViewHolder(val itemBinding: MediaItemBinding, private val listener:ClickItemMediaDetails):RecyclerView.ViewHolder(itemBinding.root){
        init {
            itemBinding.root.setOnClickListener {
                listener.onItemCLickListener(medias[position])
            }
        }
    }

}