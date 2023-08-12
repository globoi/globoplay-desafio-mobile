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
import br.com.common.Extensions.toast
import br.com.ui_kit.R as uiKitR
import br.com.common.R as commonR
import br.com.details_movie.databinding.FragmentDetailsMovieBinding
import br.com.details_movie.domain.model.Movie
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsMovieFragment : Fragment() {

    private lateinit var binding: FragmentDetailsMovieBinding
    private val args: DetailsMovieFragmentArgs by navArgs()
    val viewModel: MovieViewModel by viewModels()




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
                uiState.movie?.let { movie ->
                    updateUi(movie)
                }
            }
        }

        collectLatestLifecycleFlow(viewModel.uiStateAddFavorite) { state ->

            when(state) {
                is UiState.Loading -> {
                    setLoadingFavorite()
                }

                is UiState.Error -> {
                    state.message?.let { setIsErrorFavorite(it) }
                }
                is  UiState.Success -> {
                    setIsFavorite()
                }
                else -> {}
            }
        }

        viewModel.isMovieSaved.observe(viewLifecycleOwner) {
                if(it) {
                    val color = ContextCompat.getColorStateList(requireContext(), uiKitR.color.primary_color)
                    binding.imageButtonUnFaveItem.setImageResource(uiKitR.drawable.ic_bold_fave)
                    binding.imageButtonUnFaveItem.imageTintList = color
                }
        }
    }

    private fun updateUi(movie: Movie) {
        with(binding) {
            txtTitulo.text = movie.title
            Glide.with(requireContext()).load(movie.posterUrl).centerCrop().into(imageViewMovie)
            txtSubtitle.text = movie.subtitle
            txtTagline.text = movie.tagline
            txtDetails.text = movie.description

            imageButtonUnFaveItem.setOnClickListener {
                if(viewModel.isMovieSaved.value != true)    {
                    viewModel.addMovieFavorite(movie)
                    viewModel.checkIfMovieIsSaved(movie.id)
                } else {
                    viewModel.removeFavorite(movie)
                    viewModel.checkIfMovieIsSaved(movie.id)
                }
            }
        }

    }

    private fun setLoadingFavorite() {
        binding.imageButtonUnFaveItem.visibility = View.GONE
        binding.progressLoading.visibility = View.VISIBLE

    }

    private fun setIsFavorite() {
        binding.imageButtonUnFaveItem.visibility = View.VISIBLE
        binding.progressLoading.visibility = View.GONE

        if(viewModel.isMovieSaved.value == true) {
            val color = ContextCompat.getColorStateList(requireContext(), uiKitR.color.primary_color)
            binding.imageButtonUnFaveItem.setImageResource(uiKitR.drawable.ic_bold_fave)
            binding.imageButtonUnFaveItem.imageTintList = color
        } else {
            binding.imageButtonUnFaveItem.setImageResource(uiKitR.drawable.ic_large_fave)
            binding.imageButtonUnFaveItem.imageTintList = null
        }
    }

    private fun setIsErrorFavorite(message: String) {
        binding.imageButtonUnFaveItem.visibility = View.VISIBLE
        binding.progressLoading.visibility = View.GONE
        toast(message)
    }
}