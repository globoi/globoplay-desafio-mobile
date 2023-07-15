package com.nunkison.globoplaymobilechallenge

import androidx.annotation.StringRes

fun stringResource(@StringRes id: Int) = App.instance.getString(id)

fun thumb(imgPath: String) = "${stringResource(R.string.base_thumb_image_url)}${imgPath}"
