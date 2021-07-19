package com.com.globo.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.com.globo.R
import com.com.globo.details.helper.getNamesFormattedMovieDetails
import com.com.globo.details.model.MovieDetailsResponse
import com.com.globo.repository.model.Movie

class MovieDetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        val movie = requireActivity().intent
            .getParcelableExtra<Movie>(MovieDetailsActivity.EXTRA_MOVIE)

        val movieDetails = requireActivity().intent
            .getParcelableExtra<MovieDetailsResponse>(MovieDetailsActivity.EXTRA_MOVIE_DETAILS)

        movieDetails?.let {
            movie?.let {
                recyclerView.adapter = MovieDetailsAdapter(
                    getDetailsMovieToShow(movie, movieDetails)
                )
            }
        }
    }

    private fun getDetailsMovieToShow(
        movie: Movie,
        movieDetails: MovieDetailsResponse
    ): MutableMap<Int, String> {
        val mapData = mutableMapOf<Int, String>()
        movie.originalTitle?.let { mapData.put(R.string.original_title, it) }
        movie.popularity?.let { mapData.put(R.string.popularity, it.toString()) }
        movie.releaseDate?.let { mapData.put(R.string.release_date, it) }
        movie.rating?.let { mapData.put(R.string.avaliation, it.toString()) }
        getNamesFormattedMovieDetails(movieDetails.genres)?.let { mapData.put(R.string.genre, it) }
        getNamesFormattedMovieDetails(movieDetails.productionCountries)?.let {
            mapData.put(R.string.country, it)
        }
        return mapData
    }
}
