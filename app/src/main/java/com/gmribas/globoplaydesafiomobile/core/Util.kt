package com.gmribas.globoplaydesafiomobile.core

import androidx.compose.runtime.Composable
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import com.gmribas.globoplaydesafiomobile.core.constants.Constants

@Composable
fun asyncPainter(image: String): AsyncImagePainter {
    return rememberAsyncImagePainter(Constants.POSTER_THUMBNAIL_ORIGINAL_PATH.plus(image))
}