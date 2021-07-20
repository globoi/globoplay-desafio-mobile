package com.example.globechallenge.ui.details.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globechallenge.data.model.features.home.Movie
import com.example.globechallenge.databinding.RvWatchTooBinding
import com.example.globechallenge.helper.loadImage

class WatchTooAdapter(
   private val list: List<Movie>
   //private val onItemClickListener: (movie: Movie) -> Unit
) : RecyclerView.Adapter<WatchTooAdapter.MyViewHolderWatchToo>() {

    //private val list = ArrayList<MovieToGenre>()

//    fun addMovieToGenreWatchToo(list: List<MovieToGenre>) {
//        this.list.clear()
//        this.list.addAll(list)
//        notifyDataSetChanged()
//    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderWatchToo {
        return MyViewHolderWatchToo(
            RvWatchTooBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolderWatchToo, position: Int) {
        val movies = list[position]
        with(holder.binding) {
            imgMovieWacthToo.loadImage(movies.image)
//            imgMovie.setOnClickListener {
//              // onItemClickListener.invoke(movies)
//            }
        }
    }

    override fun getItemCount(): Int = list.size

    class MyViewHolderWatchToo(val binding: RvWatchTooBinding) :
        RecyclerView.ViewHolder(binding.root)
}