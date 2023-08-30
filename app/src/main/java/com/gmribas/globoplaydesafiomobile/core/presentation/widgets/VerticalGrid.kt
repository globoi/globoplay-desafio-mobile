package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.core.domain.model.PosterItemInterface
import kotlinx.coroutines.flow.flow

@Composable
fun <T : PosterItemInterface> VerticalGrid(list: LazyPagingItems<T>, onClick: (id: Int) -> Unit) {
    LazyVerticalGrid(
        modifier = Modifier.fillMaxWidth(),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.SpaceBetween
        ) {
        items(list.itemCount) { index ->
            list[index]?.let { item ->
                PosterItem(
                    id = item.id,
                    title = item.title,
                    poster = item.poster,
                    onClick = onClick
                )
            }
        }
    }
}

@Preview
@Composable
fun VerticalGridPreview() {
    val flow = flow<PagingData<PosterItemInterface>> {
        val obj = object : PosterItemInterface {
            override val id: Int = 1
            override val title: String = "Title"
            override val poster: String = "/jw0tYFCbzjBN8SIhvRC2kdh7pzh.jpg"
            override val backdrop: String = "/tsUlDhS8jeaK6x65ZrEtvrkPVx4.jpg"
        }

        emit(PagingData.from(listOf(obj, obj, obj, obj, obj, obj, obj, obj, obj)))
    }.collectAsLazyPagingItems()

    VerticalGrid(flow) {
    }
}