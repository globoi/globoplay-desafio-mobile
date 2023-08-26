package com.gmribas.globoplaydesafiomobile.feature.home.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.presentation.ObserveLifecycle
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.CircularLoadingCenter
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.DialogLoadingError
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.TextTitle
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)

    val discoveryMoviesState = viewModel.viewState.collectAsStateWithLifecycle()

    val discoveryMoviesItems: LazyPagingItems<Movie> = viewModel.discoverMoviesFlow.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { scaffoldPadding ->

        LazyColumn(contentPadding = scaffoldPadding) {
            item {
                Row(modifier = Modifier.fillMaxWidth()) {
                    TextTitle(text = stringResource(id = R.string.soap_operas))
                }
            }

            item {
                Divider(modifier = Modifier.padding(vertical = 8.dp))
            }


            item {
                when (discoveryMoviesState.value) {
                    is UiState.Loading -> {
                        CircularLoadingCenter()
                    }
                    is UiState.Error -> DialogLoadingError(
                        errorPlace = stringResource(id = R.string.load_soap_operas_error),
                        errorMessage = (discoveryMoviesState.value as UiState.Error).error
                    ) {
                        viewModel.dismissErrorDialog()
                    }

                    is UiState.Default -> {}
                    else -> {}
                }

                LazyRow {
                    items(count = discoveryMoviesItems.itemCount) { index ->
                        discoveryMoviesItems[index]?.let { movie ->
                            HomeMovieItem(id = movie.id, title = movie.originalTitle, poster = movie.posterPath) {

                            }
                        }
                    }
                }
            }
        }
    }
}