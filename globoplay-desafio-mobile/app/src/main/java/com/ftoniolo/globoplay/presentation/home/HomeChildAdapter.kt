package com.ftoniolo.globoplay.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.globoplay.databinding.ItemChildHomeBinding
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader

class HomeChildAdapter(
    private val homeChildList: List<HomeChildVE>,
    private val imageLoader: ImageLoader,
    private val onItemClick: (film: Film, view: View) -> Unit
) : RecyclerView.Adapter<HomeChildAdapter.HomeChildViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeChildViewHolder {
        return HomeChildViewHolder.create(parent, imageLoader, onItemClick)
    }

    override fun onBindViewHolder(holder: HomeChildViewHolder, position: Int) {
        holder.bind(homeChildList[position])
    }

    override fun getItemCount() = homeChildList.size

    class HomeChildViewHolder(
        itemBinding: ItemChildHomeBinding,
        private val imageLoader: ImageLoader,
        private val onItemClick: (film: Film, view: View) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val itemFilm = itemBinding.itemPoster

        fun bind(homeChildVE: HomeChildVE) {
            itemFilm.transitionName = homeChildVE.title

            imageLoader.load(
                itemFilm, homeChildVE.imageUrl
            )
            val film = homeChildVE.toFilm()

            itemView.setOnClickListener {
                onItemClick.invoke(film, itemFilm)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader,
                onItemClick: (film: Film, view: View) -> Unit
            ): HomeChildViewHolder {
                val itemBinding = ItemChildHomeBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeChildViewHolder(itemBinding, imageLoader, onItemClick)
            }
        }
    }
}