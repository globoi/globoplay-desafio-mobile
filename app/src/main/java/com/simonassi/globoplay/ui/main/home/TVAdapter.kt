package com.simonassi.globoplay.ui.main.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.Tv
import com.simonassi.globoplay.databinding.ListItemTvBinding
import com.simonassi.globoplay.utilities.ImageQualitySpec

class TvAdapter : ListAdapter<Tv, RecyclerView.ViewHolder>(TvDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TvViewHolder(
            ListItemTvBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val tv = getItem(position)
        (holder as TvViewHolder).bind(tv)
    }

    class TvViewHolder(
        private val binding: ListItemTvBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.setClickListener {

            }
        }

        fun bind(item: Tv) {
            binding.apply {
                imageCover = BuildConfig.BUCKET_URL + ImageQualitySpec.MID_QUALITY + item.cover
                executePendingBindings()
            }
        }
    }
}

private class TvDiffCallback : DiffUtil.ItemCallback<Tv>() {

    override fun areItemsTheSame(oldItem: Tv, newItem: Tv): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Tv, newItem: Tv): Boolean {
        return oldItem == newItem
    }
}