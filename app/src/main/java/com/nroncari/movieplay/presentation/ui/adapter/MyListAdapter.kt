package com.nroncari.movieplay.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nroncari.movieplay.R
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import com.nroncari.movieplay.utils.loadWallpaper

class MyListAdapter(
    private val movies: List<MovieDetailPresentation>,
    var onItemClickListener: (movieId: Long) -> Unit = {}
) : RecyclerView.Adapter<MyListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_my_list, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private lateinit var movie: MovieDetailPresentation

        private val movieWallPaper: ImageView by lazy { itemView.findViewById(R.id.item_my_list_thumbnail) }
        private val movieTitle: TextView by lazy { itemView.findViewById(R.id.text_my_list_original_title) }
        private val movieYear: TextView by lazy { itemView.findViewById(R.id.text_my_list_year) }
        private val ratingBar: RatingBar by lazy { itemView.findViewById(R.id.fragment_movie_my_list_detail_rating_bar) }
        private val ratingNum: TextView by lazy { itemView.findViewById(R.id.fragment_movie_detail_my_list_rating_numeric) }

        fun bind(movie: MovieDetailPresentation) {
            this.movie = movie
            movieWallPaper.loadWallpaper(movie.posterPath)
            movieTitle.text = movie.title
            movieYear.text = movie.releaseDate
            ratingBar.rating = movie.average / 2
            ratingNum.text = (movie.average / 2).toString()
        }

        init {
            itemView.setOnClickListener {
                if (::movie.isInitialized)
                    onItemClickListener(movie.id)
            }
        }
    }
}