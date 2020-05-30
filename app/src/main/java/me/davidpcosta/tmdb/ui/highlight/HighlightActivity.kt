package me.davidpcosta.tmdb.ui.highlight

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import me.davidpcosta.tmdb.BuildConfig
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.data.model.Movie


class HighlightActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlight)


        val movie = intent.getSerializableExtra("movie") as Movie

        val sectionsPagerAdapter = SectionsPagerAdapter(movie,this, supportFragmentManager)

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


        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter

        val tabs: TabLayout = findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)

    }
}