package com.reisdeveloper.globoplay.ui.features.movie.main

import android.os.Build
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.reisdeveloper.data.dataModel.Favorite
import com.reisdeveloper.globoplay.R
import com.reisdeveloper.globoplay.base.BASE_IMAGE_URL
import com.reisdeveloper.globoplay.base.BaseFragment
import com.reisdeveloper.globoplay.databinding.FragmentMovieDetailsBinding
import com.reisdeveloper.globoplay.ui.features.movie.adapter.MovieDetailsAdapter
import com.reisdeveloper.globoplay.ui.uiModel.FavoriteMovieUiModel
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(
    FragmentMovieDetailsBinding::inflate
) {
    override val viewModel: MovieDetailsViewModel by viewModel()

    private val movieExtra by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(EXTRA_MOVIE, FavoriteMovieUiModel::class.java)
        } else {
            arguments?.getParcelable(EXTRA_MOVIE)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setMovieData()

        setupAppBar()

        viewClicks()

        //TODO não está funcionando. Descobrir o motivo
        binding.viewPager.adapter = MovieDetailsAdapter(this, movieExtra?.id.toString())

        TabLayoutMediator(binding.tabMovieDetails, binding.viewPager) { _, _ -> }.attach()

        binding.tabMovieDetails.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                binding.viewPager.currentItem = tab?.position!!
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    private fun setMovieData() {
        with(binding) {
            Glide.with(binding.root.context)
                .load("$BASE_IMAGE_URL${movieExtra?.posterPath}")
                .apply(bitmapTransform(BlurTransformation(25, 3)))
                .placeholder(R.drawable.bg_holder)
                .error(R.drawable.bg_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgMovieDetailsBackCover)

            Glide.with(binding.root.context)
                .load("$BASE_IMAGE_URL${movieExtra?.posterPath}")
                .placeholder(R.drawable.bg_holder)
                .error(R.drawable.bg_holder)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgMovieDetailsCover)

            txtMovieDetailsTitle.text = movieExtra?.title
            txtMovieDetailsCategory.text = movieExtra?.title
            txtMovieDetailsDescription.text = movieExtra?.overview
        }
    }

    private fun setupAppBar() {
        var isShow = false
        var scrollRange = -1
        binding.appBar.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = appBarLayout.totalScrollRange
            }
            if (scrollRange + verticalOffset <= 0) {
                isShow = true
            } else if (isShow) {
                isShow = false
            }
        }
    }

    private fun viewClicks() {
        binding.btnMovieDetailsPlay.setOnClickListener {

        }

        binding.btnMovieDetailsFavorite.setOnClickListener {
            //TODO implementar uma forma de pegar esse account id
            viewModel.favoriteMovie(
                "20244782", Favorite(
                    true,
                    movieExtra?.id ?: 0,
                    "movie"
                )
            )
        }
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
    }
}