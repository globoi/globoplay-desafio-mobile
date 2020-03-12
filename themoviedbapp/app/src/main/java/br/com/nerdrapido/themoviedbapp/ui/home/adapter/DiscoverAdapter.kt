package br.com.nerdrapido.themoviedbapp.ui.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.domain.glide.GlideApp.with
import br.com.nerdrapido.themoviedbapp.domain.glide.MyGlide
import com.bumptech.glide.Glide

import com.bumptech.glide.request.RequestOptions


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class DiscoverAdapter(
    private val data: MutableList<MovieListResultObject>,
    private val context: Context
) : RecyclerView.Adapter<DiscoverViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiscoverViewHolder {
        context
        val itemView: View = LayoutInflater
            .from(context)
            .inflate(
                R.layout.home_card_view,
                parent,
                false
            )
        return DiscoverViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.count()
    }

    override fun onBindViewHolder(holder: DiscoverViewHolder, position: Int) {
        holder.textView.text = data[position].title

        var requestOptions = RequestOptions()
        requestOptions = requestOptions.placeholder(R.drawable.ic_home_black_24dp)

        Glide.with(context).load("https://image.tmdb.org/t/p/w500" + data[position].posterPath)
            .apply(requestOptions).into(holder.poster)
    }
}