package com.gmribas.globoplaydesafiomobile.feature.details.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.gmribas.globoplaydesafiomobile.core.asyncPainter
import com.gmribas.globoplaydesafiomobile.core.domain.ObserveLifecycle
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.CircularLoadingCenter
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.DialogLoadingError
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.PosterItem
import com.gmribas.globoplaydesafiomobile.feature.details.domain.model.MovieDetails
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    viewModel: DetailsScreenViewModel = koinViewModel()
    ) {

    viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)

    val state = viewModel.viewState.collectAsStateWithLifecycle()

    Column {
        TopAppBar(
            title = {},
            navigationIcon = { Icons.Filled.ArrowBack },
            modifier = Modifier.fillMaxWidth(),
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent)
        )

        when (state.value) {
            UiState.Default -> {}
            is UiState.Error -> DialogLoadingError(errorPlace = "", errorMessage = "") {

            }
            UiState.Loading -> CircularLoadingCenter()
            is UiState.Success -> BuildDetailsBody(movie = (state.value as UiState.Success).data)
        }
    }
}

@Composable
private fun BuildDetailsBody(movie: MovieDetails) {
    Image(
        painter = asyncPainter(movie.posterPath),
        contentDescription = movie.originalTitle,
        modifier = Modifier.blur(radius = 15.dp)
    )

    PosterItem(id = movie.id, title = movie.originalTitle, poster = movie.posterPath, onClick = {})
}

@Preview
@Composable
fun DetailsScreenPreview() {
}