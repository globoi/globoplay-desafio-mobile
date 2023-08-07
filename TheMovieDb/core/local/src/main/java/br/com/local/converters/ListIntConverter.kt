package br.com.local.converters

import androidx.room.TypeConverter

class ListIntConverter {
    @TypeConverter
    fun fromList(list: List<Int>?): String? {
        return list?.joinToString(",")
    }

    @TypeConverter
    fun toList(data: String?): List<Int> {
        if (data.isNullOrEmpty()) {
            return emptyList()
        }
        return data.split(",").mapNotNull { it.toIntOrNull() }
    }
}