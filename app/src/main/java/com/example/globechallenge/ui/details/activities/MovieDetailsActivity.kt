package com.example.globechallenge.ui.details.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.globechallenge.data.repository.details.MovieDetailsRepository
import com.example.globechallenge.databinding.ActivityMovieDetailsBinding
import com.example.globechallenge.helper.concatGenre
import com.example.globechallenge.helper.loadImage
import com.example.globechallenge.ui.details.adapters.MovieInfoAdapter
import com.example.globechallenge.ui.details.fragments.DetailsFragment
import com.example.globechallenge.ui.details.fragments.MyFavoriteMovieFragment
import com.example.globechallenge.ui.details.viewmodels.MovieDetailsViewModel

class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private lateinit var viewModel: MovieDetailsViewModel
    private val detailFragment = DetailsFragment()
    private val myFavoriteMovieFragment = MyFavoriteMovieFragment()

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

    private fun setupPageView() {
        val adapter = MovieInfoAdapter(supportFragmentManager)
        adapter.addFragment(myFavoriteMovieFragment, MyFavoriteMovieFragment.TITLE_MY_FAVORITE)
        adapter.addFragment(detailFragment, DetailsFragment.TITLE_DETAIL)
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            MovieDetailsViewModel.
            MovieDetailViewModelFactory(MovieDetailsRepository()))
            .get(MovieDetailsViewModel::class.java)
    }

    private fun getValuesIntent() {
        intent.getStringExtra(EXTRA_ID).run {
            viewModel.getMovieDetail(this.toString())
        }
        intent.getStringExtra(EXTRA_ID).run {
            viewModel.getMovieCreditToGetCast(this.toString())
        }
    }

    private fun setValues(){
        viewModel.movieDetail.observe(this) {
            binding.txtTitle.text = it.title
            binding.txtDescription.text = it.overview
            binding.txtGenre.text = it.genres.concatGenre()
            binding.imgBlur.loadImage(it.postPath, true)
            binding.imgMovie.loadImage(it.postPath)
            detailFragment.setMovie(it)
        }

        viewModel.movieCreditToGetCast.observe(this) {
            detailFragment.setMovieCast(it)
        }
    }

    companion object {
        const val EXTRA_ID = "EXTRA_ID"
        fun getIntentMovieDetail(context: Context): Intent {
            return Intent(context, MovieDetailsActivity::class.java)
        }
    }
}