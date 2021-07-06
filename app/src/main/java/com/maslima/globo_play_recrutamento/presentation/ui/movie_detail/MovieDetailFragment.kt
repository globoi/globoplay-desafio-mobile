package com.maslima.globo_play_recrutamento.presentation.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.presentation.components.*
import com.maslima.globo_play_recrutamento.utils.loadDrawableImage
import com.maslima.globo_play_recrutamento.utils.loadPictures
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {

    @ExperimentalCoroutinesApi
    private val viewModel: MovieDetailViewModel by viewModels()

    @ExperimentalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getInt("movieId")?.let { movieId ->
            viewModel.onTriggerEvent(MovieEvent.GetMovieEvent(movieId))
        }
    }

    @ExperimentalCoroutinesApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val loading = viewModel.loading.value
                val movie = viewModel.movie.value
                val movieExists = viewModel.movieExists.value
                movie?.let {
                    ScreenDetail(
                        movie = it,
                        enableClick = { viewModel.onTriggerEvent(MovieEvent.AddMovieEvent) },
                        disableClick = { viewModel.onTriggerEvent(MovieEvent.DeleteMovieEvent) },
                        isAlreadyAdded = movieExists
                    )
                }
            }
        }
    }
}

@ExperimentalCoroutinesApi
@Composable
fun ScreenDetail(movie: Movie, enableClick: () -> Unit, disableClick: () -> Unit, isAlreadyAdded: Boolean) {
    Scaffold(
        topBar = {
            GeneralToolbar()
        },
        bodyContent = {
            MovieContent(movie, enableClick, disableClick)
        },
    )
}

@ExperimentalCoroutinesApi
@Composable
fun MovieContent(movie: Movie, enableClick: () -> Unit, disableClick: () -> Unit) {
    ScrollableColumn {
        ImageSection(movie)
        MovieDescriptionSection(movie)
        RowButtons(enableClick, disableClick)
        Spacer(modifier = Modifier.padding(10.dp))
        Row {
            Text(text = "Assista também", Modifier.clickable {

            }, style = MaterialTheme.typography.h6)
            Text(text = "Detalhes", style = MaterialTheme.typography.h6)
        }
    }
}