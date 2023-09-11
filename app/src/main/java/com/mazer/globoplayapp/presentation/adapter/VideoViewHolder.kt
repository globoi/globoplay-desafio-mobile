package com.mazer.globoplayapp.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.ItemVideoListBinding
import com.mazer.globoplayapp.domain.entities.Video
import com.mazer.globoplayapp.presentation.wrapper.VideoUI
import com.mazer.globoplayapp.utils.AppConstants
import com.mazer.globoplayapp.utils.AppConstants.URL_THUMB

class VideoViewHolder  (private val binding: ItemVideoListBinding, private val onVideoSelected: (video: VideoUI) -> Unit) : RecyclerView.ViewHolder(binding.root) {

    fun bind(video: VideoUI) {
        binding.tvVideoTitle.text = video.video.name
        binding.tvVideoType.text = video.video.type
        binding.tvReleaseDate.text = video.video.publishDate

        val urlThumb = URL_THUMB.format(video.video.youtubeKey)
        Glide.with(binding.root.context).load(urlThumb).into(binding.ivThumb)

        if (video.isPlaying){
            binding.ivPlay.visibility = View.GONE
            binding.tvPlayingNow.visibility = View.VISIBLE
        }else{
            binding.ivPlay.visibility = View.VISIBLE
            binding.tvPlayingNow.visibility = View.GONE
        }

        binding.root.setOnClickListener {
            onVideoSelected.invoke(video)
        }
    }
}

