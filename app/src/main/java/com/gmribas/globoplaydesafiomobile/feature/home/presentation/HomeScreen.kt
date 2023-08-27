package com.gmribas.globoplaydesafiomobile.feature.home.presentation

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.domain.ObserveLifecycle
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.CustomTopAppBar
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.HorizontalCarousel
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.TextTitle
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)

//    val discoveryMoviesState = viewModel.viewState.collectAsStateWithLifecycle()

    val discoveryMoviesItems: LazyPagingItems<Movie> = viewModel.discoverMoviesFlow.collectAsLazyPagingItems()

    val discoverySoapOperasItems: LazyPagingItems<SoapOpera> = viewModel.discoverSoapOperasFlow.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { CustomTopAppBar() },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) { scaffoldPadding ->

        LazyColumn(contentPadding = scaffoldPadding) {

            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)) {
                    TextTitle(text = stringResource(id = R.string.soap_operas))
                }
            }

            item {
                HorizontalCarousel(pagingItems = discoverySoapOperasItems) { soapOperaId ->

                }
            }

            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)) {
                    TextTitle(text = stringResource(id = R.string.cinema))
                }
            }

            item {
                HorizontalCarousel(pagingItems = discoveryMoviesItems) { movieId ->

                }
            }
        }
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}