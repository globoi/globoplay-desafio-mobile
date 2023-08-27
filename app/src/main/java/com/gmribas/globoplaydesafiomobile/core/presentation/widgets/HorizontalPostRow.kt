package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.feature.home.domain.model.PosterItemInterface
import kotlinx.coroutines.flow.flow


@Composable
fun <T : PosterItemInterface> HorizontalCarousel(modifier: Modifier = Modifier, pagingItems: LazyPagingItems<T>, onClick: (id: Int) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)
        .then(modifier)
    ) {

        if (pagingItems.itemCount == 0) {
            CircularLoadingCenter()
        }

        LazyRow {
            items(count = pagingItems.itemCount) { index ->
                pagingItems[index]?.let { movie ->
                    PosterItem(id = movie.id, title = movie.title, poster = movie.poster, onClick)
                }
            }
        }
    }
}

@Preview
@Composable
fun HorizontalCarouselPreview() {
    val flow = flow<PagingData<PosterItemInterface>> {
        val obj = object : PosterItemInterface {
            override val id: Int = 1
            override val title: String = "Title"
            override val poster: String = "Poster"
            override val backdrop: String = ""
        }

        emit(PagingData.from(listOf(obj)))
    }.collectAsLazyPagingItems()

    HorizontalCarousel(pagingItems = flow) {}
}