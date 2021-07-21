package com.example.globechallenge.ui.details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.globechallenge.data.model.models.details.MovieCast
import com.example.globechallenge.data.model.models.details.MovieDetails
import com.example.globechallenge.databinding.FragmentDetailsBinding
import com.example.globechallenge.utils.concatCast
import com.example.globechallenge.utils.concatGenre

class DetailsFragment : Fragment() {

    private lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    companion object {
        const val TITLE_DETAIL = "Detalhes"
    }

    fun setMovie(movie: MovieDetails) {
        binding.txtOriginalTitle.text = movie.title
        binding.txtGenre.text = movie.genres.concatGenre()
        binding.txtRuntime.text = movie.runtime.toString()
        binding.txtReleaseDate.text = movie.year
        binding.txtCountryName.text = movie.countryName
    }

    fun setMovieCast(movieCast: MovieCast) {
        binding.txtCast.text = movieCast.cast.concatCast()
    }
}