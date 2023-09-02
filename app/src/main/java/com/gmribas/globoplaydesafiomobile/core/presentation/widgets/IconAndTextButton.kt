package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun IconAndTextButton(
    icon: ImageVector,
    backgroundColor: Color,
    backgroundStroke: Boolean = false,
    text: String,
    textColor: Color,
    onClick: () -> Unit
) {

    val shape = RoundedCornerShape(5.dp)
    val strokeModifier = Modifier

    if (backgroundStroke) {
        strokeModifier
            .clip(shape)
            .border(3.dp, Color.Red, shape)
    }

    Card(
        modifier = Modifier
            .clickable { onClick() }
            .then(strokeModifier),
        shape = shape
    ) {
        Row(
            modifier = Modifier
                .height(48.dp)
                .defaultMinSize(minWidth = 100.dp)
                .background(backgroundColor)
                .padding(start = 24.dp, end = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(icon, contentDescription = null, tint = textColor)

            Spacer(modifier = Modifier.width(12.dp))

            TextTitle(
                text = text,
                color = textColor,
                fontSize = 16,
                fontWeight = FontWeight.Normal.weight
            )
        }
    }
}

@Preview
@Composable
fun IconAndTextButtonPreview() {
    IconAndTextButton(
        icon = Icons.Filled.Warning,
        backgroundColor = Color.Black,
        backgroundStroke = false,
        text = "button button button",
        textColor = Color.White
    ) {}
}

@Preview
@Composable
fun IconAndTextButtonPreview2() {
    IconAndTextButton(
        icon = Icons.Filled.Warning,
        backgroundColor = Color.White,
        backgroundStroke = true,
        text = "button button button",
        textColor = Color.Black
    ) {}
}