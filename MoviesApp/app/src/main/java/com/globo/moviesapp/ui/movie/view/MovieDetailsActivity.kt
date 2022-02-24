package com.globo.moviesapp.ui.movie.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.globo.moviesapp.R
import com.globo.moviesapp.model.movie.Movie
import com.globo.moviesapp.shared.FragmentTabHelper
import com.globo.moviesapp.ui.movie.fragment.MovieDatasheetFragment
import com.globo.moviesapp.ui.movie.fragment.MovieWatchTooFragment
import com.globo.moviesapp.ui.movie.viewmodel.MovieViewModel
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerAppCompatActivity
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.activity_movie_details.*
import kotlinx.android.synthetic.main.activity_panel.*
import kotlinx.android.synthetic.main.button_watch_my_list_movie_details.*
import kotlinx.android.synthetic.main.header_movie_details.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MovieDetailsActivity : DaggerAppCompatActivity() {
    companion object {
        const val URL_IMAGE = "https://image.tmdb.org/t/p/w500/"
        const val MOVIE_BUNDLE = "movie_bundle"

        fun newInstance(context: Context, movie: Movie) {
            val intent = Intent(context, MovieDetailsActivity::class.java)
            intent.putExtra(MOVIE_BUNDLE, movie)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val movieViewModel: MovieViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_details)
        val movie = intent.getSerializableExtra(MOVIE_BUNDLE) as Movie

        movieViewModel.apiKey = getString(R.string.api_key_movie_db)
        movieViewModel.languageLocale = getString(R.string.locale_movie_db)

        setupToolbar()
        setupTab(movie)
        setupViewModel(movie)
    }

    private fun setupViewModel(movie: Movie){
        movieViewModel.movie.observe(this){
            initViews(it)
        }
        movieViewModel.isLoading.observe(this){
            updateIsLoading(it)
        }

        movieViewModel.error.observe(this){
            updateIsErrors(!it.isNullOrBlank())
        }

        movieViewModel.openMovie(movie.id!!)
    }

    private fun updateIsErrors(status: Boolean){
        if(status) {
            viewFailConnection.visibility = View.VISIBLE
            clMovieDetails.visibility = View.GONE
        } else {
            viewFailConnection.visibility = View.GONE
            clMovieDetails.visibility = View.VISIBLE
        }
    }

    private fun updateIsLoading(status: Boolean){
        viewMain.visibility = if(status) View.VISIBLE else View.GONE
    }

    private fun initViews(movie: Movie){
        tvTitleMovie.text = movie.name
        tvResumeMovie.text = if(movie.overview.length < 130) movie.overview else
            "${movie.overview.substring(0, 130)}${if(movie.overview.length > 130) "..." else ""}"
        tvTypeMovie.text = movie.type

        val newImage = ContextCompat.getDrawable(this, if(movie.isFavorite)
            R.drawable.ic_baseline_check_24 else R.drawable.ic_baseline_star_white_24)
        ivMyList.setImageDrawable(newImage)
        tvMyList.text = if(movie.isFavorite) getString(R.string.btn_my_list_add)
            else getString(R.string.btn_my_list)

        cvMyList.setOnClickListener{
            if(movieViewModel.isLoading.value == false) {
                CoroutineScope(Dispatchers.IO).launch {
                    movieViewModel.updateFavoriteMovie(movie.id!!, !movie.isFavorite)
                }
            }
        }

        cvWatch.setOnClickListener{
            if(movieViewModel.isLoading.value == false) {
                if(movie.keyYoutube.isNullOrBlank()){
                    Toast.makeText(this, getString(R.string.msg_not_video),
                        Toast.LENGTH_SHORT).show()
                } else {
                    PlayMovieActivity.newInstance(this, movie)
                }
            }
        }

        val requestOptions = RequestOptions().placeholder(R.drawable.white_background)
            .error(R.drawable.white_background)
        Glide.with(this).setDefaultRequestOptions(requestOptions)
            .load("${URL_IMAGE}${movie.poster_path}").transform(BlurTransformation(10, 10)).into(ivPhotoMovieBackground)
        Glide.with(this).setDefaultRequestOptions(requestOptions)
            .load("${URL_IMAGE}${movie.poster_path}").into(ivPhotoMovie)
    }

    private fun setupToolbar() {
        movieToolbar.title = ""
        setSupportActionBar(movieToolbar)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_white_24)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupTab(movie: Movie){
        val titleTab = arrayListOf(
            getString(R.string.title_tab_watch_other),
            getString(R.string.title_tab_details)
        )

        val fragments = arrayListOf<Fragment>(
            MovieWatchTooFragment.newInstance(movie),
            MovieDatasheetFragment.newInstance(movie)
        )

        val tabAdapter = FragmentTabHelper(this, fragments)
        movieDetailsViewPager.isUserInputEnabled = false
        movieDetailsViewPager.adapter = tabAdapter
        movieDetailsViewPager.currentItem = 1

        TabLayoutMediator(movieDetailsTabLayout, movieDetailsViewPager) { tab, position ->
            tab.text = titleTab[position]
        }.attach()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}