package com.maslima.globo_play_recrutamento.repository

import com.maslima.globo_play_recrutamento.domain.model.ImageConfig

interface ConfigRepository {
    suspend fun getConfig(): ImageConfig
}