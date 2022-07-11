package com.nroncari.movieplay.presentation.ui

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.nroncari.movieplay.databinding.ActivityYoutubePlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.options.IFramePlayerOptions


class YoutubePlayerActivity : AppCompatActivity() {

    private val binding by lazy { ActivityYoutubePlayerBinding.inflate(layoutInflater) }
    private val args by navArgs<YoutubePlayerActivityArgs>()
    private val pathVideo by lazy { args.movieDataVideoPath }
    private val iFramePlayerOptions: IFramePlayerOptions = IFramePlayerOptions.Builder()
        .controls(1)
        .ivLoadPolicy(3)
        .ccLoadPolicy(1)
        .rel(0)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        lifecycle.addObserver(binding.youtubePlayerView)

        binding.youtubePlayerView.enterFullScreen()
        binding.youtubePlayerView.initialize(
            abstractYouTubePlayerListener(), false, iFramePlayerOptions
        )
    }

    private fun abstractYouTubePlayerListener() = object :
        AbstractYouTubePlayerListener() {
        override fun onReady(youTubePlayer: YouTubePlayer) {
            val videoId = pathVideo
            youTubePlayer.loadVideo(videoId, 0f)
        }
    }
}