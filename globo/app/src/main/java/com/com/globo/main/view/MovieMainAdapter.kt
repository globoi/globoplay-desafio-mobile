package com.com.globo.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.com.globo.R
import com.com.globo.repository.model.Movie

internal class MovieMainAdapter(private val listMovies: List<Movie>) :
    RecyclerView.Adapter<MovieViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie, parent, false),
            listMovies
        )
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        holder.bindItem(listMovies[position])
    }

    override fun getItemCount(): Int {
        return listMovies.size
    }
}
