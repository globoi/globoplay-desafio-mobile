package com.com.globo.details

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.com.globo.R
import com.com.globo.repository.model.Movie

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
