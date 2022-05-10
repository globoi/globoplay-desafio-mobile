package com.ftoniolo.globoplay.presentation.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.globoplay.databinding.ItemParentHomeBinding
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader

class HomeParentAdapter(
    private val homeParentList: List<HomeParentVE>,
    private val imageLoader: ImageLoader,
    private val onItemClick: (film: Film, view: View) -> Unit
) : RecyclerView.Adapter<HomeParentAdapter.HomeParentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeParentViewHolder {
        return HomeParentViewHolder.create(parent, imageLoader, onItemClick)
    }

    override fun onBindViewHolder(holder: HomeParentViewHolder, position: Int) {
        holder.bind(homeParentList[position])
    }

    override fun getItemCount() = homeParentList.size

    class HomeParentViewHolder(
        itemBinding: ItemParentHomeBinding,
        private val imageLoader: ImageLoader,
        private val onItemClick: (film: Film, view: View) -> Unit
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        private val textItemCategory: TextView = itemBinding.textItemCategory
        private val rvChildHome: RecyclerView = itemBinding.rvChildHome


        fun bind(homeParentVE: HomeParentVE) {
            textItemCategory.text = itemView.context.getString(homeParentVE.categoryStringResId)
            rvChildHome.run {
                setHasFixedSize(true)
                adapter = HomeChildAdapter(homeParentVE.homeChildList, imageLoader, onItemClick)
            }
        }

        companion object {
            fun create(
                parent: ViewGroup,
                imageLoader: ImageLoader,
                onItemClick: (film: Film, view: View) -> Unit
            ): HomeParentViewHolder {
                val itemBinding = ItemParentHomeBinding
                    .inflate(LayoutInflater.from(parent.context), parent, false)

                return HomeParentViewHolder(itemBinding, imageLoader, onItemClick)
            }
        }
    }
}