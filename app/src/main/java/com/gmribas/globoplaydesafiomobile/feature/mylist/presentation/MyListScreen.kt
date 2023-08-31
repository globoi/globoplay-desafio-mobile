package com.gmribas.globoplaydesafiomobile.feature.mylist.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.paging.PagingData
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.domain.model.PosterItemInterface
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.core.presentation.navigation.Screens
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.TextTitle
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.VerticalGrid
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.bottombar.BottomNavItem
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.bottombar.BottomNavigation
import kotlinx.coroutines.flow.flow
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyListScreen(navController: NavController, viewModel: MyListViewModel = koinViewModel()) {

    val mediaListSate = viewModel.mediaList.collectAsState()

    val flow = flow {

        val toEmitValue = when (mediaListSate.value) {
            is UiState.Default -> PagingData.empty()
            is UiState.Error -> throw IllegalStateException((mediaListSate.value as UiState.Error).error)
            is UiState.Loading -> PagingData.empty()
            is UiState.Success -> PagingData.from<PosterItemInterface>((mediaListSate.value as UiState.Success).data)
        }

        emit(toEmitValue)
    }.collectAsLazyPagingItems()

    var selectedScreen by remember { mutableStateOf<BottomNavItem>(BottomNavItem.MyList) }

    val myListScreenRoute = BottomNavItem.MyList.screenRoute.route

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    TextTitle(text = stringResource(id = R.string.home_bottom_bar_my_list_label))
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary)
            )
        },
        bottomBar = {
            BottomNavigation(selectedScreen) { clickedBottomNavItem ->
                if (clickedBottomNavItem.screenRoute.route != myListScreenRoute) {
                    selectedScreen = clickedBottomNavItem
                    navController.navigate(selectedScreen.screenRoute.route)
                }
            }
        },
        containerColor = MaterialTheme.colorScheme.primaryContainer
    ) { padding ->
        VerticalGrid(
            modifier = Modifier.padding(padding).fillMaxSize(),
            list = flow,
            emptyListMessage = R.string.my_list_screen_empty_list
        ) { id, isTvShow ->
            navController.navigate(route = Screens.Details.route + "/${id}/${isTvShow}")
        }
    }
}