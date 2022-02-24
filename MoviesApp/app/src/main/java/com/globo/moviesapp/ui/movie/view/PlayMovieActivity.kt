package com.globo.moviesapp.ui.movie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.globo.moviesapp.R
import com.globo.moviesapp.model.movie.Movie
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import kotlinx.android.synthetic.main.activity_play_movie.*

class PlayMovieActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener{
    companion object {
        const val MOVIE_VIDEO_BUNDLE = "movie_video_bundle"

        fun newInstance(context: Context, movie: Movie) {
            val intent = Intent(context, PlayMovieActivity::class.java)
            intent.putExtra(MOVIE_VIDEO_BUNDLE, movie)
            context.startActivity(intent)
        }
    }

    lateinit var movie: Movie

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_movie)

        movie = intent.getSerializableExtra(MOVIE_VIDEO_BUNDLE) as Movie

        ypvMovie.initialize(getString(R.string.api_key_google), this)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        p1?.cueVideo(movie.keyYoutube)
        p1?.play()
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        finish()
    }
}