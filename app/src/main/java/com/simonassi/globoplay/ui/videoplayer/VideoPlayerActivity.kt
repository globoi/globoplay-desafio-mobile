package com.simonassi.globoplay.ui.videoplayer

import android.os.Bundle
import android.view.View
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
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

        videoView!!.setOnPreparedListener { mp ->
            val videoRatio: Float = mp.videoWidth.toFloat() / mp.videoHeight.toFloat()
            val screenRatio = videoView!!.width / videoView!!.height.toFloat()
            val scaleX = videoRatio / screenRatio
            if (scaleX >= 1f) {
                videoView!!.scaleX = scaleX
            } else {
                videoView!!.scaleY = 1f / scaleX
            }
            binding.videoLoadingProgressBar.visibility = View.INVISIBLE
        }

        videoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, getString(R.string.video_error), Toast.LENGTH_LONG).show()
            finish()
            false
        }
    }

}