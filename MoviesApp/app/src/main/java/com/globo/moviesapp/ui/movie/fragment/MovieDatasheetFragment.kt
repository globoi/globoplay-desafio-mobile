package com.globo.moviesapp.ui.movie.fragment

import android.graphics.Typeface
import android.os.Bundle
import android.text.SpannableString
import android.text.style.StyleSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.globo.moviesapp.R
import com.globo.moviesapp.model.movie.Movie
import com.globo.moviesapp.ui.movie.viewmodel.MovieViewModel
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_movie_datasheet.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDatasheetFragment(): DaggerFragment() {
    companion object {
        var movieId: Int? = null
        fun newInstance(movie: Movie) : MovieDatasheetFragment {
            this.movieId = movie.id
            return MovieDatasheetFragment()
        }
    }

    lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_datasheet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
    }

    private fun setupViewModel(){
        movieViewModel = ViewModelProvider(requireActivity()).get(MovieViewModel::class.java)

        movieViewModel.movie.observe(viewLifecycleOwner){
            initView(it)
        }

        CoroutineScope(Dispatchers.IO).launch {
            movieViewModel.openMovie(movieId!!)
        }
    }

    private fun initView(movie: Movie){
        val lblTitleOriginal = getString(R.string.tv_movie_title_original)
        val spannableTitleOriginal = SpannableString("$lblTitleOriginal: ${movie.name}")
        spannableTitleOriginal.setSpan(StyleSpan(Typeface.BOLD), 0, lblTitleOriginal.length, 0)
        tvTitleOriginal.text = spannableTitleOriginal

        val genresStr = movie.genres?.map { it.name }?.reduce{
                genreStr, value -> "$genreStr, $value"
        }
        val lblGenre = getString(R.string.tv_movie_genre)
        val spannableGenre = SpannableString("$lblGenre: $genresStr")
        spannableGenre.setSpan(StyleSpan(Typeface.BOLD), 0, lblGenre.length, 0)
        tvGenre.text = spannableGenre

        val lblEpisodes = getString(R.string.tv_movie_episodes)
        val spannableEpisodes = SpannableString("${lblEpisodes}: ${movie.number_of_episodes}")
        spannableEpisodes.setSpan(StyleSpan(Typeface.BOLD), 0, lblEpisodes.length, 0)
        tvEpisode.text = spannableEpisodes

        val lblYearProdution = getString(R.string.tv_movie_year_prodution)
        val yearStr = movie.first_air_date
        val spannableYearProdution = SpannableString("${lblYearProdution}: ${yearStr?.substring(0, 4)}")
        spannableYearProdution.setSpan(StyleSpan(Typeface.BOLD), 0, lblYearProdution.length, 0)
        tvYearProdution.text = spannableYearProdution

        val lblCountry = getString(R.string.tv_movie_country)
        val countryStr = movie.production_countries?.map { it.name }?.reduce{
                countryStr, value -> "$countryStr, $value"
        }
        val spannableCountry = SpannableString("${lblCountry}: $countryStr")
        spannableCountry.setSpan(StyleSpan(Typeface.BOLD), 0, lblCountry.length, 0)
        tvCountry.text = spannableCountry

        val lblDirector = getString(R.string.tv_movie_director)
        val crews = movie.aggregateCredits?.crew?.filter{ it.department == "Directing" }
        val crewStr = if(!crews.isNullOrEmpty()) crews.get(0).name else ""
        val spannableDirector = SpannableString("${lblDirector}: $crewStr")
        spannableDirector.setSpan(StyleSpan(Typeface.BOLD), 0, lblDirector.length, 0)
        tvDirector.text = spannableDirector

        val lblCast = getString(R.string.tv_movie_cast)
        val casts = movie.aggregateCredits?.cast?.filter { it.order < 10 }
        val castStr = if(!casts.isNullOrEmpty()) casts.map{ it.name }.reduce{
                castStr, value -> "$castStr, ${value}"
        } else ""
        val spannableCast = SpannableString("${lblCast}: $castStr")
        spannableCast.setSpan(StyleSpan(Typeface.BOLD), 0, lblCast.length, 0)
        tvCast.text = spannableCast
    }
}