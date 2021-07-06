package com.maslima.globo_play_recrutamento.presentation.components

import android.annotation.SuppressLint
import androidx.compose.animation.ColorPropKey
import androidx.compose.animation.DpPropKey
import androidx.compose.animation.core.TransitionState
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.transitionDefinition
import androidx.compose.animation.core.tween
import androidx.compose.animation.transition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.maslima.globo_play_recrutamento.R
import com.maslima.globo_play_recrutamento.presentation.components.HeartAnimationDefinition.HeartButtonState
import com.maslima.globo_play_recrutamento.presentation.components.HeartAnimationDefinition.HeartButtonState.*
import com.maslima.globo_play_recrutamento.presentation.components.HeartAnimationDefinition.heartSize
import com.maslima.globo_play_recrutamento.presentation.components.HeartAnimationDefinition.heartTransitionDefinition
import com.maslima.globo_play_recrutamento.utils.loadDrawableImage
import kotlinx.coroutines.ExperimentalCoroutinesApi


@ExperimentalCoroutinesApi
@Composable
fun AnimatedHeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartButtonState>,
    onToggle: () -> Unit
) {

    val toState = if (buttonState.value == IDLE) {
        ACTIVE
    } else {
        IDLE
    }

    val state = transition(
        definition = heartTransitionDefinition,
        initState = buttonState.value,
        toState = toState
    )

    HeartButton(
        modifier = modifier,
        buttonState = buttonState,
        state = state,
        onToggle = onToggle
    )

}

@ExperimentalCoroutinesApi
@Composable
private fun HeartButton(
    modifier: Modifier,
    buttonState: MutableState<HeartButtonState>,
    state: TransitionState,
    onToggle: () -> Unit,
) {
    if (buttonState.value == ACTIVE) {

        Column(
            Modifier
                .clickable { onToggle() }) {

            Text(
                text = "Adicionado",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            loadDrawableImage(defaultImage = R.drawable.heart_red).value?.let { image ->
                Image(
                    bitmap = image.asImageBitmap(),
                    modifier = modifier
                        .clickable(
                            onClick = onToggle,
                            indication = null,
                        )
                        .fillMaxWidth().align(Alignment.CenterHorizontally)
                        .width(state[heartSize])
                        .height(state[heartSize]),
                )
            }
        }


    } else {
        Column(

            Modifier
                .clickable { onToggle() }) {

            Text(
                text = "Favoritar",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally),
                textAlign = TextAlign.Center
            )

            loadDrawableImage(defaultImage = R.drawable.heart_grey).value?.let { image ->
                Image(
                    bitmap = image.asImageBitmap(),
                    modifier = modifier
                        .clickable(
                            onClick = onToggle,
                            indication = null,
                        )
                        .fillMaxWidth().align(Alignment.CenterHorizontally)
                        .width(state[heartSize])
                        .height(state[heartSize]),
                )
            }
        }
    }
}


object HeartAnimationDefinition {

    enum class HeartButtonState {
        IDLE, ACTIVE
    }

    val heartColor = ColorPropKey(label = "heartColor")
    val heartSize = DpPropKey(label = "heartDp")

    private val idleIconSize = 50.dp
    private val expandedIconSize = 80.dp

    @SuppressLint("Range")
    val heartTransitionDefinition =
        transitionDefinition<HeartButtonState> {
            state(IDLE) {
                this[heartColor] = Color.LightGray
                this[heartSize] = idleIconSize
            }

            state(ACTIVE) {
                this[heartColor] = Color.Red
                this[heartSize] = idleIconSize
            }

            transition(IDLE to ACTIVE) {
                heartColor using tween(durationMillis = 500)

                heartSize using keyframes {
                    durationMillis = 500
                    expandedIconSize at 100
                    idleIconSize at 200
                }
            }

            transition(ACTIVE to IDLE) {
                heartColor using tween(durationMillis = 500)

                heartSize using keyframes {
                    durationMillis = 500
                    expandedIconSize at 100
                    idleIconSize at 200
                }
            }
        }
}