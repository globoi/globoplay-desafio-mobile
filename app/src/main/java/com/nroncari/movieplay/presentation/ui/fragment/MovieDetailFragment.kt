package com.nroncari.movieplay.presentation.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.tabs.TabLayoutMediator
import com.nroncari.movieplay.databinding.FragmentMovieDetailBinding
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import com.nroncari.movieplay.presentation.ui.adapter.TabsAdapter
import com.nroncari.movieplay.presentation.viewmodel.MovieDetailViewModel
import com.nroncari.movieplay.presentation.viewmodel.StateAppComponentsViewModel
import com.nroncari.movieplay.presentation.viewmodel.VisualComponents
import com.nroncari.movieplay.utils.loadWallpaper
import jp.wasabeef.glide.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class MovieDetailFragment : Fragment() {

    private var _binding: FragmentMovieDetailBinding? = null
    private val appComponentsViewModel: StateAppComponentsViewModel by sharedViewModel()
    private val binding get() = _binding!!
    private val args by navArgs<MovieDetailFragmentArgs>()
    private val movieId by lazy { args.movieId }
    private val viewModel: MovieDetailViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        appComponentsViewModel.havComponent = VisualComponents(false)

        listeners()
        initTabLayout(view)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun listeners() {
        viewModel.getMovieDetail(movieId)
        viewModel.getMovieDataVideo(movieId)

        viewModel.movie.observe(viewLifecycleOwner) { loadMovieFields(it) }
        binding.backButton.setOnClickListener { findNavController().popBackStack() }
        binding.watch.setOnClickListener {
            viewModel.dataMovieVideo.value?.let { movieDataVideo ->
                goToYouTubePlayerActivity(movieDataVideo.path)
            }
        }
    }

    private fun goToYouTubePlayerActivity(path: String) {
        val direction =
            MovieDetailFragmentDirections.actionMovieDetailFragmentToYoutubePlayerActivity(path)
        findNavController().navigate(direction)
    }

    private fun loadMovieFields(movie: MovieDetailPresentation) {
        binding.movieTitle.text = movie.title
        binding.movieDescription.text = "${movie.overview}\n"
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

    private fun initTabLayout(view: View) {
        val tabsAdapter = TabsAdapter(requireActivity())
        val viewPager by lazy {
            view.findViewById<ViewPager2>(com.nroncari.movieplay.R.id.fragment_movie_detail_view_pager)
        }

        viewPager.adapter = tabsAdapter
        viewPager.isSaveEnabled = false
        TabLayoutMediator(binding.fragmentMovieDetailTabLayout, viewPager) { tab, position ->
            val tabTitles = arrayOf("Assista também", "Detalhes")
            tab.text = tabTitles[position]
        }.attach()
    }
}