package com.example.globechallenge.ui.details.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.globechallenge.data.model.features.details.MovieCast
import com.example.globechallenge.data.model.features.details.MovieDetails
import com.example.globechallenge.databinding.FragmentDetailsBinding
import com.example.globechallenge.helper.concatCast
import com.example.globechallenge.helper.concatGenre

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
        binding.txtReleaseDate.text = movie.releaseDate
        binding.txtCountryName.text = movie.countryName
    }

    fun setMovieCast(movieCast: MovieCast) {
        binding.txtCast.text = movieCast.cast.concatCast()
    }
}