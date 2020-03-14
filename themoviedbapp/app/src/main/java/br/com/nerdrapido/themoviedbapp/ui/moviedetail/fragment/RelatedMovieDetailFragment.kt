package br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.nerdrapido.themoviedbapp.R

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class RelatedMovieDetailFragment : MovieDetailFragment() {

    override fun getTitle(): String {
        return context?.getString(R.string.movie_detail_related_fragment_title) ?: "aaaa"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val newVIew = inflater.inflate(R.layout.fragment_related_movies, null)
        return newVIew
    }

}