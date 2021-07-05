package com.maslima.globo_play_recrutamento.presentation.ui.movie_detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.ScrollableColumn
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
import com.maslima.globo_play_recrutamento.presentation.components.MovieEvent
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
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val loading = viewModel.loading.value
                val movie = viewModel.movie.value
                movie?.let {
                    ScreenDetail(movie = it)
                }
            }
        }
    }
}

@Composable
fun ScreenDetail(movie: Movie) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "globoplay",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                },
                elevation = Dp(7f),
            )
        },
        bodyContent = {
            MovieContent(movie)
        },
    )
}

@Composable
fun MovieContent(movie: Movie) {
    ScrollableColumn {
        ImageSection(movie)
        MovieDescriptionSection(movie)
        RowButtons()
    }
}

@Composable
private fun ImageSection(movie: Movie) {
    val bitmap = loadPictures(
        url = "https://image.tmdb.org/t/p/w500".plus(movie.posterPath),
        defaultImage = R.drawable.no_image_avaiable
    )
    bitmap.value?.let {
        Image(
            bitmap = it.asImageBitmap(),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentWidth()
                .wrapContentHeight(),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun MovieDescriptionSection(movie: Movie) {
    Text(
        text = movie.title,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Text(
        text = movie.originalTitle,
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Text(
        text = movie.overview,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun RowButtons() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .padding(bottom = 40.dp)
                .preferredHeight(40.dp)
                .wrapContentWidth()
        ) {
            val img = loadDrawableImage(defaultImage = R.drawable.ic_play_arrow)
            img.value?.let {
                Image(bitmap = it.asImageBitmap())
            }
            Text(text = "Assista")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .padding(bottom = 40.dp)
                .preferredHeight(40.dp)
                .wrapContentWidth()
        ) {
            val img = loadDrawableImage(defaultImage = R.drawable.ic_star_rate)
            img.value?.let {
                Image(bitmap = it.asImageBitmap())
            }
            Text(text = "Favoritos")
        }
    }
}