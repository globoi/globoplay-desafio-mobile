package com.nunkison.globoplaymobilechallenge

import androidx.annotation.StringRes
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun stringResource(@StringRes id: Int) = App.instance.getString(id)

fun thumbImage(imgPath: String?): String? {
    imgPath?.let {
        return "${stringResource(R.string.base_thumb_image_url)}${it}"
    }
    return null
}

fun originalImage(imgPath: String?): String? {
    imgPath?.let {
        return "${stringResource(R.string.base_original_image_url)}${it}"
    }
    return null
}

fun getYear(date: String) = Calendar.getInstance().apply {
    time = try {
        SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)!!
    } catch (e: Exception) {
        Date()
    }
}.get(Calendar.YEAR).toString()

fun Int.toCurrency(symbol: String): String {
    val format = DecimalFormat.getCurrencyInstance(Locale.getDefault()) as DecimalFormat
    val symbols = DecimalFormatSymbols(Locale.getDefault())
    symbols.currencySymbol = symbol
    format.decimalFormatSymbols = symbols
    return format.format(this)
}

