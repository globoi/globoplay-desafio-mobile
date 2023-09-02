package com.gmribas.globoplaydesafiomobile.feature.player

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.constants.Constants.VIDEO_KEY
import com.gmribas.globoplaydesafiomobile.databinding.AppPlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
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

        val videoKeys = intent.extras?.getStringArrayList(VIDEO_KEY)

        if (videoKeys?.isNotEmpty() == true) {
            val copyVideoKeys = ArrayList(videoKeys)

            val currentVideo = copyVideoKeys.first()

            startPlaying(currentVideo, copyVideoKeys)
        } else {
            Toast.makeText(this, getString(R.string.app_player_empty_video_key), Toast.LENGTH_LONG)
                .show()
        }
    }

    private fun startPlaying(key: String, allKeys: ArrayList<String>) {
        binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onStateChange(
                youTubePlayer: YouTubePlayer,
                state: PlayerConstants.PlayerState
            ) {
                if (state == PlayerConstants.PlayerState.ENDED) {
                    allKeys.remove(key)
                    binding.youtubePlayerView.release()
                    binding.youtubePlayerView.removeYouTubePlayerListener(this)

                    val currentKey = allKeys.firstOrNull()
                    if (currentKey != null) {
                        startPlaying(currentKey, allKeys)
                    } else {
                        finish()
                    }
                }
            }

            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(key, 0f)
            }
        })
    }
}