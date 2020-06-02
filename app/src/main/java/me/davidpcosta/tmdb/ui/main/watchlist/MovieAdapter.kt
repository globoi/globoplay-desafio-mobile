package me.davidpcosta.tmdb.ui.main.watchlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import me.davidpcosta.tmdb.BuildConfig
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.data.model.Movie

class MovieAdapter(private val applicationContext: Context): BaseAdapter() {

    private var inflater: LayoutInflater = LayoutInflater.from(applicationContext)
    var movies: List<Movie> = ArrayList()

    override fun getCount(): Int {
        return movies.size
    }

    override fun getItem(i: Int): Movie {
        return movies[i]
    }

    override fun getItemId(i: Int): Long {
        return movies[i].id
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
        val view: View?
        val viewHolder: ViewHolder
        val movie = getItem(position)

        if (convertView == null) {
            view = inflater.inflate(R.layout.movie_item, parent, false)
            viewHolder =
                ViewHolder(
                    view
                )
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        viewHolder.poster.contentDescription = movie.title
        Picasso.with(applicationContext)
            .load(BuildConfig.TMDB_IMAGE_URL + movie.posterPath)
            .placeholder(R.drawable.movie_poster_placeholder)
            .into(viewHolder.poster)

        return view
    }

    private class ViewHolder(view: View?) {
        val poster: ImageView = view?.findViewById(R.id.poster) as ImageView
    }
}

