package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun TextTitle(text: String, color: Color = Color.White) {
    Text(
        text = text,
        color = color,
        style = TextStyle(fontSize = 14.sp)
    )
}