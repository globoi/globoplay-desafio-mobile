package com.nunkison.globoplaymobilechallenge.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel.*
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel.UiState.*
import com.nunkison.globoplaymobilechallenge.ui.ErrorLayout

import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    whenRequestingMovieDetails: (id: String) -> Unit
) {
    val vm: MoviesViewModel = koinViewModel<MoviesViewModelImpl>()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                Surface(shadowElevation = 4.dp) {
                    CenterAlignedTopAppBar(
                        modifier = Modifier.background(Color.Black),
                        colors = TopAppBarDefaults.mediumTopAppBarColors(
                            containerColor = Color.Black,
                        ),
                        title = {
                            Image(
                                painter = painterResource(id = R.drawable.logo_globoplay_white),
                                contentDescription = stringResource(id = R.string.app_name),
                                modifier = Modifier.height(100.dp)
                            )
                        },
                    )
                }
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    when (val state = vm.uiState.collectAsState().value){
                        is Success -> MoviesLayout(
                            data = state.data,
                            onMovieClick = whenRequestingMovieDetails
                        )
                        is Error -> ErrorLayout(message = state.message)
                    }
                    if (vm.loadingState.collectAsState().value){
                        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                    }
                }
            },
        )
    }
}