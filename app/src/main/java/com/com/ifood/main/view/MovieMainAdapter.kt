package com.com.ifood.main.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.com.ifood.R
import com.com.ifood.repository.model.Movie

internal class MovieMainAdapter :
    RecyclerView.Adapter<MovieViewHolder>() {

    private val listMovies: MutableList<Movie> = mutableListOf()

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

    fun addMovie(list: List<Movie>) {
        val initIndex = listMovies.size
        listMovies.addAll(list)
        notifyItemRangeInserted(initIndex, listMovies.size)
    }
}
