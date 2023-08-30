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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.constants.Constants.CAROUSEL_HOME_TOTAL_ITEMS_TO_SHOW
import com.gmribas.globoplaydesafiomobile.core.domain.ObserveLifecycle
import com.gmribas.globoplaydesafiomobile.core.presentation.navigation.Screens
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.CustomTopAppBar
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.HorizontalAnimatedCarousel
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.HorizontalCarousel
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.TextTitle
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.SoapOpera
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.bottombar.BottomNavItem
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.bottombar.BottomNavigation
import org.koin.androidx.compose.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    viewModel: HomeScreenViewModel = koinViewModel()
) {
    viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)

//    val discoveryMoviesState = viewModel.viewState.collectAsStateWithLifecycle()

    val topRatedTvShows: LazyPagingItems<SoapOpera> = viewModel.topRatedTvShowsFlow.collectAsLazyPagingItems()

    val discoverySoapOperasItems: LazyPagingItems<SoapOpera> = viewModel.discoverSoapOperasFlow.collectAsLazyPagingItems()

    val discoveryMoviesItems: LazyPagingItems<Movie> = viewModel.discoverMoviesFlow.collectAsLazyPagingItems()

    var selectedScreen by remember { mutableStateOf<BottomNavItem>(BottomNavItem.Home) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = { CustomTopAppBar() },
        bottomBar = {
            BottomNavigation(selectedScreen) {
                selectedScreen = it
            }
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) { scaffoldPadding ->

        LazyColumn(contentPadding = scaffoldPadding) {
            item {
                Spacer(modifier = Modifier.height(8.dp))
            }

            item {
                HorizontalAnimatedCarousel(
                    list = topRatedTvShows,
                    totalItemsToShow = CAROUSEL_HOME_TOTAL_ITEMS_TO_SHOW
                ) { topRatedTVId ->

                }
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
            }

            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)) {
                    TextTitle(text = stringResource(id = R.string.soap_operas))
                }
            }

            item {
                HorizontalCarousel(
                    modifier = Modifier.padding(start = 8.dp),
                    pagingItems = discoverySoapOperasItems) { id ->
                    navController.navigate(Screens.Details.route + "/${id}/${true}")
                }
            }

            item {
                Spacer(modifier = Modifier.height(12.dp))
            }

            item {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)) {
                    TextTitle(text = stringResource(id = R.string.cinema))
                }
            }

            item {
                HorizontalCarousel(
                    modifier = Modifier.padding(start = 8.dp),
                    pagingItems = discoveryMoviesItems
                ) { movieId ->
                    navController.navigate(Screens.Details.route + "/${movieId}/${false}")
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