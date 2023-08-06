package com.reisdeveloper.globoplay.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Date
import java.util.Locale

const val YYYY_MM_DD = "yyyy-MM-dd"
const val DATE_FORMAT_DD_MM_YYYY = "dd/MM/yyyy"

fun String.toDateTimeString(patternReceived: String, patternResponse: String): String {
    return try {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            toLocalDateTime(patternReceived).toStringByPattern(patternResponse)
        } else {
            toDate(patternReceived)?.toStringByPattern(patternResponse) ?: ""
        }
    } catch (e: Exception) {
        ""
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun String.toLocalDateTime(pattern: String): LocalDateTime {
    try {
        return LocalDateTime.parse(this, DateTimeFormatter.ofPattern(pattern))
    } catch (e: DateTimeParseException) {
        throw e
    }
}

fun Date.toStringByPattern(pattern: String): String {
    return SimpleDateFormat(pattern, Locale.getDefault()).format(this)
}

fun String.toDate(pattern: String): Date? {
    val formatter = SimpleDateFormat(pattern, Locale.getDefault())
    return formatter.parse(this)
}

@RequiresApi(Build.VERSION_CODES.O)
fun LocalDateTime.toStringByPattern(pattern: String): String {
    return try {
        val formatter = DateTimeFormatter.ofPattern(pattern, Locale.getDefault())
        this.format(formatter)
    } catch (e: DateTimeParseException) {
        ""
    }
}