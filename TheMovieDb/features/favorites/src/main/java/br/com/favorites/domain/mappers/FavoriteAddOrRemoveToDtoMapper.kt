package br.com.favorites.domain.mappers

import br.com.common.domain.model.Movie
import br.com.common.util.Mapper
import br.com.favorites.data.remote.dto.AddOrRemoveFavoriteDto
import br.com.favorites.domain.model.AddOrRemoveFavorite
import br.com.local.model.favorite.FavoritiesMovieEntity
import javax.inject.Inject

class FavoriteAddOrRemoveToDtoMapper @Inject constructor() :
    Mapper<AddOrRemoveFavorite, AddOrRemoveFavoriteDto> {
    override suspend fun map(from: AddOrRemoveFavorite): AddOrRemoveFavoriteDto {
        return AddOrRemoveFavoriteDto(
            mediaId = from.mediaId,
            favorite = from.favorite,
            mediaType = from.mediaType
        )
    }
}