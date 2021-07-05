package com.maslima.globo_play_recrutamento.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.utils.loadPictures

@Composable
fun MovieCard(movieUrlImage: String, onClickCard: () -> Unit) {
    Card(
        shape = MaterialTheme.shapes.small,
        modifier = Modifier
            .padding(Dp(5f))
            .clickable(onClick = onClickCard),
        elevation = Dp(8f)
    ) {
        val img = loadPictures(url = movieUrlImage, defaultImage = R.drawable.no_image_avaiable)
        img.value?.let { image ->
            Image(
                bitmap = image.asImageBitmap(),
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
        }
    }
}