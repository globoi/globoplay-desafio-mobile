package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.gmribas.globoplaydesafiomobile.R
import com.gmribas.globoplaydesafiomobile.core.domain.model.PosterItemInterface
import kotlinx.coroutines.flow.flow

@Composable
fun <T : PosterItemInterface> VerticalGrid(
    modifier: Modifier = Modifier,
    list: LazyPagingItems<T>,
    emptyListMessage: Int,
    onClick: (id: Int, isTvShow: Boolean) -> Unit
) {
    LazyVerticalGrid(
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        columns = GridCells.Fixed(3),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(list.itemCount) { index ->
            list[index]?.let { item ->
                PosterItem(
                    id = item.id,
                    title = item.title,
                    isTvShow = item.isTvShow,
                    poster = item.poster,
                    onClick = onClick
                )
            }
        }
    }

    if (list.itemCount == 0) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .then(modifier)
        ) {
            TextTitle(
                text = stringResource(id = emptyListMessage),
                modifier = Modifier.align(Alignment.Center),
                fontSize = 14
            )
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
            override val isTvShow: Boolean = false
            override val poster: String = "/jw0tYFCbzjBN8SIhvRC2kdh7pzh.jpg"
            override val backdrop: String = "/tsUlDhS8jeaK6x65ZrEtvrkPVx4.jpg"
        }

        emit(PagingData.from(listOf(obj, obj, obj, obj, obj, obj, obj, obj, obj)))
    }.collectAsLazyPagingItems()

    VerticalGrid(list = flow, emptyListMessage = R.string.my_list_screen_empty_list) { _, _ ->
    }
}