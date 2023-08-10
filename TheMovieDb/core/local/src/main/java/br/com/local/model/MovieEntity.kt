package br.com.local.model



data class MovieEntity(


    val adult: Boolean?,

    val backdropPath: String?,

    val firstAirDate: String?,

    val genreIds: List<Int>? = null,

    val mediaType: String?,

    val name: String?,

    val originCountry: List<String>? = null,

    val originalLanguage: String?,

    val originalName: String?,

    val originalTitle: String?,

    val overview: String?,

    val popularity: Double?,

    val posterPath: String?,

    val releaseDate: String?,

    val title: String?,

    val video: Boolean?,

    val voteAverage: Double?,

    val voteCount: Int?,
)
