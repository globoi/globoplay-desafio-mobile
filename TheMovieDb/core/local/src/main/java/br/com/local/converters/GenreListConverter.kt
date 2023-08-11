package br.com.local.converters

import androidx.room.TypeConverter
import br.com.local.model.movie_details.GenreEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListConverter {

    @TypeConverter
    fun fromGenreList(genreList: List<GenreEntity>): String {
        val gson = Gson()
        return gson.toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreListString: String): List<GenreEntity> {
        val gson = Gson()
        val type = object : TypeToken<List<GenreEntity>>() {}.type
        return gson.fromJson(genreListString, type)
    }
}