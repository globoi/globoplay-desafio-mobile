package com.maslima.globo_play_recrutamento.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.utils.loadDrawableImage

@Composable
fun RowButtons(onFavoriteClick: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .padding(bottom = 40.dp)
                .preferredHeight(40.dp)
                .wrapContentWidth()
        ) {
            val img = loadDrawableImage(defaultImage = R.drawable.ic_play_arrow)
            img.value?.let {
                Image(bitmap = it.asImageBitmap())
            }
            Text(text = "Assista")
        }
        Spacer(modifier = Modifier.padding(10.dp))
        Button(
            onClick = { onFavoriteClick()},
            Modifier
                .padding(bottom = 40.dp)
                .preferredHeight(40.dp)
                .wrapContentWidth()
        ) {
            val img = loadDrawableImage(defaultImage = R.drawable.ic_star_rate)
            img.value?.let {
                Image(bitmap = it.asImageBitmap())
            }
            Text(text = "Favoritos")
        }
    }
}