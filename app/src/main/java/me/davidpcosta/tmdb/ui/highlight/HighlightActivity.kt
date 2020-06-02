package me.davidpcosta.tmdb.ui.highlight

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import me.davidpcosta.tmdb.BuildConfig
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.toast


class HighlightActivity : AppCompatActivity() {

    private lateinit var highlightViewModel: HighlightViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var movie: Movie
    private lateinit var sessionId: String
    private var accountId: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlight)

        highlightViewModel = ViewModelProvider(this, HighlightViewModelFactory()).get(HighlightViewModel::class.java)
        movie = intent.getSerializableExtra("movie") as Movie
        sharedPreferences = getSharedPreferences("user_login", Context.MODE_PRIVATE)
        sessionId = sharedPreferences.getString("session_id", "")!!
        accountId = sharedPreferences.getLong("account_id", 0)

        initComponents()
        setViewData()
        observeWatchlistOperationResponse()
    }

    private fun initComponents() {
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = SectionsPagerAdapter(movie,this, supportFragmentManager)

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

//        val watchlistButton: Button = findViewById(R.id.add_to_watchlist_button)
//        watchlistButton.setOnClickListener {
//            highlightViewModel.addToWatchlist(accountId, sessionId, movie.id)
//        }

        val watchlistButton: Button = findViewById(R.id.add_to_watchlist_button)
        watchlistButton.setOnClickListener {
            highlightViewModel.removeFromWatchlist(accountId, sessionId, movie.id)
        }
    }

    private fun setViewData() {
        val moviePoster: ImageView = findViewById(R.id.movie_poster)
        Picasso.with(applicationContext)
            .load(BuildConfig.TMDB_IMAGE_URL + movie.posterPath)
            .into(moviePoster)

        val movieBackdrop: ImageView = findViewById(R.id.movie_backdrop)
        Picasso.with(applicationContext)
            .load(BuildConfig.TMDB_IMAGE_URL + movie.backdropPath)
            .transform(BlurTransformation(applicationContext, 5, 1))
            .into(movieBackdrop)

        val movieTitle: TextView = findViewById(R.id.movie_title)
        movieTitle.text = movie.title

        val movieOverview: TextView = findViewById(R.id.movie_overview)
        movieOverview.text = movie.overview
    }

    private fun observeWatchlistOperationResponse() {
        highlightViewModel.watchlistOperationResponse.observe(this, Observer {
            if (it.statusCode == 1 || it.statusCode == 12) {
                toast("Filme adicionado à sua lista")
            }
            else if (it.statusCode == 13) {
                toast("Filme removido sua lista")
            }
        })
    }
}