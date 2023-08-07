package br.com.local.converters

import androidx.room.TypeConverter


class ListStringConverter {
    @TypeConverter
    fun fromList(list: List<String>?): String? {
        return list?.joinToString(";")
    }

    @TypeConverter
    fun toListList(data: String?): List<String> {
        return data?.split(",")?.map { it.toString() } ?: emptyList()

    }
}