package com.com.ifood.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.com.ifood.R
import com.com.ifood.main.view.MovieViewHolder
import com.com.ifood.repository.model.Movie

class MovieMyListAdapter(private var listMovies: List<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_with_space, parent, false),
            listMovies
        )
    }

    override fun getItemCount() = listMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bindItem(listMovies[position])
    }

    fun changeList(listMovies: List<Movie>) {
        this.listMovies = listMovies
        notifyDataSetChanged()
    }
}
