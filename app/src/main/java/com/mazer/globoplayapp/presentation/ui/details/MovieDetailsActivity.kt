package com.mazer.globoplayapp.presentation.ui.details

import android.os.Build
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayoutMediator
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.ActivityMovieDetailsBinding
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.adapter.PagerAdapter
import com.mazer.globoplayapp.utils.AppConstants
import jp.wasabeef.glide.transformations.BlurTransformation


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movieDetails = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra(AppConstants.MOVIE_EXTRA, Movie::class.java)
        } else {
            intent.getParcelableExtra<Movie>(AppConstants.MOVIE_EXTRA)
        }
        setupView(movieDetails)
    }

    private fun setupView(movie: Movie?){
        setMovieDetails(movie)
        setupTabLayout()

        binding.ivBackButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
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

    private fun setupTabLayout(){
        val pagerAdapter = PagerAdapter(this)
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