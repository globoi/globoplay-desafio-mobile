package me.davidpcosta.tmdb.ui.main.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import me.davidpcosta.tmdb.BuildConfig
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.toast
import me.davidpcosta.tmdb.ui.highlight.HighlightActivity
import me.davidpcosta.tmdb.ui.main.MainActivity

class MovieRecycleViewAdapter(
    private val applicationContext: Context
): RecyclerView.Adapter<MovieRecycleViewAdapter.ViewHolder>() {

    private var inflater: LayoutInflater = LayoutInflater.from(applicationContext)
    var movies: List<Movie> = ArrayList()


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val poster: ImageView = view.findViewById(R.id.poster)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = inflater.inflate(R.layout.movie_item, parent, false)
        return ViewHolder(
            view
        )
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movie = movies[position]
        holder.poster.contentDescription = movie.title
        Picasso.with(applicationContext)
            .load(BuildConfig.TMDB_IMAGE_URL + movie.posterPath)
            .into(holder.poster)

        holder.itemView.setOnClickListener {
            applicationContext.toast(movie.title)
            val intent = Intent(applicationContext, HighlightActivity::class.java)
            intent.putExtra("movie", movie)
            applicationContext.startActivity(intent)
        }
    }
}

