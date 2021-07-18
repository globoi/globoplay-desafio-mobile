package com.example.globechallenge.helper

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceHelper (val context: Context?) {
    private val IS_FAVORITE = "isFavorite"

    private fun getSharedPreferences() : SharedPreferences {
        return context?.applicationContext!!
            .getSharedPreferences(IS_FAVORITE, Context.MODE_PRIVATE)
    }

    fun setIsFavoriteSharedPreferences(boolean: Boolean) {
        val editor = getSharedPreferences().edit().clear()
        editor.putBoolean(IS_FAVORITE, boolean)
        editor.apply()
    }

    fun getIsFavoriteSharedPreferences(): Boolean {
        return getSharedPreferences().getBoolean(IS_FAVORITE, false)
    }
}