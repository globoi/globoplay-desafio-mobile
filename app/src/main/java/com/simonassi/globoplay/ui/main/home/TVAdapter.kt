package com.simonassi.globoplay.ui.main.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.ListAdapter
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.Tv
import com.simonassi.globoplay.databinding.ListItemTvBinding
import com.simonassi.globoplay.utilities.contants.ImageQualitySpec
import com.simonassi.globoplay.utilities.contants.ItemType

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
                binding.tvItem?.let { tvItem ->
                    navigateToHighLights(it, tvItem)
                }
            }
        }

        private fun navigateToHighLights(view: View, tvItem: Tv) {
            val direction = HomeFragmentDirections.actionHomePagerFragmentToHighlightsActivity(
                tvItem.id,
                ItemType.TV
            )
            view.findNavController().navigate(direction)

        }

        fun bind(item: Tv) {
            binding.apply {
                item.cover = BuildConfig.BUCKET_URL + ImageQualitySpec.MID_QUALITY + item.cover
                item.backdropCover = BuildConfig.BUCKET_URL + ImageQualitySpec.MID_QUALITY + item.cover
                tvItem = item
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