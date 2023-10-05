package com.mazer.globoplayapp.presentation.ui.player

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.mazer.globoplayapp.databinding.ActivityPlayerBinding
import com.mazer.globoplayapp.presentation.adapter.VideoAdapter
import com.mazer.globoplayapp.presentation.wrapper.VideoUI
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.utils.loadOrCueVideo
import org.koin.androidx.viewmodel.ext.android.viewModel


class PlayerActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayerBinding
    private lateinit var adapter: VideoAdapter
    private val viewModel : PlayerViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        registerObservers()
        intent.extras?.let {
            viewModel.setExtras(it)
        }

    }

    private fun registerObservers() {
        lifecycle.addObserver(binding.youtubePlayerVideo)
        viewModel.videoList.observe(this) {
            setupVideoUI(it.toList())
        }
    }

    private fun setupVideoUI(videoList: List<VideoUI>) {
        val videoPlayingNow = videoList.first { it.isPlaying }
        playVideo(videoPlayingNow)
        setupVideoList(videoList)
    }

    private fun setupVideoList(videoList: List<VideoUI>) {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvVideoList.layoutManager = layoutManager
        adapter.setList(videoList)
    }

    private fun playVideo(videoPlayingNow: VideoUI) {
        binding.youtubePlayerVideo.getYouTubePlayerWhenReady(object: YouTubePlayerCallback{
            override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(videoPlayingNow.video.youtubeKey, 0f)
            }

        })
        binding.tvVideoTitle.text = videoPlayingNow.video.name
        binding.tvVideoType.text = videoPlayingNow.video.type
        binding.tvVideoReleaseDate.text = videoPlayingNow.video.publishDate
    }

    private fun setupView(){
        setupLayoutRecyclerView()
        binding.youtubePlayerVideo.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
            }
        })
    }


    private fun setupLayoutRecyclerView() {
        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvVideoList.layoutManager = layoutManager
        adapter = VideoAdapter {
            viewModel.setVideoPlaying(it.video)
        }
        binding.rvVideoList.adapter = adapter
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.youtubePlayerVideo.release()
    }
}
