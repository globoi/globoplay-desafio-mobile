package com.mazer.globoplayapp.presentation.ui.details

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.ActivityMovieDetailsBinding
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.adapter.PagerAdapter
import com.mazer.globoplayapp.presentation.ui.player.PlayerActivity
import com.mazer.globoplayapp.utils.AppConstants
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding
    private val viewModel : MovieDetailsViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        registerObservers()
        intent.extras?.let {
            viewModel.setExtras(it)
        }
    }

    private fun registerObservers(){
        viewModel.movieDetails.observe(this) {
            setupView(it)
        }
        viewModel.btnAddToFavoriteVisibility.observe(this) {
            toggleButtonAddToFavoriteVisible(it)
        }
        viewModel.btnFavoritedVisibility.observe(this){
            toggleFavoritedButtonVisible(it)
        }
    }

    private fun setupView(movie: Movie){
        setMovieDetails(movie)
        setupTabLayout(movie)
        setupFavoriteButton(movie)

        binding.ivBackButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.btnPlay.root.setOnClickListener {
            goToPayerScreen(movie)
        }
    }

    private fun setupFavoriteButton(movie: Movie?) {
        binding.btnAddToFavorite.root.setOnClickListener {
            viewModel.addMovieToFavorites(movie)
        }

        binding.btnFavorited.root.setOnClickListener {
            viewModel.deleteMovieFromFavorites(movie)
        }
    }

    private fun goToPayerScreen(movie: Movie){
        val intent = Intent(this, PlayerActivity::class.java)
        intent.putExtra(AppConstants.VIDEO_EXTRA, movie)
        startActivity(intent)
    }

    private fun toggleButtonAddToFavoriteVisible(isVisible: Boolean){
        binding.btnAddToFavorite.root.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun toggleFavoritedButtonVisible(isVisible: Boolean){
        binding.btnFavorited.root.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun setMovieDetails(movie: Movie?) {
        Glide.with(this).load(AppConstants.BASE_IMG_URL_SMALL + movie?.posterPath).into(binding.tvMoviePoster)
        Glide.with(this).load(AppConstants.BASE_IMG_URL_SMALL + movie?.posterPath)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(25, 3)))
            .into(binding.ivImgBackground)
        binding.tvMovieTitle.text = movie?.title
        binding.tvGenre.text = movie?.genre
        binding.tvMovieDescription.text = movie?.overview
    }

    private fun setupTabLayout(movie: Movie?){
        val pagerAdapter = PagerAdapter(this)
        pagerAdapter.setMovie(movie ?: return)
        binding.viewPager.adapter = pagerAdapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (pagerAdapter.getItemId(position)) {
                0L -> {
                    tab.text = getString(R.string.tab_movie_recommendation)
                }
                1L -> {
                    tab.text = getString(R.string.tab_movie_details)
                }

            }

            if (position == binding.viewPager.currentItem){
                tab.customView?.findViewById<TextView>(binding.tabLayout.id)?.setTextAppearance(R.style.TabLayoutTextActive)
            }else{
                tab.customView?.findViewById<TextView>(binding.tabLayout.id)?.setTextAppearance(R.style.TabLayoutTextInactive)
            }
        }.attach()
    }

}