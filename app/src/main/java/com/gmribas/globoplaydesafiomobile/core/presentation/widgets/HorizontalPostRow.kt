package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.core.domain.model.PosterItemInterface
import kotlinx.coroutines.flow.flow


@Composable
fun <T : PosterItemInterface> HorizontalCarousel(modifier: Modifier = Modifier, pagingItems: LazyPagingItems<T>, onClick: (id: Int, isTvShow: Boolean) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(160.dp)
        .then(modifier)
    ) {
        val state = pagingItems.loadState.refresh

        if (state is LoadState.Loading) {
            CircularLoadingCenter()
        }

        if (state is LoadState.Error && pagingItems.itemCount == 0) {
            Icon(
                Icons.Filled.Warning,
                contentDescription = "",
                tint = Color.Red,
                modifier = Modifier.fillMaxSize().padding(start = 8.dp)
            )
        }

        LazyRow {
            items(count = pagingItems.itemCount) { index ->
                pagingItems[index]?.let { movie ->
                    PosterItem(id = movie.id, title = movie.title, poster = movie.poster, isTvShow = movie.isTvShow, onClick = onClick)
                }
            }
            if (pagingItems.loadState.append is LoadState.Error) {
                item {
                    Icon(
                        Icons.Filled.Warning,
                        contentDescription = "",
                        tint = Color.Red,
                        modifier = Modifier
                            .fillMaxHeight()
                            .padding(start = 8.dp)
                            .width(110.dp)
                    )
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
            override val poster: String = "/jw0tYFCbzjBN8SIhvRC2kdh7pzh.jpg"
            override val backdrop: String = "/tsUlDhS8jeaK6x65ZrEtvrkPVx4.jpg"
            override val isTvShow: Boolean = false
        }

        emit(PagingData.from(listOf(obj, obj, obj)))
    }.collectAsLazyPagingItems()

    HorizontalCarousel(pagingItems = flow) { _, _ ->

    }
}