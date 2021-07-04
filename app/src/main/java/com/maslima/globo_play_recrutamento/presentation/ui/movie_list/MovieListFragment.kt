package com.maslima.globo_play_recrutamento.presentation.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maslima.globo_play_recrutamento.presentation.components.MovieCard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {

    val viewModel: MovieListViewModel by viewModels()
    @ExperimentalFoundationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val movies = viewModel.movies.value
                LazyVerticalGrid(cells = GridCells.Fixed(3)) {
                    itemsIndexed(items = movies) { _, movie ->
                        val url = "https://image.tmdb.org/t/p/w154".plus(movie.posterPath)
                        MovieCard(movieUrlImage = url, onClickCard = {})
                    }
                }
            }
        }
    }

}