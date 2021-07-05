package com.maslima.globo_play_recrutamento.presentation.components

import android.os.Bundle
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.PAGE_SIZE

@ExperimentalFoundationApi
@Composable
fun MovieListComponent(
    movies: List<Movie>,
    page: Int,
    loading: Boolean,
    onChangeMovieScrollPosition: (Int) -> Unit,
    onTriggerEvent: () -> Unit,
    navController: NavController
) {
    MovieList(movies, onChangeMovieScrollPosition, page, loading, onTriggerEvent, navController)
    CircularIndeterminateProgressBar(isDisplayed = loading, verticalBias = 0.3f)
}

@ExperimentalFoundationApi
@Composable
private fun MovieList(
    movies: List<Movie>,
    onChangeMovieScrollPosition: (Int) -> Unit,
    page: Int,
    loading: Boolean,
    onTriggerEvent: () -> Unit,
    navController: NavController
) {
    if (movies.isNotEmpty()) {
        LazyVerticalGrid(cells = GridCells.Fixed(3)) {
            itemsIndexed(items = movies) { index, movie ->
                onChangeMovieScrollPosition(index)
                if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                    onTriggerEvent()
                }
                val url = "https://image.tmdb.org/t/p/w154".plus(movie.posterPath)
                MovieCard(movieUrlImage = url, onClickCard = {
                    val bundle = Bundle()
                    bundle.putInt("movieId", movie.id)
                    navController.navigate(R.id.listToDetail, bundle)
                })
            }
        }
    } else {
        Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center) {
            Text(
                text = "Não foram encontrados dados!",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}