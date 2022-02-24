package com.globo.moviesapp.network.genre

import com.globo.moviesapp.model.genre.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import retrofit2.Retrofit
import java.lang.Exception
import javax.inject.Inject

class GenreRepository @Inject constructor(
    val retrofit: Retrofit
) {
    fun getGenreAll(apiKey: String, language: String): List<Genre> {
        val call = retrofit.create(GenreServiceInterface::class.java)
            .getGenreCall(apiKey, language).execute()

        val result = call.body()
        val error = call.errorBody().toString()

        if (error != "null"){
            throw Exception(call.message())
        }

        val type = object : TypeToken<List<Genre>>() {}.type
        return Gson().fromJson(result?.get("genres"), type)
    }
}