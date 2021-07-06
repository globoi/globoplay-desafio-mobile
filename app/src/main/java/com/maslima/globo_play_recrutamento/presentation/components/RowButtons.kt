package com.maslima.globo_play_recrutamento.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.presentation.components.HeartAnimationDefinition.HeartButtonState.*
import com.maslima.globo_play_recrutamento.utils.loadDrawableImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@Composable
fun RowButtons(disableClick: () -> Unit, enableClick: () -> Unit) {
    val state = remember { mutableStateOf(IDLE) }
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        AnimatedHeartButton(
            modifier = Modifier,
            buttonState = state,
            onToggle = {
                if(state.value == IDLE){
                    disableClick()
                    state.value = ACTIVE
                } else {
                    enableClick()
                    state.value =  IDLE
                }
            },
        )
    }
}