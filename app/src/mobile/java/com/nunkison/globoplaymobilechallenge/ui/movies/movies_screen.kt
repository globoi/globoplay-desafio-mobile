package com.nunkison.globoplaymobilechallenge.ui.movies

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.nunkison.globoplaymobilechallenge.R
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel.*
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel.UiState.*
import com.nunkison.globoplaymobilechallenge.tryRequestFocus

import com.nunkison.globoplaymobilechallenge.ui.components.EmptyLayout
import com.nunkison.globoplaymobilechallenge.ui.components.ErrorLayout
import com.nunkison.globoplaymobilechallenge.viewmodel.MoviesViewModelImpl

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import java.net.UnknownHostException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    whenRequestingMovieDetails: (id: String) -> Unit
) {
    val vm: MoviesViewModel = koinViewModel<MoviesViewModelImpl>()
    val state = vm.uiState.collectAsState().value
    val favoritesFilterEnable = state is Success && state.successState.favoriteFilterEnable
    val focusRequester = remember { FocusRequester() }
    val coroutineScope = rememberCoroutineScope()
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
                            containerClolor = Color.Black,
                        ),
                        title = {
                            if (vm.searchModeEnable.collectAsState().value) {
                                TextField(
                                    modifier = Modifier.focusRequester(focusRequester),
                                    value = vm.searchQuery.collectAsState().value,
                                    onValueChange = {
                                        vm.searchQuery.value = it
                                        vm.loadMoviesDelayed()
                                    },
                                    singleLine = true,
                                    colors = TextFieldDefaults.textFieldColors(
                                        textColor = Color.LightGray,
                                        disabledTextColor = Color.Transparent,
                                        containerColor = Color.Transparent,
                                        focusedIndicatorColor = Color.Transparent,
                                        unfocusedIndicatorColor = Color.Transparent,
                                        disabledIndicatorColor = Color.Transparent,
                                        cursorColor = Color.LightGray,
                                    ),
                                )
                            } else {
                                Image(
                                    painter = painterResource(id = R.drawable.logo_globoplay_white),
                                    contentDescription = stringResource(id = R.string.app_name),
                                    modifier = Modifier.height(100.dp)
                                )
                            }
                        },
                        actions = {
                            IconButton(onClick = {
                                vm.toogleFilterByFavorites()
                            }) {
                                Icon(
                                    painter = if (favoritesFilterEnable) {
                                        painterResource(R.drawable.baseline_star_24)
                                    } else {
                                        painterResource(R.drawable.baseline_star_border_24)
                                    },
                                    contentDescription = stringResource(
                                        id = R.string.favorites_filter
                                    ),
                                    tint = Color.White
                                )
                            }
                        },
                        navigationIcon = {
                            IconButton(onClick = {
                                vm.searchModeEnable.value = !vm.searchModeEnable.value
                                if (vm.searchModeEnable.value) {
                                    coroutineScope.launch {
                                        delay(300)
                                        focusRequester.tryRequestFocus()
                                    }
                                } else {
                                    vm.searchQuery.value = ""
                                    vm.loadMovies()
                                }
                            }) {
                                Icon(
                                    imageVector = if (vm.searchModeEnable.collectAsState().value) {
                                        Icons.Default.Close
                                    } else {
                                        Icons.Default.Search
                                    },
                                    contentDescription = stringResource(
                                        id = R.string.search
                                    ),
                                    tint = Color.White
                                )
                            }
                        }
                    )
                }
            },
            content = { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)
                ) {
                    when (state) {
                        is Success -> {
                            MoviesLayout(
                                data = state.successState.data,
                                onMovieClick = whenRequestingMovieDetails
                            )
                        }

                        is Error -> {
                            ErrorLayout(
                                message = when (state.exception) {
                                    is UnknownHostException -> stringResource(id = R.string.no_internet_error)
                                    else -> state.message
                                }, onRetry = {
                                    vm.loadMovies()
                                }
                            )
                        }

                        is Empty -> {
                            EmptyLayout()
                        }
                    }
                    if (vm.loadingState.collectAsState().value) {
                        LinearProgressIndicator(
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
        )
    }
}