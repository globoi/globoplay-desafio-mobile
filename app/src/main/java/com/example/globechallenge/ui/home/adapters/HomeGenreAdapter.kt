package com.example.globechallenge.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globechallenge.data.model.home.Movie
import com.example.globechallenge.data.model.MovieToGenre
import com.example.globechallenge.databinding.RvHomeListGenreBinding
import com.example.globechallenge.ui.details.activities.MovieDetailsActivity

class HomeGenreAdapter : RecyclerView.Adapter<HomeGenreAdapter.MyViewHolderGenre>() {

    private val movieToGenre = ArrayList<MovieToGenre>()

    fun addMovieToGenre(list: List<MovieToGenre>) {
        movieToGenre.clear()
        movieToGenre.addAll(list)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderGenre {
        return MyViewHolderGenre(
            RvHomeListGenreBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holderGenre: MyViewHolderGenre, position: Int) {
        val movie = movieToGenre[position]
        with(holderGenre.binding) {
            titleGenre.text = movie.name
            setListMovieToGenre(rvMovie, movie.listMovie)
        }
    }

    override fun getItemCount(): Int = movieToGenre.size

    class MyViewHolderGenre(val binding: RvHomeListGenreBinding) :
        RecyclerView.ViewHolder(binding.root)

    private fun setListMovieToGenre(recyclerView: RecyclerView, list: List<Movie>) {
        val itemRecyclerView = HomeMovieAdapter(list) {
            val intent = MovieDetailsActivity.getIntentMovieDetail(recyclerView.context)
            intent.putExtra(MovieDetailsActivity.EXTRA_ID, it.id)
            ContextCompat.startActivity(recyclerView.context, intent, null)
        }
        recyclerView.layoutManager =
            LinearLayoutManager(recyclerView.context, RecyclerView.HORIZONTAL, false)
        recyclerView.adapter = itemRecyclerView
    }
}