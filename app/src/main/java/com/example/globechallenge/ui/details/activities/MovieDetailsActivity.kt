package com.example.globechallenge.ui.details.activities

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.globechallenge.R
import com.example.globechallenge.application.GlobeChallengeApplication
import com.example.globechallenge.data.model.entities.FavoriteMoviesEntity
import com.example.globechallenge.data.model.features.home.MovieToGenre
import com.example.globechallenge.data.repository.details.MovieDetailsRepository
import com.example.globechallenge.databinding.ActivityMovieDetailsBinding
import com.example.globechallenge.helper.concatGenre
import com.example.globechallenge.helper.loadImage
import com.example.globechallenge.helper.setDrawable
import com.example.globechallenge.ui.FavoritesViewModel
import com.example.globechallenge.ui.FavoritiesViewModelFactory
import com.example.globechallenge.ui.details.adapters.MovieInfoAdapter
import com.example.globechallenge.ui.details.fragments.DetailsFragment
import com.example.globechallenge.ui.details.fragments.WatchTooFragment
import com.example.globechallenge.ui.details.viewmodels.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel
    private val detailFragment = DetailsFragment()
    private val watchTooFragment = WatchTooFragment()
    private var movieToGenre: ArrayList<MovieToGenre>? = ArrayList()
    private lateinit var favoriteMoviesEntity: FavoriteMoviesEntity
    private var movieId = ""
    private var imageString = ""
    private var isClicked = false
    private var key = ""

    private val favoritesViewModel: FavoritesViewModel by viewModels {
        FavoritiesViewModelFactory(
            (application as GlobeChallengeApplication)
                .repository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initialize()
    }

    private fun initialize() {
        setupPageView()
        setupViewModel()
        getValuesIntent()
        setValues()
    }

    private fun setupButtons() {
        binding.btnWatch.setOnClickListener {
            if(key == "") {
                Toast.makeText(this, getString(R.string.msg_no_filme), Toast.LENGTH_LONG).show()
            }
            openBrowser()
        }
        binding.btnMyList.setOnClickListener {
            isMovieSavedClicked()
        }
    }

    private fun openBrowser() {
        val url = urlPath + key
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }

    private fun deleteMovieInRoomById() {
        favoritesViewModel.deleteOneFavoriteMovie(movieId)
    }

    private fun insertMovieInRoomById() {
        setupFavoriteEntity(imageString, movieId)
        favoritesViewModel.insert(favoriteMoviesEntity)
    }

    private fun setupPageView() {
        val adapter = MovieInfoAdapter(supportFragmentManager)
        adapter.addFragment(watchTooFragment, WatchTooFragment.TITLE_MY_FAVORITE)
        adapter.addFragment(detailFragment, DetailsFragment.TITLE_DETAIL)
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModel
                .MovieDetailViewModelFactory(MovieDetailsRepository())
        )
            .get(MovieDetailsViewModel::class.java)
    }

    private fun getValuesIntent() {
        intent.getStringExtra(EXTRA_ID).run {
            movieId = this.toString()
            viewModel.getMovieDetail(movieId)
            viewModel.getMovieCreditToGetCast(movieId)
            viewModel.getMovieVideos(movieId)
        }
        movieToGenre = intent.getParcelableArrayListExtra(EXTRA_MOVIE_TO_GENRE)
        watchTooFragment.setMovieToGenre(movieToGenre)
    }

    private fun setValues() {
        viewModel.movieDetailLiveData.observe(this) {
            binding.txtTitle.text = it.title
            binding.txtDescription.text = it.overview
            binding.txtGenre.text = it.genres.concatGenre()
            binding.imgBlur.loadImage(it.postPath, true)
            binding.imgMovie.loadImage(it.postPath)
            detailFragment.setMovie(it)
            imageString = it.postPath
            setupFavoriteEntity(it.postPath, movieId)
            isInitialMovieSaved(movieId)
            setupButtons()
        }

        viewModel.movieCastMutableLiveData.observe(this) {
            detailFragment.setMovieCast(it)
        }

        viewModel.movieVideoMutableLivedata.observe(this) {
            if(!it.results.isNullOrEmpty()) {
               key = it.results[0].key
            }
        }
    }

    private fun isInitialMovieSaved(movieId: String) {
        favoritesViewModel.getFavoriteMovieById(movieId).observe(
            this@MovieDetailsActivity
        ) { favoriteMoviesEntity ->
            val isMovieSaved = favoriteMoviesEntity != null
            if(isMovieSaved) {
                isClicked = true
                setupBtnForAdded()
            } else {
                isClicked = false
                setupBtnForMyList()
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if(isClicked) {
            insertMovieInRoomById()
        }
        else {
            deleteMovieInRoomById()
        }
    }

    private fun isMovieSavedClicked() {
        isClicked = !isClicked
        if(isClicked) {
            setupBtnForAdded()
        } else {
            setupBtnForMyList()
        }
    }

    private fun setupBtnForAdded() {
        with(binding) {
            btnMyList.setDrawable(this@MovieDetailsActivity, R.drawable.ic_baseline_check)
            btnMyList.text = getString(R.string.added)
        }
    }

    private fun setupBtnForMyList() {
        with(binding) {
            btnMyList.setDrawable(this@MovieDetailsActivity, R.drawable.ic_star)
            btnMyList.text = getString(R.string.btn_my_list)
        }
    }

    private fun setupFavoriteEntity(image: String, movieId: String) {
        favoriteMoviesEntity = FavoriteMoviesEntity(image, movieId)
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        const val EXTRA_MOVIE_TO_GENRE = "EXTRA_MOVIE_TO_GENRE"
        const val urlPath = "https://www.youtube.com/watch?v="

        fun getIntentMovieDetail(context: Context): Intent {
            return Intent(context, MovieDetailsActivity::class.java)
        }
    }
}