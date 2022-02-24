package com.globo.moviesapp.ui.movie.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.globo.moviesapp.R
import com.globo.moviesapp.model.movie.Movie
import com.globo.moviesapp.ui.movie.view.MovieDetailsActivity

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movies = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_movie, parent, false)
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]

        holder.setIsRecyclable(false)

        val requestOptions = RequestOptions().placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        Glide.with(holder.itemView.context).setDefaultRequestOptions(requestOptions)
            .load("${MovieDetailsActivity.URL_IMAGE}${movie.poster_path}").into(holder.ivPhotoMovie)

        holder.clMovie.setOnClickListener{
            MovieDetailsActivity.newInstance(holder.itemView.context, movie)
        }
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateList(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val clMovie: View = itemView.findViewById(R.id.clMovie)
        val ivPhotoMovie: ImageView = itemView.findViewById(R.id.ivPhotoMovie)
    }
}