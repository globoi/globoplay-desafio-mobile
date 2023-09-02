package com.gmribas.globoplaydesafiomobile.feature.player

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.constants.Constants.VIDEO_KEY
import com.gmribas.globoplaydesafiomobile.databinding.AppPlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


/**
 *  Thanks to PierfrancescoSoffritti
 *  https://github.com/PierfrancescoSoffritti/android-youtube-player
 */
class AppPlayer : AppCompatActivity() {

    private val binding by lazy {
        AppPlayerBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        lifecycle.addObserver(binding.youtubePlayerView)

        val videoKey = intent.extras?.getString(VIDEO_KEY)

        if (videoKey?.isNotBlank() == true) {
            binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    youTubePlayer.loadVideo(videoKey, 0f)
                }
            })

        } else {
            Toast.makeText(this, getString(R.string.app_player_empty_video_key), Toast.LENGTH_LONG)
                .show()
        }
    }
}