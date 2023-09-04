package com.tiagopereira.globotmdb.ui.view

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.tiagopereira.globotmdb.application.MyApplication
import com.tiagopereira.globotmdb.databinding.ActivityVideoBinding
import com.tiagopereira.globotmdb.utils.Constants.Companion.ID_MOVIE
import com.tiagopereira.globotmdb.utils.Constants.Companion.NAME_MOVIE
import com.tiagopereira.globotmdb.viewmodel.VideoViewModel
import com.tiagopereira.globotmdb.viewmodel.factory.VideoViewModelFactory


class VideoActivity: AppCompatActivity() {

    private var idMovie = 0
    private var nameMovie = "Trailer"
    private lateinit var _binding: ActivityVideoBinding
    private val binding get() = _binding
    private val viewModel: VideoViewModel by viewModels {
        VideoViewModelFactory((application as MyApplication).videoRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityVideoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        idMovie = intent.getIntExtra(ID_MOVIE, 0)
        nameMovie = intent.getStringExtra(NAME_MOVIE).toString()

        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = nameMovie
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()

        if (idMovie > 0) {
            viewModel.getTrailer(idMovie)
        }
    }

    override fun onResume() {
        super.onResume()

        binding.prgBarTrailer.visibility = View.VISIBLE

        viewModel.trailerMovie.observe(this) {
            binding.prgBarTrailer.visibility = View.GONE
            binding.youtubePlayerView.visibility = View.VISIBLE
            binding.txtError.visibility = View.GONE

            binding.youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    val videoId = it.results.last().key
                    youTubePlayer.loadVideo(videoId, 0f)
                }
            })
        }

        viewModel.showError.observe(this) {
            binding.prgBarTrailer.visibility = View.GONE
            binding.youtubePlayerView.visibility = View.GONE
            binding.txtError.visibility = View.VISIBLE
        }
    }
}