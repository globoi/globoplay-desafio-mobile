package com.ftoniolo.globoplay.presentation.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.databinding.FragmentDetailsBinding
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader
import com.ftoniolo.globoplay.presentation.extensions.showShortToast
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailsFilmFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding: FragmentDetailsBinding get() = _binding!!

    private val args by navArgs<DetailsFilmFragmentArgs>()

    private val viewModel: DetailsFilmViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentDetailsBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setView()
        setSharedElementTransitionOnEnter()
        setAndObserveFavoriteUiState(args.detailsFilmViewArgs)
        setAndObserveTrailerUiState(args.detailsFilmViewArgs)
        setupTabViews()
    }

    private fun setAndObserveTrailerUiState(detailsFilmViewArgs: DetailsFilmViewArg) {
        viewModel.trailer.run {
            binding.buttonTrailer.setOnClickListener {
                load(detailsFilmViewArgs.id)
            }
            state.observe(viewLifecycleOwner) { uiState ->
                when(uiState) {
                    is TrailerUiActionStateLiveData.UiState.Success -> {
                        val intent = Intent( Intent.ACTION_VIEW, Uri.parse(uiState.trailerList[0].trailerUrl))
                        intent.putExtra("force_fullscreen",true);
                        startActivity(intent);
                    }
                    TrailerUiActionStateLiveData.UiState.Error -> {
                        Toast.makeText(context, R.string.error_trailer_unavailable, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

    private fun setView(){
        val filmViewArgs = args.detailsFilmViewArgs
        binding.itemPoster.run {
            transitionName = filmViewArgs.title
            imageLoader.load(
                this, filmViewArgs.imageUrl
            )
        }
        binding.imagePosterBlur.run {
            imageLoader.loadWithBlur(
                this, filmViewArgs.imageUrl
            )
        }

        binding.textNameFilm.text = filmViewArgs.title
    }

    private fun setAndObserveFavoriteUiState(detailsFilmViewArg: DetailsFilmViewArg){
        viewModel.favorite.run {

            checkFavorite(detailsFilmViewArg.id)

            binding.buttonFavorite.setOnClickListener {
                update(
                    DetailsFilmViewArg(
                        id = detailsFilmViewArg.id,
                        overview = detailsFilmViewArg.overview,
                        title = detailsFilmViewArg.title,
                        imageUrl = detailsFilmViewArg.imageUrl,
                        releaseDate = detailsFilmViewArg.releaseDate
                    )
                )
            }

            state.observe(viewLifecycleOwner) { uiState ->
                binding.flipperFavorite.displayedChild = when (uiState) {
                    FavoriteUiActionStateLiveData.UiState.Loading ->
                        FLIPPER_FAVORITE_CHILD_POSITION_LOADING
                    is FavoriteUiActionStateLiveData.UiState.Button -> {
                        binding.buttonFavorite.run {
                            text = uiState.buttonDescription
                            setCompoundDrawablesWithIntrinsicBounds(
                                uiState.icon, NOT_ICON ,NOT_ICON, NOT_ICON)
                            FLIPPER_FAVORITE_CHILD_POSITION_IMAGE
                        }
                    }
                    is FavoriteUiActionStateLiveData.UiState.Error -> {
                        showShortToast(uiState.messageResId)
                        FLIPPER_FAVORITE_CHILD_POSITION_IMAGE
                    }
                }
            }

        }
    }

    private fun setSharedElementTransitionOnEnter() {
        TransitionInflater.from(requireContext())
            .inflateTransition(android.R.transition.move).apply {
                sharedElementEnterTransition = this
            }
    }

    private fun setupTabViews(){
        val tabLayout = binding.tabDetails
        val viewpager = binding.addViewpager
        val tabs = listOf(R.string.details, R.string.watch_too)

        val tabViewPagerArgs = TabViewPagerArgs(
            overview = args.detailsFilmViewArgs.overview,
            title = args.detailsFilmViewArgs.title,
            releaseDate = args.detailsFilmViewArgs.releaseDate,
            id = args.detailsFilmViewArgs.id
        )
        val adapter = TabViewPagerAdapter(this, tabs, tabViewPagerArgs)
            viewpager.adapter = adapter

        TabLayoutMediator(tabLayout, viewpager){ tab, position ->
            tab.text = getString(tabs[position])
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val FLIPPER_FAVORITE_CHILD_POSITION_IMAGE = 0
        private const val FLIPPER_FAVORITE_CHILD_POSITION_LOADING = 1
        private const val NOT_ICON = 0
    }
}