package me.davidpcosta.tmdb.data.model

data class Generes (
    var genres: List<Genre>
)

data class Genre (
    var id: Long,
    var name: String
)