package com.app.fakegloboplay.network.response

import com.google.gson.annotations.SerializedName


data class DetailsMove(

    @SerializedName("adult") var adult: Boolean? = null,
    @SerializedName("backdrop_path") var backdropPath: String? = null,
    @SerializedName("created_by") var createdBy: ArrayList<CreatedBy> = arrayListOf(),
    @SerializedName("episode_run_time") var episodeRunTime: ArrayList<Int> = arrayListOf(),
    @SerializedName("first_air_date") var firstAirDate: String? = null,
    @SerializedName("genres") var genres: ArrayList<Genres> = arrayListOf(),
    @SerializedName("homepage") var homepage: String? = null,
    @SerializedName("id") var id: Int? = null,
    @SerializedName("in_production") var inProduction: Boolean? = null,
    @SerializedName("languages") var languages: ArrayList<String> = arrayListOf(),
    @SerializedName("last_air_date") var lastAirDate: String? = null,
    @SerializedName("last_episode_to_air") var lastEpisodeToAir: LastEpisodeToAir? = LastEpisodeToAir(),
    @SerializedName("name") var name: String? = null,
    @SerializedName("next_episode_to_air") var nextEpisodeToAir: String? = null,
    @SerializedName("number_of_episodes") var numberOfEpisodes: Int? = null,
    @SerializedName("number_of_seasons") var numberOfSeasons: Int? = null,
    @SerializedName("origin_country") var originCountry: ArrayList<String> = arrayListOf(),
    @SerializedName("original_language") var originalLanguage: String? = null,
    @SerializedName("original_name") var originalName: String? = null,
    @SerializedName("original_title") var originalTitle: String? = null,
    @SerializedName("release_date") var releaseDate: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("popularity") var popularity: Double? = null,
    @SerializedName("poster_path") var posterPath: String? = null,
    @SerializedName("production_companies") var productionCompanies: ArrayList<ProductionCompanies> = arrayListOf(),
    @SerializedName("production_countries") var productionCountries: ArrayList<ProductionCountries> = arrayListOf(),
    @SerializedName("seasons") var seasons: ArrayList<Seasons> = arrayListOf(),
    @SerializedName("spoken_languages") var spokenLanguages: ArrayList<SpokenLanguages> = arrayListOf(),
    @SerializedName("status") var status: String? = null,
    @SerializedName("tagline") var tagline: String? = null,
    @SerializedName("type") var type: String? = null,
    @SerializedName("vote_average") var voteAverage: Double? = null,
    @SerializedName("vote_count") var voteCount: Int? = null,
    var credits: Credits? = Credits()

)