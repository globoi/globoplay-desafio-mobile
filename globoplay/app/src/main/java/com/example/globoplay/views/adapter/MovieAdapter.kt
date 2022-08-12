package com.example.globoplay.views.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globoplay.databinding.MediaItemBinding
import com.example.globoplay.domain.PopularMovie
import com.squareup.picasso.Picasso

class MovieAdapter(private val movies:List<PopularMovie>, private val listener: ClickItemMovieDetails):RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
       val itemList = MediaItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(itemList, listener )
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500" + movies[position].posterPath).into(holder.itemBinding.moviePoster)
    }

    override fun getItemCount(): Int  = movies.size

    inner class MovieViewHolder(val itemBinding: MediaItemBinding, private val listener:ClickItemMovieDetails):RecyclerView.ViewHolder(itemBinding.root){
        init {
            itemBinding.root.setOnClickListener {
                listener.onItemCLickListener(movies[position])
            }
        }
    }

 }