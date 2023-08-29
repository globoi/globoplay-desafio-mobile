package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(
    modifier: Modifier = Modifier,
    text: String,
    fontSize: Int = 20,
    color: Color = MaterialTheme.colorScheme.onPrimary,
    fontWeight: Int = FontWeight.Bold.weight
) {
    Text(
        text = text,
        color = color,
        style = TextStyle(
            fontSize = fontSize.sp,
            fontWeight = FontWeight(fontWeight)
        ),
        modifier = modifier,
        overflow = TextOverflow.Ellipsis
    )
}