package br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject
import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class HorizontalMovieAdapter(
    private val data: MutableList<MovieListResultObject>,
    private val context: Context
) : RecyclerView.Adapter<HorizontalMovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalMovieViewHolder {
        context
        val itemView: View = LayoutInflater
            .from(context)
            .inflate(
                R.layout.home_card_view,
                parent,
                false
            )
        return HorizontalMovieViewHolder(
            itemView
        )
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: HorizontalMovieViewHolder, position: Int) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.placeholder(R.drawable.poster_progress)

        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + data[position].posterPath)
            .apply(requestOptions).into(holder.poster)
    }
}