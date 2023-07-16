package com.nunkison.globoplaymobilechallenge.ui.movies

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup

@Composable
fun MoviesLayout(
    data: List<MoviesGroup>,
    onMovieClick: (id: String) -> Unit
) {
    LazyColumn {
        itemsIndexed(data) { index, moviesGroup ->
            Text(
                modifier = Modifier
                    .padding(
                        top = if (index == 0) 40.dp else 24.dp,
                        bottom = 16.dp,
                        start = 16.dp,
                    ),
                text = moviesGroup.category,
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
            LazyRow {
                itemsIndexed(moviesGroup.movieCovers) { index, item ->
                    Box(
                        modifier = Modifier
                            .width(if (index == 0 || index == moviesGroup.movieCovers.size - 1) 158.dp else 150.dp)
                            .padding(
                                start = if (index == 0) 16.dp else 8.dp,
                                end = if (index == moviesGroup.movieCovers.size - 1) 16.dp else 8.dp
                            )
                            .clickable(onClick = {
                                onMovieClick(item.id)
                            })
                    ) {
                        AsyncImage(
                            modifier = Modifier
                                .width(150.dp),
                            model = ImageRequest.Builder(LocalContext.current)
                                .data(item.cover)
                                .crossfade(true)
                                .build(),
                            contentDescription = item.name,
                            contentScale = ContentScale.FillWidth,
                        )
                    }
                }
            }
            Box(modifier = Modifier.height(30.dp))
        }
    }
}