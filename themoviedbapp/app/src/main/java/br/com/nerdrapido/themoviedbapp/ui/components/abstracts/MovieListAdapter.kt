package br.com.nerdrapido.themoviedbapp.ui.components.abstracts

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist.HorizontalMovieViewHolder
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.MovieDetailActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
abstract class MovieListAdapter<T: MovieListViewHolder>(
    private val data: MutableList<MovieListResultObject>,
    private val context: Context
) : RecyclerView.Adapter<T>() {

    abstract fun getViewHolder(itemView: View) : T

    abstract fun getViewHolderLayoutId() : Int

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): T {
        val itemView: View = LayoutInflater
            .from(context)
            .inflate(
                getViewHolderLayoutId(),
                parent,
                false
            )
        return getViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: T, position: Int) {
        var requestOptions = RequestOptions()
//        requestOptions = requestOptions.placeholder(R.drawable.poster_progress)

        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + data[position].posterPath)
            .apply(requestOptions).into(holder.poster)

        holder.itemView.setOnClickListener {
            val newIntent = Intent(context, MovieDetailActivity::class.java)
            newIntent.putExtra(
                MovieDetailActivity.MOVIE_OBJECT_RESULT,
                Gson().toJson(data[position])
            )
            ContextCompat.startActivity(context, newIntent, null)
        }
    }
}