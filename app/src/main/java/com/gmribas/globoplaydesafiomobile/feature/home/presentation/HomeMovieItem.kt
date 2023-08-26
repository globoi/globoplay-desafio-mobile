package com.gmribas.globoplaydesafiomobile.feature.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun HomeMovieItem(id: Int, title: String, poster: String, onClick: (movieId: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(bottom = 5.dp, top = 5.dp, start = 5.dp, end = 5.dp)
            .width(48.dp)
            .height(64.dp)
            .clickable{ onClick(id) },
        shape = RoundedCornerShape(15.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Image(
                painter = rememberAsyncImagePainter(poster),
                contentDescription = title,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}