package com.nunkison.globoplaymobilechallenge

import androidx.annotation.StringRes
import androidx.compose.ui.focus.FocusRequester

fun FocusRequester.tryRequestFocus() {
    try {
        requestFocus()
    } catch (ignore: Exception) {
    }
}

fun stringResource(@StringRes id: Int) = MobileApp.instance.getString(id)