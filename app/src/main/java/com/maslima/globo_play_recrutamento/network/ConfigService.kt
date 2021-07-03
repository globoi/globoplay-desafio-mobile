package com.maslima.globo_play_recrutamento.network

import com.maslima.globo_play_recrutamento.network.responses.ConfigResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ConfigService {
    @GET("configuration")
    suspend fun getConfigInfo(@Query("api_key") apiKey: String): ConfigResponse
}