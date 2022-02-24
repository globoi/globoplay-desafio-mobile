package com.globo.moviesapp.ui.genre.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.globo.moviesapp.R
import com.globo.moviesapp.model.genre.Genre
import com.globo.moviesapp.model.movie.Movie
import com.globo.moviesapp.ui.movie.adapter.MovieAdapter

class GenreAdapter(): RecyclerView.Adapter<GenreAdapter.ViewHolder>() {

    lateinit var activity: FragmentActivity
    lateinit var moviewAdapter: MovieAdapter
    private var genres = arrayListOf<Genre>()
    private var movies = arrayListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val item = LayoutInflater.from(parent.context)
            .inflate(R.layout.row_genre_movie, parent, false)
        activity = parent.context as FragmentActivity
        return ViewHolder(item)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val genre = genres[position]

        holder.setIsRecyclable(false)

        updateIsLoading(holder, true)
        holder.tvNameGenre.text = genre.name

        setupRecicleView(holder, genre)
    }

    private fun updateIsLoading(holder: ViewHolder, status: Boolean){
        if(status) {
            holder.pbGenreMovie.visibility = View.VISIBLE
            holder.rvMovies.visibility = View.GONE
        } else {
            holder.pbGenreMovie.visibility = View.GONE
            holder.rvMovies.visibility = View.VISIBLE
        }
    }

    private fun setupRecicleView(holder: ViewHolder, genre: Genre){
        moviewAdapter = MovieAdapter()
        holder.rvMovies.layoutManager = LinearLayoutManager(holder.itemView.context,
            LinearLayoutManager.HORIZONTAL, false)
        holder.rvMovies.adapter = moviewAdapter
        moviewAdapter.updateList(movies
            .filter { movie -> movie.genre_ids!!.filter { genreId -> genreId == genre.id }.isNotEmpty() })
        if(movies.size > 0) {
            updateIsLoading(holder, false)
        }
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    fun updateList(genres: List<Genre>) {
        this.genres.clear()
        this.genres.addAll(genres)
        notifyDataSetChanged()
    }

    fun updateMovieList(movies: List<Movie>) {
        this.movies.clear()
        this.movies.addAll(movies)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvNameGenre: TextView = itemView.findViewById(R.id.tvNameGenre)
        val rvMovies: RecyclerView = itemView.findViewById(R.id.rvMovies)
        val pbGenreMovie: ProgressBar = itemView.findViewById(R.id.pbGenreMovie)
    }
}