package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gmribas.globoplaydesafiomobile.core.asyncPainter

@Composable
fun PosterItem(modifier: Modifier = Modifier, id: Int, title: String, poster: String, onClick: (id: Int) -> Unit) {
    Card(
        modifier = Modifier
            .padding(bottom = 5.dp, top = 5.dp, start = 5.dp, end = 5.dp)
            .width(110.dp)
            .height(160.dp)
            .clickable{ onClick(id) }
            .then(modifier),
        shape = RoundedCornerShape(5.dp)
    ) {
        Image(
            painter = asyncPainter(poster),
            contentDescription = title,
            modifier = Modifier
                .width(110.dp)
                .height(160.dp),
            contentScale = ContentScale.FillBounds
        )
    }
}

@Preview
@Composable
fun PosterItemPreview() {
    PosterItem(id = 1, title = "", poster = "") {}
}