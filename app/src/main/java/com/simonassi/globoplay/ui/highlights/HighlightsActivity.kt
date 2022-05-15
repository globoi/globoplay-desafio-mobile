package com.simonassi.globoplay.ui.highlights

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.simonassi.globoplay.BuildConfig
import com.simonassi.globoplay.R
import com.simonassi.globoplay.data.favorite.entity.Favorite
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.tv.Tv
import com.simonassi.globoplay.databinding.ActivityHighlightsBinding
import com.simonassi.globoplay.utilities.contants.ImageQualitySpec
import com.simonassi.globoplay.utilities.contants.ItemType
import com.simonassi.globoplay.viewmodels.HighlightsViewModel
import dagger.hilt.android.AndroidEntryPoint
import jp.wasabeef.glide.transformations.BlurTransformation

@AndroidEntryPoint
class HighlightsActivity : AppCompatActivity() {

    val args: HighlightsActivityArgs by navArgs()
    private val highlighthsViewModel: HighlightsViewModel by viewModels()
    private lateinit var movie: Movie
    private lateinit var tv: Tv
    private lateinit var binding: ActivityHighlightsBinding
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHighlightsBinding.inflate(layoutInflater)
        val view = binding.root
        supportActionBar?.hide()
        setContentView(view)

        highlighthsViewModel.favoriteSearchLiveData.observe(this, Observer { favorite ->
            setupFavorite(favorite)
        })
        highlighthsViewModel.getFavoriteById(args.itemId)

        when (args.type) {
            ItemType.MOVIE -> {
                highlighthsViewModel.movieSearchLiveData.observe(this, Observer { fetchedMovie ->
                    movie = fetchedMovie
                    setupMovie(fetchedMovie)
                })
                highlighthsViewModel.getMovieById(args.itemId)
            }
            ItemType.TV -> {
                highlighthsViewModel.tvSearchLiveData.observe(this, Observer { fetchedTv ->
                    tv = fetchedTv
                    setupTv(fetchedTv)
                })
                highlighthsViewModel.getTvById(args.itemId)
            }
        }
        setupListeners()
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

    fun setupFavorite(favorite: Favorite?){
        if(favorite == null){
            isFavorite = false
            binding.favoriteTextView.text = this.getText(R.string.my_list)
            Glide.with(binding.favoriteIconImageView.context)
                .load(R.drawable.ic_favorite)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.favoriteIconImageView)
        }else{
            isFavorite = true
            binding.favoriteTextView.text = this.getText(R.string.added_into_my_list)
            Glide.with(binding.favoriteIconImageView.context)
                .load(R.drawable.ic_check)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.favoriteIconImageView)
        }
    }

    private fun setupListeners() {
        binding.favoriteButton.setOnClickListener(View.OnClickListener {
            if(!isFavorite){
                var newFavorite: Favorite?
                when(args.type){
                    ItemType.TV -> {
                        newFavorite = Favorite(args.itemId, tv.title, args.type, tv.cover)
                    }
                    else -> {
                        newFavorite = Favorite(args.itemId, movie.title, args.type, movie.cover)
                    }
                }
                highlighthsViewModel.saveFavorite(newFavorite)
                isFavorite = true
                setupFavorite(newFavorite)
            }else{
                highlighthsViewModel.deleteFavoriteById(args.itemId)
                isFavorite = false
                setupFavorite(null)
            }
        })

        binding.showContentButton.setOnClickListener(View.OnClickListener {
            Log.d("TOuch", "aoba")
        })

        binding.goBackButton.setOnClickListener(View.OnClickListener {
            finish()
        })
    }


}