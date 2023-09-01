package com.tiagopereira.globotmdb.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import coil.load
import coil.size.Scale
import com.github.chrisbanes.photoview.PhotoViewAttacher
import com.tiagopereira.globotmdb.R
import com.tiagopereira.globotmdb.application.MyApplication
import com.tiagopereira.globotmdb.databinding.ActivityMovieDetailsBinding
import com.tiagopereira.globotmdb.utils.Constants.Companion.BASE_URL_IMG
import com.tiagopereira.globotmdb.utils.Constants.Companion.ID_MOVIE
import com.tiagopereira.globotmdb.utils.Constants.Companion.NAME_MOVIE
import com.tiagopereira.globotmdb.utils.convertDateBr
import com.tiagopereira.globotmdb.utils.formatUs
import com.tiagopereira.globotmdb.viewmodel.DetailsMovieViewModel
import com.tiagopereira.globotmdb.viewmodel.factory.DetailsMovieViewModelFactory

class DetailsMovieActivity : AppCompatActivity() {

    private var idMovie = 0
    private var isFavorite = false
    private lateinit var _binding: ActivityMovieDetailsBinding
    private val binding get() = _binding
    private val viewModel: DetailsMovieViewModel by viewModels {
        DetailsMovieViewModelFactory((application as MyApplication).detailsRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        idMovie = intent.getIntExtra(ID_MOVIE, 0)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onStart() {
        super.onStart()

        if (idMovie > 0) {
            viewModel.loadDetailsMovie(idMovie)
            viewModel.isFavorite(idMovie)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.detailsMovie.observe(this) { response ->
            val moviePosterURL = BASE_URL_IMG + response.posterPath
            binding.imgMovie.load(moviePosterURL) {
                crossfade(true)
                crossfade(500)
                placeholder(R.drawable.baseline_theaters_24)
                scale(Scale.FILL)
            }
            binding.imgMovieBack.load(moviePosterURL) {
                crossfade(true)
                crossfade(500)
                placeholder(R.drawable.baseline_theaters_24)
                scale(Scale.FILL)
            }

            binding.zoomableImageView.load(moviePosterURL) {
                crossfade(true)
                crossfade(500)
                placeholder(R.drawable.baseline_theaters_24)
                scale(Scale.FILL)
            }

            binding.imgMovie.setOnClickListener {
                binding.zoomableImageView.visibility = View.VISIBLE
                binding.imgClose.visibility = View.VISIBLE
            }

            binding.imgClose.setOnClickListener {
                binding.zoomableImageView.visibility = View.GONE
                binding.imgClose.visibility = View.GONE
            }

            val attacher = PhotoViewAttacher(binding.zoomableImageView)
            binding.zoomableImageView.setOnClickListener {
                attacher.update()
            }

            binding.tvMovieTitle.text = response.title
            binding.tvMovieTagLine.text = response.tagline
            binding.tvMovieDateRelease.text = response.releaseDate.convertDateBr()
            binding.tvMovieRating.text = response.voteAverage.toString()
            binding.tvMovieRuntime.text = resources.getString(R.string.runtime_minutes, response.runtime.toString())
            binding.tvMovieBudget.text = response.budget.toDouble().formatUs() //response.budget.toString()
            binding.tvMovieRevenue.text = response.revenue.toDouble().formatUs()
            binding.tvMovieOverview.text = response.overview

            binding.imgStar.setOnClickListener {
                if (!isFavorite){
                    viewModel.favorite(response)
                } else {
                    viewModel.removeFavorite(response.id)
                }
            }

            binding.imgTrailer.setOnClickListener {
                goToTrailer(idMovie, response.title)
            }
        }

        viewModel.loading.observe(this) {
            if (it) {
                binding.prgBarMovies.visibility = View.VISIBLE
            } else {
                binding.prgBarMovies.visibility = View.INVISIBLE
            }
        }

        viewModel.star.observe(this) {
            binding.imgStar.setColorFilter(
                ContextCompat.getColor(
                    this@DetailsMovieActivity,
                    if(it) {R.color.star_selected} else {R.color.white}),
                android.graphics.PorterDuff.Mode.SRC_IN)
        }

        viewModel.isFavorite.observe(this) {
            isFavorite = it
        }
    }

    private fun goToTrailer(id: Int, name: String) {
        val intent = Intent(this, VideoActivity::class.java)
        intent.putExtra(ID_MOVIE, id)
        intent.putExtra(NAME_MOVIE, name)
        startActivity(intent)
    }
}