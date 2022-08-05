package com.example.globoplay.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globoplay.databinding.MediaItemBinding
import com.example.globoplay.domain.PopularMovie
import com.example.globoplay.domain.PopularTVSeries
import com.squareup.picasso.Picasso

class SerieAdapter(private val series:List<PopularTVSeries>, private val listener: ClickItemSerieDetails): RecyclerView.Adapter<SerieAdapter.SerieViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SerieViewHolder {
        val itemList = MediaItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return SerieViewHolder(itemList, listener)
    }

    override fun onBindViewHolder(holder: SerieViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + series[position].posterPath).into(holder.itemBinding.moviePoster)
    }

    override fun getItemCount(): Int  = series.size

    inner class SerieViewHolder(val itemBinding: MediaItemBinding, private val listener:ClickItemSerieDetails):RecyclerView.ViewHolder(itemBinding.root){init {
        itemBinding.root.setOnClickListener {
            listener.onItemCLickListener(series[position])
        }
    }
    }
}