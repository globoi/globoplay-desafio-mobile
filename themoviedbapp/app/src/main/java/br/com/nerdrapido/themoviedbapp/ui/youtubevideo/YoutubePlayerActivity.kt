package br.com.nerdrapido.themoviedbapp.ui.youtubevideo

import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View.VISIBLE
import android.view.Window
import android.view.WindowManager
import br.com.nerdrapido.themoviedbapp.BuildConfig
import br.com.nerdrapido.themoviedbapp.R
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayer.*
import kotlinx.android.synthetic.main.activity_youtube_player.*
import timber.log.Timber


/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 */
class YoutubePlayerActivity : YouTubeBaseActivity() {

    companion object {
        /**
         * The video id Intent Flag
         */
        const val VIDEO_ID = "VIDEO_ID"
    }

    /**
     * The video ID to be played
     */
    private var videoId: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        initView()
    }

    /**
     * View initialization
     */
    private fun initView() {
        setContentView(R.layout.activity_youtube_player)
        //get video id from intent
        val videoIdLocal = intent.extras?.getString(VIDEO_ID)
        if (videoIdLocal == null) {
            Timber.w("Could not get the VIDEO_ID ending activity")
            this.finish()
        } else {
            videoId = videoIdLocal
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.navigationBarColor = resources.getColor(R.color.colorBlack)
        }
        youtubePlayerView.initialize(
            BuildConfig.YOUTUBE_API_KEY,
            object : OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: Provider?,
                    youTubePlayer: YouTubePlayer?,
                    wasRestored: Boolean
                ) {

                    if (!wasRestored) {
                        youTubePlayer?.fullscreenControlFlags =
                            FULLSCREEN_FLAG_ALWAYS_FULLSCREEN_IN_LANDSCAPE or FULLSCREEN_FLAG_CONTROL_ORIENTATION or FULLSCREEN_FLAG_CUSTOM_LAYOUT or FULLSCREEN_FLAG_CONTROL_SYSTEM_UI
                        youTubePlayer?.loadVideo(videoId)
                        youTubePlayer?.play()
                    }
                }

                override fun onInitializationFailure(
                    p0: Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    errorTextView.visibility = VISIBLE
                }
            }
        )
    }
}