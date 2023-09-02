package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.constants.Constants
import com.gmribas.globoplaydesafiomobile.core.domain.model.PosterItemInterface
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Thanks to TheSomeshKumar
 * https://github.com/TheSomeshKumar/Flixplorer
 */
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun <T: PosterItemInterface>HorizontalAnimatedCarousel(
    list: LazyPagingItems<T>,
    totalItemsToShow: Int = 10,
    autoScrollDuration: Long = Constants.CAROUSEL_AUTO_SCROLL_TIMER,
    onItemClicked: (T) -> Unit
) {
    val pageCount = list.itemCount.coerceAtMost(totalItemsToShow)
    val pagerState: PagerState = rememberPagerState()
    val isDragged by pagerState.interactionSource.collectIsDraggedAsState()
    if (isDragged.not()) {
        with(pagerState) {
            if (pageCount > 0) {
                var currentPageKey by remember { mutableStateOf(0) }
                LaunchedEffect(key1 = currentPageKey) {
                    launch {
                        delay(timeMillis = autoScrollDuration)
                        val nextPage = (currentPage + 1).mod(pageCount)
                        animateScrollToPage(page = nextPage)
                        currentPageKey = nextPage
                    }
                }
            }
        }
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.height(dimensionResource(id = R.dimen.horizontal_animated_carousel_height))
    ) {
        if (list.itemSnapshotList.isEmpty()) {
            CircularLoadingCenter()
        }

        Box {
            HorizontalPager(
                pageCount = pageCount,
                state = pagerState,
                contentPadding = PaddingValues(
                    horizontal = dimensionResource(id = R.dimen.double_padding)
                ),
                pageSpacing = dimensionResource(id = R.dimen.normal_padding)
            ) { page: Int ->
                val item: T? = list[page]
                item?.let {
                    Card(onClick = { onItemClicked(it) }) {
                        CarouselItem(it)
                    }
                }
            }
        }
    }
}

@Composable
fun CarouselItem(item: PosterItemInterface) {
    Box {
        Image(
            painter = rememberAsyncImagePainter(Constants.POSTER_THUMBNAIL_ORIGINAL_PATH.plus(item.backdrop)),
            contentDescription = item.title,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.horizontal_animated_carousel_height))
                .fillMaxWidth(),
        )
        val gradient =
            Brush.verticalGradient(listOf(Color.Transparent, MaterialTheme.colorScheme.primaryContainer))

        Text(
            text = item.title,
            color = Color.White,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .background(gradient)
                .padding(
                    horizontal = dimensionResource(id = R.dimen.normal_padding),
                    vertical = dimensionResource(id = R.dimen.small_padding)
                )
        )
    }
}
