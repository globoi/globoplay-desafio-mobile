package com.gmribas.globoplaydesafiomobile.core.data.dto

import com.google.gson.annotations.SerializedName

data class MovieDetailsDTO (
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: CollectionDTO,

    val budget: Long,
    val genres: List<GenreDTO>,
    val homepage: String,
    val id: Int,

    @SerializedName("imdb_id")
    val imdbID: String,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_title")
    val originalTitle: String,

    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompanyDTO>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountryDTO>,

    @SerializedName("release_date")
    val releaseDate: String,

    val revenue: Long,
    val runtime: Long,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<LanguageDTO>,

    val status: String,
    val tagline: String,
    val title: String,
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long
)

data class CollectionDTO (
    val id: Long,
    val name: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String
)

data class GenreDTO (
    val id: Long,
    val name: String
)

data class ProductionCompanyDTO(
    val id: Long,

    @SerializedName("logo_path")
    val logoPath: String,

    val name: String,

    @SerializedName("origin_country")
    val originCountry: String
)

data class ProductionCountryDTO (
    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    val name: String
)

data class LanguageDTO (
    @SerializedName("english_name")
    val englishName: String,

    @SerializedName("iso_639_1")
    val iso639_1: String,

    val name: String
)