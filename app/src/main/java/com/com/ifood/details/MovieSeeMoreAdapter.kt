package com.com.ifood.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.com.ifood.R
import com.com.ifood.repository.model.Movie

class MovieSeeMoreAdapter(private val listMovies: List<Movie>) :
    RecyclerView.Adapter<MovieSeeMoreViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSeeMoreViewHolder {
        return MovieSeeMoreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_with_space, parent, false)
        )
    }

    override fun getItemCount() = listMovies.size

    override fun onBindViewHolder(holder: MovieSeeMoreViewHolder, position: Int) {
        holder.bindItem(listMovies[position])
    }
}
