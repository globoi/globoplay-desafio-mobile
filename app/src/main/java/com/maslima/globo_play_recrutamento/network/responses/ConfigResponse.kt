package com.maslima.globo_play_recrutamento.network.responses

import com.google.gson.annotations.SerializedName
import com.maslima.globo_play_recrutamento.network.model.ImageConfigDto

data class ConfigResponse(
    @SerializedName("images") val images: ImageConfigDto,
    @SerializedName("change_keys") val change_keys: List<String>
)