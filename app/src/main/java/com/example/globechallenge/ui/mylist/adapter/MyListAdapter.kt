package com.example.globechallenge.ui.mylist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globechallenge.data.model.entities.FavoriteMoviesEntity
import com.example.globechallenge.databinding.RvMyListBinding
import com.example.globechallenge.helper.loadImage

class MyListAdapter(
        //private val onItemClickListener: (movie: Movie) -> Unit
    ) : RecyclerView.Adapter<MyListAdapter.ViewHolderMyList>() {

        private val list = ArrayList<FavoriteMoviesEntity>()

    fun addMoviesToMyList(list: List<FavoriteMoviesEntity>) {
        this.list.clear()
        this.list.addAll(list)
        notifyDataSetChanged()
    }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMyList {
            return ViewHolderMyList(
                RvMyListBinding.inflate(
                    LayoutInflater.from(parent.context), parent, false
                )
            )
        }

        override fun onBindViewHolder(holder: ViewHolderMyList, position: Int) {
            val movies = list[position]
            with(holder.binding) {
                imgMovieMyList.loadImage(movies.image)
//            imgMovie.setOnClickListener {
//              // onItemClickListener.invoke(movies)
//            }
            }
        }

        override fun getItemCount(): Int = list.size

        class ViewHolderMyList(val binding: RvMyListBinding) :
            RecyclerView.ViewHolder(binding.root)
    }