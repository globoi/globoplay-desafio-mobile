package com.app.fakegloboplay.network.response

import com.google.gson.annotations.SerializedName


data class LastEpisodeToAir(

    @SerializedName("id") var id: Int? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("overview") var overview: String? = null,
    @SerializedName("vote_average") var voteAverage: Int? = null,
    @SerializedName("vote_count") var voteCount: Int? = null,
    @SerializedName("air_date") var airDate: String? = null,
    @SerializedName("episode_number") var episodeNumber: Int? = null,
    @SerializedName("production_code") var productionCode: String? = null,
    @SerializedName("runtime") var runtime: Int? = null,
    @SerializedName("season_number") var seasonNumber: Int? = null,
    @SerializedName("show_id") var showId: Int? = null,
    @SerializedName("still_path") var stillPath: String? = null

)