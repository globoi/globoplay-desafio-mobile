package com.reisdeveloper.globoplay.ui.features.movie.details

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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
import com.reisdeveloper.globoplay.extensions.safeNavigate
import com.reisdeveloper.globoplay.ui.features.movie.adapter.MovieDetailsAdapter
import com.reisdeveloper.globoplay.ui.features.movie.player.PlayerMovieFragment
import com.reisdeveloper.globoplay.ui.uiModel.MovieUiModel
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailsFragment : BaseFragment<FragmentMovieDetailsBinding, MovieDetailsViewModel>(
    FragmentMovieDetailsBinding::inflate
) {
    override val viewModel: MovieDetailsViewModel by viewModel()

    private val movieExtra by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(EXTRA_MOVIE, MovieUiModel::class.java)
        } else {
            arguments?.getParcelable(EXTRA_MOVIE)
        }
    }

    private var favorite: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.topAppBar.setNavigationOnClickListener {
            onBackPressed()
        }
        favorite = arguments?.getBoolean(EXTRA_FAVORITE, false) == true

        setupObserver()

        setMovieData()

        setupAppBar()

        viewClicks()

        setupMoreDetails()
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            viewModel.screen.collectLatest { state ->
                when (state) {
                    is MovieDetailsViewModel.Screen.Error -> {
                        showError(getString(R.string.there_was_an_error_when_favorite_the_movie))
                    }

                    is MovieDetailsViewModel.Screen.Loading -> {
                        binding.progressFavorite.isVisible = state.loading
                        binding.btnMovieDetailsFavorite.isVisible = !state.loading
                    }

                    is MovieDetailsViewModel.Screen.FavoriteMovie -> {
                        favorite = state.favorite
                        binding.btnMovieDetailsFavorite.setIconResource(getFavoriteIcon())
                    }
                }
            }
        }
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
            txtMovieDetailsDescription.text = movieExtra?.overview
            btnMovieDetailsFavorite.setIconResource(getFavoriteIcon())
        }
    }

    private fun getFavoriteIcon(): Int {
        return if (favorite) {
            R.drawable.ic_check_24
        } else {
            R.drawable.ic_star_rate_24dp
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
                binding.appBar.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.appBar.context,
                        android.R.color.white
                    )
                )
            } else if (isShow) {
                isShow = false
                binding.appBar.setBackgroundColor(
                    ContextCompat.getColor(
                        binding.appBar.context,
                        android.R.color.transparent
                    )
                )
            }
        }
    }

    private fun viewClicks() {
        binding.btnMovieDetailsPlay.setOnClickListener {
            findNavController().safeNavigate(
                R.id.action_navigation_movie_details_to_navigation_movie_player,
                Bundle().apply {
                    putString(PlayerMovieFragment.EXTRA_MOVIE_ID, movieExtra?.id.toString())
                }
            )
        }

        binding.btnMovieDetailsFavorite.setOnClickListener {
            viewModel.favoriteMovie(
                Favorite(
                    !favorite,
                    movieExtra?.id ?: 0,
                    MOVIE_TYPE
                )
            )
        }
    }

    private fun setupMoreDetails() {
        binding.viewPager.adapter = MovieDetailsAdapter(this, movieExtra?.id.toString())

        TabLayoutMediator(binding.tabMovieDetails, binding.viewPager) { _, _ -> }.attach()

        TabLayoutMediator(binding.tabMovieDetails, binding.viewPager) { tab, position ->
            tab.text = if (position == 0) {
                getString(R.string.watch_too)
            } else {
                getString(R.string.details)
            }
        }.attach()

        binding.tabMovieDetails.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {

            override fun onTabSelected(tab: TabLayout.Tab?) {
                tab?.position?.let {
                    binding.viewPager.currentItem = it
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}
        })
    }

    companion object {
        const val EXTRA_MOVIE = "EXTRA_MOVIE"
        const val EXTRA_FAVORITE = "EXTRA_FAVORITE"
        const val MOVIE_TYPE = "movie"
    }
}