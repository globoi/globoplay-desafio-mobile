package com.maslima.globo_play_recrutamento.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.utils.loadPictures

@Composable
fun ImageSection(movie: Movie) {
    val bitmap = loadPictures(
        url = "https://image.tmdb.org/t/p/w500".plus(movie.posterPath),
        defaultImage = R.drawable.no_image_avaiable
    )
    bitmap.value?.let {
        Image(
            bitmap = it.asImageBitmap(),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .wrapContentWidth()
                .wrapContentHeight(),
            contentScale = ContentScale.Crop
        )
    }
}