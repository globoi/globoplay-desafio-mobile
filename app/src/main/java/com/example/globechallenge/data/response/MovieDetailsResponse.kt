package com.example.globechallenge.data.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("imdb_id")
    val imdbId: String,

    @SerializedName("video")
    val video: Boolean,

    @SerializedName("title")
    val title: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("revenue")
    val revenue: Int,

    @SerializedName("genres")
    val genres: List<GenresItemDetail>,

    @SerializedName("popularity")
    val popularity: Double,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountriesItem?>,

    @SerializedName("id")
    val id: Int,

    @SerializedName("vote_count")
    val voteCount: Int,

    @SerializedName("budget")
    val budget: Int,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("original_title")
    val originalTitle: String,

    @SerializedName("runtime")
    val runtime: Int,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<SpokenLanguagesItem?>,

    @SerializedName("production_companies")
    val productionCompanies: List<ProductionCompaniesItem?>,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("belongs_to_collection")
    val belongsToCollection: Any,

    @SerializedName("tagline")
    val tagline: String,

    @SerializedName("adult")
    val adult: Boolean,

    @SerializedName("homepage")
    val homepage: String,

    @SerializedName("status")
    val status: String
)

data class ProductionCompaniesItem(

    @SerializedName("logo_path")
    val logoPath: String,

    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: Int,

    @SerializedName("origin_country")
    val originCountry: String
)

data class ProductionCountriesItem(

    @SerializedName("iso_3166_1")
    val iso31661: String,

    @SerializedName("name")
    val name: String
)

data class SpokenLanguagesItem(

    @SerializedName("name")
    val name: String,

    @SerializedName("iso_639_1")
    val iso6391: String,

    @SerializedName("english_name")
    val englishName: String
)

data class GenresItemDetail(

    @SerializedName("name")
    val name: String,

    @SerializedName("id")
    val id: Int
)