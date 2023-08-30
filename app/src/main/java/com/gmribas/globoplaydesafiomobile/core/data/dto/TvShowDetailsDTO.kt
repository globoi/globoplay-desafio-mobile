package com.gmribas.globoplaydesafiomobile.core.data.dto

import com.gmribas.globoplaydesafiomobile.core.domain.model.Language
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShow
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.details.data.source.toDomain
import com.google.gson.annotations.SerializedName

data class TvShowDetailsDTO(
    val adult: Boolean,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("created_by")
    val createdBy: List<CreatedBy>,

    @SerializedName("episode_run_time")
    val episodeRunTime: List<Any?>,

    @SerializedName("first_air_date")
    val firstAirDate: String,

    val genres: List<GenreDTO>,
    val homepage: String,
    val id: Int,

    @SerializedName("in_production")
    val inProduction: Boolean,

    val languages: List<String>,

    @SerializedName("last_air_date")
    val lastAirDate: String,

    @SerializedName("last_episode_to_air")
    val lastEpisodeToAir: LastEpisodeToAir,

    val name: String,

    @SerializedName("next_episode_to_air")
    val nextEpisodeToAir: Any? = null,

    val networks: List<Network>,

    @SerializedName("number_of_episodes")
    val numberOfEpisodes: Long,

    @SerializedName("number_of_seasons")
    val numberOfSeasons: Long,

    @SerializedName("origin_country")
    val originCountry: List<String>,

    @SerializedName("original_language")
    val originalLanguage: String,

    @SerializedName("original_name")
    val originalName: String,

    val overview: String,
    val popularity: Double,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("production_companies")
    val productionCompanies: List<Network>,

    @SerializedName("production_countries")
    val productionCountries: List<ProductionCountry>,

    val seasons: List<Season>,

    @SerializedName("spoken_languages")
    val spokenLanguages: List<LanguageDTO>,

    val status: String,
    val tagline: String,
    val type: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long
) {
    fun toDomain(): TvShowDetails {
        return TvShowDetails(
            adult,
            backdropPath,
            episodeRunTime,
            firstAirDate,
            homepage,
            id,
            inProduction,
            languages,
            lastAirDate,
            name,
            numberOfEpisodes,
            numberOfSeasons,
            originCountry,
            originalLanguage,
            originalName,
            overview,
            popularity,
            posterPath,
            spokenLanguages.map { it.toDomain() },
            status,
            tagline,
            type,
            voteAverage,
            voteCount
        )
    }
}

data class CreatedBy(
    val id: Long,

    @SerializedName("credit_id")
    val creditID: String,

    val name: String,
    val gender: Long,

    @SerializedName("profile_path")
    val profilePath: String
)

data class LastEpisodeToAir(
    val id: Long,
    val name: String,
    val overview: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Long,

    @SerializedName("air_date")
    val airDate: String,

    @SerializedName("episode_number")
    val episodeNumber: Long,

    @SerializedName("episode_type")
    val episodeType: String,

    @SerializedName("production_code")
    val productionCode: String,

    val runtime: Long,

    @SerializedName("season_number")
    val seasonNumber: Long,

    @SerializedName("show_id")
    val showID: Long,

    @SerializedName("still_path")
    val stillPath: String
)

data class Network(
    val id: Long,

    @SerializedName("logo_path")
    val logoPath: String? = null,

    val name: String,

    @SerializedName("origin_country")
    val originCountry: String
)

data class ProductionCountry(
    @SerializedName("iso_3166_1")
    val iso3166_1: String,

    val name: String
)

data class Season(
    @SerializedName("air_date")
    val airDate: String,

    @SerializedName("episode_count")
    val episodeCount: Long,

    val id: Long,
    val name: String,
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("season_number")
    val seasonNumber: Long,

    @SerializedName("vote_average")
    val voteAverage: Double
)
