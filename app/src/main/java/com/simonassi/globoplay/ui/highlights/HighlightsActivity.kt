package com.simonassi.globoplay.ui.highlights

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.data.Movie
import com.simonassi.globoplay.data.Tv
import com.simonassi.globoplay.databinding.ActivityHighlightsBinding
import com.simonassi.globoplay.utilities.contants.ImageQualitySpec
import com.simonassi.globoplay.utilities.contants.ItemType
import com.simonassi.globoplay.viewmodels.HighlightsViewModel
import com.simonassi.globoplay.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation

@AndroidEntryPoint
class HighlightsActivity : AppCompatActivity() {

    val args: HighlightsActivityArgs by navArgs()
    private val highlighthsViewModel: HighlightsViewModel by viewModels()
    private lateinit var movie: Movie
    private lateinit var tv: Tv
    private lateinit var binding: ActivityHighlightsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighlightsBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()
        setContentView(view)

        when (args.type) {
            ItemType.MOVIE -> {
                highlighthsViewModel.movieSearchLiveData.observe(this, Observer { fetchedMovie ->
                    setupMovie(fetchedMovie)
                })
                highlighthsViewModel.getMovieById(args.itemId)
            }
            ItemType.TV -> {
                highlighthsViewModel.tvSearchLiveData.observe(this, Observer { fetchedTv ->
                    setupTv(fetchedTv)
                })
                highlighthsViewModel.getTvById(args.itemId)
            }
        }
//        setupListeners()
    }

    private fun setupInfos(
        coverPath: String,
        backgroundImagePath: String,
        title: String,
        overview: String,
        type: Int
    ) {
        Glide.with(binding.coverImageView.context)
            .load(coverPath)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.coverImageView)

        Glide.with(this)
            .load(backgroundImagePath)
            .apply(bitmapTransform(BlurTransformation(18, 3)))
            .transition(DrawableTransitionOptions.withCrossFade(1500))
            .into(object : CustomTarget<Drawable?>() {
                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable?>?
                ) {
                    binding.blurBackgroundLayout.background = resource
                }

                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        binding.titleTextView.text = title
        binding.overviewTextView.text = overview
    }

    fun setupMovie(movie: Movie){
        movie.cover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + movie.cover
        movie.backdropCover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + movie.backdropCover
        setupInfos(
            movie.cover,
            movie.backdropCover,
            movie.title,
            movie.overview,
            args.type
        )
    }

    fun setupTv(tv: Tv){
        tv.cover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + tv.cover
        tv.backdropCover = BuildConfig.BUCKET_URL + ImageQualitySpec.LOW_QUALITY + tv.backdropCover
        setupInfos(
            tv.cover,
            tv.backdropCover,
            tv.title,
            tv.overview,
            args.type
        )
    }

    private fun setupListeners() {

    }
}