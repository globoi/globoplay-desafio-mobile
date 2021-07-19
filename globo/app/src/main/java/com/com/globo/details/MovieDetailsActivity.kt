package com.com.globo.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.viewpager.widget.ViewPager
import com.com.globo.BuildConfig
import com.com.globo.R
import com.com.globo.details.helper.getNamesFormattedMovieDetails
import com.com.globo.details.model.MovieDetailsResponse
import com.com.globo.extension.loadImageMovie
import com.com.globo.helper.TransitionHelper
import com.com.globo.repository.model.Movie
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout


class MovieDetailsActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_LIST_MOVIE = "EXTRA_LIST_MOVIE"
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        const val EXTRA_MOVIE_DETAILS = "EXTRA_MOVIE_DETAILS"
    }

    private val viewModel = MovieDetailsViewModel()
    private val viewPager by lazy { findViewById<ViewPager>(R.id.viewpager) }
    private val appBarLayout by lazy { findViewById<AppBarLayout>(R.id.appbar) }
    private val tabLayout by lazy { findViewById<TabLayout>(R.id.tablayout) }
    private val imageView by lazy { findViewById<ImageView>(R.id.imageview) }
    private val imageViewBig by lazy { findViewById<ImageView>(R.id.imageview_big) }
    private val imageViewShadow by lazy { findViewById<View>(R.id.view_shadow) }
    private val shadowAllImageView by lazy { findViewById<View>(R.id.view_shadow_all) }
    private val cardView by lazy { findViewById<View>(R.id.cardview) }
    private val txtCategory by lazy { findViewById<TextView>(R.id.txt_category) }
    private val movie by lazy { intent.getParcelableExtra<Movie>(EXTRA_MOVIE) }

    private val buttonMyList by lazy { findViewById<MaterialButton>(R.id.button_my_list) }
    private var isAddMovieToList: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)

        initToolbar()
        initTabLayout()
        initButtons()
        initLayoutMovie(movie)
        startListenerOpacity()
        changeStateButtonList(isAddMovieToList)

        viewModel.answers.observe(this, Observer {
            when (it) {
                is MovieDetailsAnswer.ShowMovieDetails -> showMovieDetails(it.movieDetails)
                is MovieDetailsAnswer.AlphaChange -> changeAlpha(it)
            }
        })

        viewModel.answersList.observe(this, Observer {
            when (it) {
                is MovieMyListAnswers.ChangeStateMyList -> changeStateButtonList(!isAddMovieToList)
                is MovieMyListAnswers.ErrorOnChangeSelectedState -> showSnackbarError()
                is MovieMyListAnswers.ChangeStateMyListButton -> changeStateButtonList(it.currentState)
            }
        })

        viewModel.interact(MovieDetailsInteract.SearchMovie(movie))
    }

    private fun showSnackbarError() {
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(R.string.error_list_user),
            Snackbar.LENGTH_SHORT
        ).show()
    }

    override fun onResume() {
        super.onResume()
        viewModel.interact(MovieDetailsInteract.ShouldChangeStateMyButton(movie))
    }

    private fun initButtons() {
        findViewById<View>(R.id.button_watch).setOnClickListener { openTrailerMovie() }
        findViewById<View>(R.id.button_my_list).setOnClickListener {
            viewModel.interact(MovieDetailsInteract.ChangeMovieOnMyList(movie, isAddMovieToList))
        }
    }

    private fun changeStateButtonList(isAddMovieToList: Boolean) {
        this.isAddMovieToList = isAddMovieToList

        if (isAddMovieToList) {
            buttonMyList.setText(R.string.add)
            buttonMyList.icon = getDrawable(R.drawable.baseline_check_black_24)
        } else {
            buttonMyList.setText(R.string.my_list)
            buttonMyList.icon = getDrawable(R.drawable.baseline_star_rate_black_24)
        }
    }

    private fun initLayoutMovie(movie: Movie) {
        val txtTitle = findViewById<TextView>(R.id.txt_title)
        val txtOverview = findViewById<TextView>(R.id.txt_overview)

        txtTitle.text = movie.title
        txtCategory.text = movie.originalTitle
        txtOverview.text = movie.description

        imageView.loadImageMovie(movie)
        imageViewBig.loadImageMovie(movie)
    }

    private fun initTabLayout() {
        viewPager.adapter = TabsAdapter(
            mutableMapOf(
                Pair(getString(R.string.watch_more), MovieSeeMoreFragment())
            ),
            supportFragmentManager
        )

        tabLayout.setupWithViewPager(viewPager)
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar?.let {
            it.setHomeButtonEnabled(true)
            it.setDisplayHomeAsUpEnabled(true)
            it.title = ""
        }
    }

    private fun changeAlpha(it: MovieDetailsAnswer.AlphaChange) {
        imageViewShadow.alpha = it.opacityShadowBigPoster
        shadowAllImageView.alpha = it.percentageScroll
        imageViewBig.alpha = it.opacityBigPoster
        cardView.alpha = it.opacityPrincipalImage
    }

    private fun startListenerOpacity() {
        appBarLayout.addOnOffsetChangedListener(
            AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
                viewModel.interact(
                    MovieDetailsInteract.ScrollChanged(
                        Math.abs(verticalOffset).toFloat() / appBarLayout.totalScrollRange
                    )
                )
            }
        )
    }

    private fun showMovieDetails(movieDetails: MovieDetailsResponse) {
        intent.putExtra(EXTRA_MOVIE_DETAILS, movieDetails)

        (viewPager.adapter!! as TabsAdapter).addFragment(
            getString(R.string.details),
            MovieDetailsFragment()
        )

        getNamesFormattedMovieDetails(movieDetails.genres)?.let {
            txtCategory.text = it
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun openTrailerMovie() {
        val title = if (movie.originalTitle == null) movie.title else movie.originalTitle!!
        val url =
            "https://www.youtube.com/results?search_query=Official+Trailer+${title.replace(" ", "+")
                .replace(":", "")}+${BuildConfig.LANGUAGE}"

        val videoClient = Intent(Intent.ACTION_VIEW)
        videoClient.data = Uri.parse(url)
        startActivity(videoClient)
    }
}