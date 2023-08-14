package br.com.details_movie.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import br.com.common.Extensions.collectLatestLifecycleFlow
import br.com.common.Extensions.hide
import br.com.common.Extensions.setFaveImageAndColor
import br.com.common.Extensions.show
import br.com.common.Extensions.toast
import br.com.ui_kit.R as uiKitR
import br.com.common.R as commonR
import br.com.details_movie.databinding.FragmentDetailsMovieBinding
import br.com.details_movie.domain.model.MovieDetails
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailsMovieBinding
    private val viewModel: MovieViewModel by viewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsMovieBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val idMovie = arguments?.getInt(getString(commonR.string.argument_movie_id), -1)

        idMovie?.let {
            viewModel.getMovie(it)
            viewModel.checkIfMovieIsSaved(it)
        }
        subscribeObservers()
    }

    private fun subscribeObservers() {
        collectLatestLifecycleFlow(viewModel.uiState) { uiState ->
            if (uiState is MovieUiState.Success) {
                updateUi(uiState.movieDetails)
            }
        }

        collectLatestLifecycleFlow(viewModel.uiStateAddFavorite) { state ->

            when(state) {
                is UiState.Loading -> {
                    setLoadingFavorite()
                }
                is  UiState.Success -> {
                    viewModel.movieDetails?.id?.let { viewModel.checkIfMovieIsSaved(it) }
                }
                is UiState.Error -> {
                    state.message?.let { setIsErrorFavorite(it) }
                }
                else -> {}
            }
        }

        viewModel.isMovieSaved.observe(viewLifecycleOwner) {
            setIsFavorite()
        }

    }

    private fun updateUi(movieDetails: MovieDetails) {
        with(binding) {
            txtTitulo.text = movieDetails.title
            Glide.with(requireContext()).load(movieDetails.posterPath).centerCrop().into(imageViewMovie)
            txtSubtitle.text = movieDetails.subtitle
            txtTagline.text = movieDetails.tagline
            txtDetails.text = movieDetails.overview

            imageButtonUnFaveItem.setOnClickListener {
                viewModel.isMovieSaved.value?.let { isAddOrRemove ->
                    viewModel.addMovieFavorite(movieDetails.id, isAddOrRemove)
                }
            }
        }

    }

    private fun setLoadingFavorite() {
        binding.imageButtonUnFaveItem.hide()
        binding.progressLoading.show()

    }

    private fun setIconFavorite() {
        binding.imageButtonUnFaveItem.setFaveImageAndColor(
            uiKitR.drawable.ic_bold_fave, uiKitR.color.primary_color)
    }

    private fun setNotIconFavorite() {

        binding.imageButtonUnFaveItem.setFaveImageAndColor(
            uiKitR.drawable.ic_large_fave,null)
    }

    private fun setIsFavorite() {
        binding.imageButtonUnFaveItem.show()
        binding.progressLoading.hide()

        if(viewModel.isMovieSaved.value == true) {
           setIconFavorite()
        } else {
            setNotIconFavorite()
        }
    }

    private fun setIsErrorFavorite(message: String) {
        binding.imageButtonUnFaveItem.show()
        binding.progressLoading.hide()
        toast(message)
    }
}