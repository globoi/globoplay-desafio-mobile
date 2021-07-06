package com.maslima.globo_play_recrutamento.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maslima.globo_play_recrutamento.domain.model.Movie

@Composable
fun MovieDescriptionSection(movie: Movie) {
    Text(
        text = movie.title,
        style = MaterialTheme.typography.h5,
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Text(
        text = movie.originalTitle,
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier
            .padding(bottom = 4.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )
    Text(
        text = movie.overview,
        style = MaterialTheme.typography.body1,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        maxLines = 4
    )
}