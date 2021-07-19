package com.com.globo.helper

import android.content.Context
import android.text.Spannable
import android.text.SpannableString
import android.text.style.TextAppearanceSpan

fun createSpannableString(
    context: Context,
    vararg pairTextAndStyle: Pair<String, Int>
): SpannableString {

    var allText = ""
    for (pair in pairTextAndStyle) {
        allText += "${pair.first} "
    }
    allText = allText.removeSuffix(" ")

    val spannableString = SpannableString(allText)

    for (pair in pairTextAndStyle) {

        val index = allText.indexOf(pair.first)

        spannableString.setSpan(
            TextAppearanceSpan(context, pair.second),
            index,
            index + pair.first.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }

    return spannableString
}
