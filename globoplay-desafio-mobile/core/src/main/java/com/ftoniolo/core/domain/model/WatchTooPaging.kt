package com.ftoniolo.core.domain.model

data class WatchTooPaging(
    val page: Long,
    val totalPages: Long,
    val films: List<WatchToo>
)
