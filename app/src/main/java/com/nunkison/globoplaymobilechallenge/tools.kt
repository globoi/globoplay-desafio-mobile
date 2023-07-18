package com.nunkison.globoplaymobilechallenge

import androidx.annotation.StringRes
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun stringResource(@StringRes id: Int) = App.instance.getString(id)

fun iconImage(imgPath: String) = "${stringResource(R.string.base_icon_image_url)}${imgPath}"

fun thumbImage(imgPath: String) = "${stringResource(R.string.base_thumb_image_url)}${imgPath}"

fun originalImage(imgPath: String) = "${stringResource(R.string.base_original_image_url)}${imgPath}"

fun getYear(date: String) = Calendar.getInstance().apply {
    time = try {
        SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)!!
    } catch (e: Exception) {
        Date()
    }
}.get(Calendar.YEAR).toString()

