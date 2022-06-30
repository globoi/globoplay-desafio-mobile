package com.nroncari.movieplay.presentation.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.nroncari.movieplay.databinding.FragmentMovieDetailBinding
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import com.nroncari.movieplay.presentation.viewmodel.MovieDetailViewModel
import com.nroncari.movieplay.utils.loadWallpaper
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : Fragment() {

    private val binding by lazy { FragmentMovieDetailBinding.inflate(layoutInflater) }
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val movieId by lazy { args.movieId }
    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        listeners()
    }

    private fun listeners() {
        viewModel.getMovieDetail(movieId)

        viewModel.movie.observe(viewLifecycleOwner) { loadMovieFields(it) }
    }

    private fun loadMovieFields(movie: MovieDetailPresentation) {
        binding.movieTitle.text = movie.title
        binding.movieDescription.text = movie.overview
        binding.movieDetailWallpaper.loadWallpaper(movie.posterPath)

        val bgPicture = "https://image.tmdb.org/t/p/w300${movie.backdropPath}"
        Glide.with(this)
            .load(bgPicture)
            .apply(RequestOptions.bitmapTransform(BlurTransformation(18, 3)))
            .transition(DrawableTransitionOptions.withCrossFade(1500))
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    binding.movieBgPicture.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })
    }
}