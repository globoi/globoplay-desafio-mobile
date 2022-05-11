package com.ftoniolo.globoplay.presentation.favorites

import com.ftoniolo.globoplay.presentation.common.ListItem

data class FilmItem(
    val id:Long,
    val title:String,
    val imageUrl: String,
    override val key: Long = id.toLong()
) : ListItem
