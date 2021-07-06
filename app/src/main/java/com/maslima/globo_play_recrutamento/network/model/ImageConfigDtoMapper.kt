package com.maslima.globo_play_recrutamento.network.model

import com.maslima.globo_play_recrutamento.domain.model.ImageConfig
import com.maslima.globo_play_recrutamento.domain.util.DomainMapper

class ImageConfigDtoMapper : DomainMapper<ImageConfigDto, ImageConfig> {
    override fun mapToDomainModel(model: ImageConfigDto) = ImageConfig(
        baseUrl = model.base_url,
        secureBaseUrl = model.secure_base_url,
        backdropSizes = model.backdrop_sizes,
        logoSizes = model.logo_sizes,
        posterSizes = model.poster_sizes,
        profileSizes = model.profile_sizes,
        stillSizes = model.still_sizes
    )

    override fun mapFromDomainModel(domainModel: ImageConfig) = ImageConfigDto(
        base_url = domainModel.baseUrl,
        secure_base_url = domainModel.secureBaseUrl,
        backdrop_sizes = domainModel.backdropSizes,
        logo_sizes = domainModel.logoSizes,
        poster_sizes = domainModel.posterSizes,
        profile_sizes = domainModel.profileSizes,
        still_sizes = domainModel.stillSizes
    )
}