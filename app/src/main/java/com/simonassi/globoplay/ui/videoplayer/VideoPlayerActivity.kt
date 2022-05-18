package com.simonassi.globoplay.ui.videoplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.MediaController
import android.widget.Toast
import android.widget.VideoView
import androidx.navigation.navArgs
import com.simonassi.globoplay.R
import com.simonassi.globoplay.databinding.ActivityVideoPlayerBinding
import com.simonassi.globoplay.ui.highlights.HighlightsActivityArgs

class VideoPlayerActivity : AppCompatActivity() {

    var videoView: VideoView? = null
    var mediaControls: MediaController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideoPlayerBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val videoUrl=intent.getStringExtra("video_url")


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
            println("OK")
        }

        videoView!!.setOnPreparedListener {
            println("OK")
        }
        



        videoView!!.setOnErrorListener { mp, what, extra ->
            Toast.makeText(applicationContext, "An Error Occured " +
                    "While Playing Video !!!", Toast.LENGTH_LONG).show()
            false
        }

    }

}