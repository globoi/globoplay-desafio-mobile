package com.reisdeveloper.globoplay.ui.features.mylist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BASE_IMAGE_URL
import com.reisdeveloper.globoplay.databinding.ItemMyListBinding
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel

class MyListAdapter(
    private val listener: Listener
) : RecyclerView.Adapter<MyListAdapter.ViewHolder>() {

    private val favoriteMovies = mutableListOf<MovieUiModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMyListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false,
            )
        )
    }

    override fun getItemCount(): Int = favoriteMovies.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favoriteMovies[position])

        holder.itemView.setOnClickListener {
            listener.onItemClick(favoriteMovies[position])
        }
    }

    fun setItems(list: List<MovieUiModel>) {
        val lastIndex = favoriteMovies.lastIndex
        notifyItemRangeRemoved(0, favoriteMovies.size)

        favoriteMovies.clear()
        favoriteMovies.addAll(list)

        notifyItemRangeInserted(lastIndex, list.size)
    }

    interface Listener {
        fun onItemClick(movie: MovieUiModel)
    }

    class ViewHolder(
        private val binding: ItemMyListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieUiModel) {
            Glide.with(binding.root.context)
                .load("${BASE_IMAGE_URL}${item.posterPath}")
                .placeholder(R.drawable.bg_holder)
                .error(R.drawable.bg_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(binding.imgItemMyList)
        }
    }
}