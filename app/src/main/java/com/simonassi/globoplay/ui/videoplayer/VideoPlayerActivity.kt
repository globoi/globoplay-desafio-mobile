package com.simonassi.globoplay.ui.videoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import com.simonassi.globoplay.R
import com.simonassi.globoplay.databinding.ActivityVideoPlayerBinding

class VideoPlayerActivity : AppCompatActivity() {

    var videoView: VideoView? = null
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val videoUrl=intent.getStringExtra("video_url")
        if(videoUrl.isNullOrEmpty()){
            finish()
        }
        binding.videoLoadingProgressBar.visibility = View.VISIBLE
        videoView = binding.videoView
        if (mediaControls == null) {
            mediaControls = MediaController(this)
            mediaControls!!.setAnchorView(this.videoView)
        }

        videoView!!.setMediaController(mediaControls)
        videoView!!.setVideoPath(videoUrl)
        videoView!!.requestFocus()
        videoView!!.start()

        videoView!!.setOnCompletionListener {
            finish()
        }

        videoView!!.setOnPreparedListener {
            binding.videoLoadingProgressBar.visibility = View.INVISIBLE
        }

        videoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, getString(R.string.video_error), Toast.LENGTH_LONG).show()
            finish()
            false
        }
    }

}