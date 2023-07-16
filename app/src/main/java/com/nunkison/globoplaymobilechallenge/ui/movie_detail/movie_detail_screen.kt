package com.nunkison.globoplaymobilechallenge.ui.movie_detail

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.project.structure.MovieDetailViewModel
import com.nunkison.globoplaymobilechallenge.project.structure.MovieDetailViewModel.UiState.*
import com.nunkison.globoplaymobilechallenge.ui.ErrorLayout
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetailScreen(
    movieId: String,
    whenRequestingMovieDetails: (id: String) -> Unit,
    whenRequestBack: () -> Unit
) {
    val vm: MovieDetailViewModel = koinViewModel<MovieDetailViewModelImpl> {
        parametersOf(movieId)
    }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (vm.loadingState.collectAsState().value) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }
            when (val state = vm.uiState.collectAsState().value) {
                is Success -> {
                    state.data?.let {
                        MovieDetailLayout(
                            data = state.data,
                            onMovieClick = whenRequestingMovieDetails
                        )
                    }
                }

                is Error -> ErrorLayout(message = state.message)
            }
            CenterAlignedTopAppBar(
                modifier = Modifier.background(Color.Transparent),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent,
                ),
                title = {},
                navigationIcon = {
                    IconButton(onClick = {
                        whenRequestBack()
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Navigation icon",
                            tint = Color.White
                        )
                    }
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MovieDetailScreenPreview1() {
    GloboplayMobileChallengeTheme {
        MovieDetailScreen("", {}, {})
    }
}