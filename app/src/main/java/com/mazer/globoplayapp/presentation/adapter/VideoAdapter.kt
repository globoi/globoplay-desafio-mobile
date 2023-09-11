package com.mazer.globoplayapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mazer.globoplayapp.databinding.ItemVideoListBinding
import com.mazer.globoplayapp.presentation.wrapper.VideoUI

class VideoAdapter (private val onVideoSelected: (video: VideoUI) -> Unit)  : RecyclerView.Adapter<VideoViewHolder>() {

    private var videoList: List<VideoUI> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        val binding = ItemVideoListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VideoViewHolder(binding, onVideoSelected)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        holder.bind(videoList[position])
    }

    fun setList(list: List<VideoUI>) {
        this.videoList = list
        notifyDataSetChanged()
    }
    override fun getItemCount(): Int =  videoList.size
}
