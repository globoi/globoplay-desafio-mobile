package com.mazer.globoplayapp.presentation.ui.details.tabs.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.FragmentDetailsBinding
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.utils.AppConstants
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Fragmento que conterá a view da aba "Detalhes" dentro da MovieDetailsActivity
 */
class DetailsFragment : Fragment() {

    private val viewModel : DetailsViewModel by viewModel()
    private lateinit var binding: FragmentDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.setExtras(it)
        }
    }

    private fun registerObservers() {
        viewModel.movieDetails.observe(viewLifecycleOwner, Observer {
                setupView(it)
        })
    }

    private fun setupView(movie: Movie){
        val title = movie.originalTitle
        val movieString = getString(R.string.label_title, title)
        binding.tvTitle.text = HtmlCompat.fromHtml(movieString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val genre = movie.genre
        val genreString = getString(R.string.label_genre, genre)
        binding.tvGenre.text = HtmlCompat.fromHtml(genreString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val releaseDate = movie.releaseDate
        val episodesString = getString(R.string.label_created_at, releaseDate)
        binding.tvCreatedAt.text = HtmlCompat.fromHtml(episodesString, HtmlCompat.FROM_HTML_MODE_LEGACY)

        val originalLanguage = movie.originalLanguage.replaceFirstChar { it.uppercase() }
        val originalLanguageString = getString(R.string.label_country, originalLanguage)
        binding.tvCountry.text = HtmlCompat.fromHtml(originalLanguageString, HtmlCompat.FROM_HTML_MODE_LEGACY)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        registerObservers()
    }

    companion object {
        @JvmStatic
        fun newInstance(movie: Movie) =
            DetailsFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(AppConstants.DETAILS_EXTRA, movie)
                }
            }
    }
}