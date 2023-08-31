package com.gmribas.globoplaydesafiomobile.core.presentation.widgets

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import com.gmribas.globoplaydesafiomobile.R

@Composable
fun DialogLoadingError(
    errorPlace: String,
    errorMessage: String,
    showDialogStateState: MutableState<Boolean>,
    okButtonAction: () -> Unit
) {
    if (showDialogStateState.value) {
        AlertDialog(
            title = { Text(text = stringResource(id = R.string.dialog_error_title)) },
            text = {
                Text(
                    text = String.format(
                        stringResource(id = R.string.dialog_error_message),
                        errorPlace,
                        errorMessage
                    )
                )
            },
            onDismissRequest = { },
            confirmButton = {
                Button(onClick = {
                    okButtonAction()
                    showDialogStateState.value = false
                }) {
                    Text(text = stringResource(id = R.string.ok))
                }
            }
        )
    }
}