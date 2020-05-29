package me.davidpcosta.tmdb.ui.main.watchlist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.data.model.Movie

class MovieAdapter(var applicationContext: Context): BaseAdapter() {

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
        val vh: ViewHolder
        val movie = getItem(position)

        if (convertView == null) {
            view = inflater.inflate(R.layout.movie_item, parent, false)
            vh = ViewHolder(view)
            view.tag = vh
        } else {
            view = convertView
            vh = view.tag as ViewHolder
        }

        vh.poster.contentDescription = movie.title
        Picasso.with(applicationContext)
            .load("https://image.tmdb.org/t/p/w220_and_h330_face" + movie.posterPath)
            .into(vh.poster)

        return view
    }

    private class ViewHolder(view: View?) {
        val poster: ImageView = view?.findViewById(R.id.poster) as ImageView
    }
}

