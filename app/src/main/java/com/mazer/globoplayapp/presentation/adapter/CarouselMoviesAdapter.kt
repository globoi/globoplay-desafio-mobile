package com.mazer.globoplayapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.globoplayapp.databinding.ItemMovieBinding
import com.mazer.globoplayapp.domain.entities.Movie

class CarouselMoviesAdapter(private val onMovieSelected: (movie: Movie) -> Unit)  : RecyclerView.Adapter<CarouselViewHolder>() {

    private var movieList: List<Movie> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarouselViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CarouselViewHolder(binding, onMovieSelected)
    }

    override fun onBindViewHolder(holder: CarouselViewHolder, position: Int) {
        holder.bind(movieList[position])
    }

    fun setList(list: List<Movie>?) {
        this.movieList = list ?: return
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int =  movieList.size
}
