package com.tiagopereira.globotmdb.utils

import com.tiagopereira.globotmdb.utils.Constants.Companion.NOW_PLAYING
import com.tiagopereira.globotmdb.utils.Constants.Companion.POPULAR
import com.tiagopereira.globotmdb.utils.Constants.Companion.TOP_RATED
import com.tiagopereira.globotmdb.utils.Constants.Companion.UPCOMING
import java.text.DecimalFormat
import java.util.Locale.US

fun String.translateToPortuguese(): String {
    return when (this.lowercase()) {
        POPULAR -> "Popular"
        TOP_RATED -> "Mais votados"
        UPCOMING -> "Em breve"
        NOW_PLAYING -> "Nos cinemas"
        else -> this // Mantém a palavra original se não corresponder a nenhuma das opções acima
    }
}

fun String.convertDateBr(): String {
    val (ano, mes, dia) = this.split("-")
    return "$dia/$mes/$ano"
}

fun Double.formatUs(): String {
    val nf = DecimalFormat.getCurrencyInstance(US)
    val formatter = nf as DecimalFormat
    formatter.applyPattern("#,###,###")
    return "US$ ${formatter.format(this)}"
}
