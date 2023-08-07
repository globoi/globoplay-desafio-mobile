package com.reisdeveloper.globoplay.ui.features.movie.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentPlayerBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class PlayerMovieFragment : BaseFragment<FragmentPlayerBinding, PlayerMovieViewModel>(
    FragmentPlayerBinding::inflate
) {
    override val viewModel: PlayerMovieViewModel by viewModel()

    private var youtubePlayer: YouTubePlayer? = null

    private val movieId by lazy {
        arguments?.getString(EXTRA_MOVIE_ID)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        setupYoutubeListener()

        setupObserver()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.screen.collectLatest { state ->
                when (state) {
                    is PlayerMovieViewModel.Screen.Error -> {
                        showError(getString(R.string.there_was_an_error_loading_videos))
                    }

                    is PlayerMovieViewModel.Screen.GetMovieVideos -> {
                        youtubePlayer?.loadVideo(
                            state.movies.results.filter { it.site == YOUTUBE }.random().key,
                            0f
                        )
                        youtubePlayer?.play()
                    }
                }
            }
        }
    }

    private fun setupYoutubeListener() {
        lifecycle.addObserver(binding.ytPlayerVideo)

        binding.ytPlayerVideo.addYouTubePlayerListener(object : YouTubePlayerListener {
            override fun onApiChange(youTubePlayer: YouTubePlayer) {}

            override fun onCurrentSecond(youTubePlayer: YouTubePlayer, second: Float) {}

            override fun onError(
                youTubePlayer: YouTubePlayer,
                error: PlayerConstants.PlayerError
            ) {
            }

            override fun onPlaybackQualityChange(
                youTubePlayer: YouTubePlayer,
                playbackQuality: PlayerConstants.PlaybackQuality
            ) {
            }

            override fun onPlaybackRateChange(
                youTubePlayer: YouTubePlayer,
                playbackRate: PlayerConstants.PlaybackRate
            ) {
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youtubePlayer = youTubePlayer
                movieId?.let { viewModel.getMovieVideos(it) }
            }

            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
            }

            override fun onVideoDuration(youTubePlayer: YouTubePlayer, duration: Float) {}

            override fun onVideoId(youTubePlayer: YouTubePlayer, videoId: String) {}

            override fun onVideoLoadedFraction(
                youTubePlayer: YouTubePlayer,
                loadedFraction: Float
            ) {
            }
        })
    }

    companion object {
        const val EXTRA_MOVIE_ID = "EXTRA_MOVIE_ID"
        const val YOUTUBE = "YouTube"
    }
}