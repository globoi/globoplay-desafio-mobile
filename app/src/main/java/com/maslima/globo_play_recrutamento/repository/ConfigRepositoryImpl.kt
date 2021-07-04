package com.maslima.globo_play_recrutamento.repository

import com.maslima.globo_play_recrutamento.domain.model.ImageConfig
import com.maslima.globo_play_recrutamento.network.MovieService
import com.maslima.globo_play_recrutamento.network.model.ImageConfigDtoMapper
import com.maslima.globo_play_recrutamento.utils.API_KEY

class ConfigRepositoryImpl(
    private val configService: MovieService,
    private val mapper: ImageConfigDtoMapper
) : ConfigRepository {

    override suspend fun getConfig(): ImageConfig {
        val result = configService.getConfigInfo(API_KEY)
        return mapper.mapToDomainModel(result.images)
    }
}