package com.gmribas.globoplaydesafiomobile.feature.details.presentation

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.asyncPainter
import com.gmribas.globoplaydesafiomobile.core.domain.ObserveLifecycle
import com.gmribas.globoplaydesafiomobile.core.domain.model.DetailsInterface
import com.gmribas.globoplaydesafiomobile.core.domain.model.MediaDetails
import com.gmribas.globoplaydesafiomobile.core.domain.model.SimilarInterface
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.core.presentation.navigation.Screens
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.CircularLoadingCenter
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.DialogLoadingError
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.IconAndTextButton
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.PosterItem
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.TextTitle
import com.gmribas.globoplaydesafiomobile.core.presentation.widgets.VerticalGrid
import com.gmribas.globoplaydesafiomobile.ui.theme.topAppBarBackground
import org.koin.androidx.compose.koinViewModel

@Composable
fun DetailsScreen(
    navController: NavHostController,
    viewModel: DetailsScreenViewModel = koinViewModel()
) {

    viewModel.ObserveLifecycle(LocalLifecycleOwner.current.lifecycle)

    val isTvShow = viewModel.isTvShow.collectAsStateWithLifecycle()

    val detailsState: State<UiState<DetailsInterface>> = if (isTvShow.value) {
        viewModel.tvShowsDetailsFlow.collectAsStateWithLifecycle()
    } else {
        viewModel.viewState.collectAsStateWithLifecycle()
    }

    val similarItems: LazyPagingItems<SimilarInterface> = if (isTvShow.value) {
        @Suppress("UNCHECKED_CAST")
        viewModel.similarTvShowsFlow.collectAsLazyPagingItems() as LazyPagingItems<SimilarInterface>
    } else {
        @Suppress("UNCHECKED_CAST")
        viewModel.similarMoviesFlow.collectAsLazyPagingItems() as LazyPagingItems<SimilarInterface>
    }

    val tabIndexState = viewModel.tabIndex.collectAsStateWithLifecycle()

    val myListMediaState = viewModel.myListMedia.collectAsStateWithLifecycle()

    val openDetailsStateDialog by remember { mutableStateOf(mutableStateOf(false)) }

//    val saveMediaState = viewModel.saveMediaFlow.collectAsStateWithLifecycle()
//
//    val removeMediaState = viewModel.removeMediaFlow.collectAsStateWithLifecycle()
//
//    updateAddToMyListButton(viewModel, saveMediaState)
//
//    updateAddToMyListButton(viewModel, removeMediaState)

    Column {
        when (detailsState.value) {
            UiState.Default -> {}
            is UiState.Error -> {
                openDetailsStateDialog.value = true

                DialogLoadingError(
                    errorPlace = stringResource(id = R.string.details_screen_error_message1),
                    errorMessage = stringResource(id = R.string.details_screen_error_message2),
                    showDialogStateState = openDetailsStateDialog
                ) {
                    navController.popBackStack()
                }
            }

            UiState.Loading -> CircularLoadingCenter()
            is UiState.Success -> BuildDetailsBody(
                navController = navController,
                viewModel = viewModel,
                details = (detailsState.value as UiState.Success).data,
                tabIndex = tabIndexState.value,
                similarItems = similarItems,
                myListMediaState = myListMediaState
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BuildDetailsBody(
    navController: NavHostController,
    viewModel: DetailsScreenViewModel,
    details: DetailsInterface,
    tabIndex: Int,
    similarItems: LazyPagingItems<SimilarInterface>,
    myListMediaState: State<UiState<MediaDetails?>>
) {
    val gradient = Brush.verticalGradient(listOf(topAppBarBackground, Color.Black))

    Box {
        Image(
            painter = asyncPainter(details.backdrop ?: ""),
            contentDescription = details.originalTitle,
            modifier = Modifier
                .blur(radius = 20.dp)
                .fillMaxWidth()
                .height(500.dp),
            contentScale = ContentScale.FillBounds
        )

        Box(
            modifier = Modifier
                .background(gradient)
                .fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TopAppBar(
                title = {},
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = null)
                    }
                },
                modifier = Modifier.fillMaxWidth(),
                colors = TopAppBarDefaults.smallTopAppBarColors(
                    containerColor = topAppBarBackground,
                    navigationIconContentColor = Color.White
                )
            )

            PosterItem(
                id = details.id,
                title = details.originalTitle,
                poster = details.poster,
                isTvShow = details.isTvShow,
                onClick = { _, _ -> })

            Spacer(modifier = Modifier.height(8.dp))

            TextTitle(text = details.title)

            Spacer(modifier = Modifier.height(36.dp))

            TextTitle(
                modifier = Modifier
                    .padding(start = 16.dp, end = 16.dp)
                    .align(Alignment.CenterHorizontally),
                text = details.overview,
                fontSize = 14,
                fontWeight = FontWeight.Normal.weight
            )

            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                IconAndTextButton(
                    icon = Icons.Filled.PlayArrow,
                    backgroundColor = Color.White,
                    text = stringResource(id = R.string.details_screen_play),
                    textColor = Color.Black
                ) {

                }

                Spacer(modifier = Modifier.width(24.dp))

                val defaultPair = Pair(R.string.details_screen_my_list, Icons.Filled.Star)
                val (label, icon) = when (myListMediaState.value) {
                    is UiState.Success -> {
                        if ((myListMediaState.value as UiState.Success).data != null) {
                            Pair(R.string.details_screen_added_to_my_list, Icons.Filled.Check)
                        } else {
                            defaultPair
                        }
                    }

                    else -> defaultPair
                }

                IconAndTextButton(
                    icon = icon,
                    backgroundColor = Color.Transparent,
                    backgroundStroke = true,
                    text = stringResource(id = label),
                    textColor = Color.White
                ) {
                    saveOrRemoveMedia(viewModel, myListMediaState, details)
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            BuildTabRow(viewModel, tabIndex)

            BuildPager(navController, tabIndex, details, similarItems)
        }
    }
}

@Composable
fun BuildTabRow(viewModel: DetailsScreenViewModel, tabIndex: Int) {
    val tabs = listOf(R.string.details_screen_watch_tab, R.string.details_screen_details_tab)

    TabRow(
        selectedTabIndex = tabIndex,
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        indicator = { tabPositions ->
            Box(
                modifier = Modifier
                    .tabIndicatorOffset(tabPositions[tabIndex])
                    .height(4.dp)
                    .background(color = MaterialTheme.colorScheme.secondary)
                    .padding(start = 4.dp, end = 4.dp)
            )
        }
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = {
                    TextTitle(
                        text = stringResource(id = title),
                        fontSize = 14,
                        fontWeight = FontWeight.Normal.weight
                    )
                },
                selected = tabIndex == index,
                onClick = { viewModel.updateTabIndex(index) },
                selectedContentColor = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun BuildPager(
    navController: NavHostController,
    tabIndex: Int,
    details: DetailsInterface,
    similarItems: LazyPagingItems<SimilarInterface>
) {
    val pagerState = rememberPagerState()

    LaunchedEffect(tabIndex) {
        pagerState.animateScrollToPage(tabIndex)
    }

    HorizontalPager(
        pageCount = 2,
        pageContent = { tabSelected ->
            when (tabSelected) {
                0 -> VerticalGrid(
                    list = similarItems,
                    emptyListMessage = R.string.details_screen_empty_list_similar
                ) { id, isTvShow ->
                    navController.navigate(Screens.Details.route + "/${id}/${isTvShow}")
                }

                1 -> BuildDetailsPage(details = details)
            }
        },
        state = pagerState,
        modifier = Modifier
            .padding(start = 16.dp, end = 16.dp)
    )
}

@Composable
fun BuildDetailsPage(details: DetailsInterface) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp)
    ) {
        TextTitle(
            text = stringResource(id = R.string.details_screen_details_page_title),
            fontSize = 16
        )

        Spacer(modifier = Modifier.height(16.dp))

        BuildMovieDetailsRow(
            R.string.details_screen_details_page_original_title,
            details.originalTitle
        )

        Spacer(modifier = Modifier.height(4.dp))

        val adultRes = if (details.adult) R.string.yes else R.string.no
        BuildMovieDetailsRow(
            R.string.details_screen_details_page_adult_content,
            stringResource(id = adultRes)
        )

        Spacer(modifier = Modifier.height(4.dp))

        val languages = details.spokenLanguages.map { it.name }.joinToString(separator = ", ")
        BuildMovieDetailsRow(R.string.details_screen_details_page_languages, languages)

        Spacer(modifier = Modifier.height(4.dp))
    }
}

@Composable
fun BuildMovieDetailsRow(titleRes: Int, attribute: String) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextTitle(
            text = stringResource(id = titleRes),
            fontSize = 15,
            color = Color.LightGray,
            fontWeight = FontWeight.Medium.weight
        )
        Spacer(modifier = Modifier.width(4.dp))
        TextTitle(
            text = attribute,
            fontSize = 15,
            color = Color.LightGray,
            fontWeight = FontWeight.Light.weight
        )
    }
}

private fun saveOrRemoveMedia(
    viewModel: DetailsScreenViewModel,
    state: State<UiState<MediaDetails?>>,
    details: DetailsInterface
) {
    when (state.value) {
        is UiState.Success -> {
            if ((state.value as UiState.Success).data != null) {
                viewModel.removeMedia(details.id)
            } else {
                viewModel.saveMedia(details)
            }
        }

        else -> {}
    }
}

@Preview
@Composable
fun DetailsScreenPreview() {
    DetailsScreen(rememberNavController())
}