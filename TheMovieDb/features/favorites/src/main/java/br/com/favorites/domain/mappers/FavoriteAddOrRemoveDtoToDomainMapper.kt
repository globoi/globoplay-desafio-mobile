package br.com.favorites.domain.mappers

import br.com.common.util.Mapper
import br.com.favorites.data.remote.dto.ResultAddFavoriteDto
import br.com.favorites.domain.model.ResultAddFavorite
import javax.inject.Inject


//private const val FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER = "dd MMM yyyy"
//const val FORMAT_DATE_MOVIE_RELEASE_PARSER = "yyyy-MM-dd"

class FavoriteAddOrRemoveDtoToDomainMapper @Inject constructor() :
    Mapper<ResultAddFavoriteDto, ResultAddFavorite> {

    override suspend fun map(from: ResultAddFavoriteDto): ResultAddFavorite {

        return ResultAddFavorite(
            statusCode = from.statusCode,
            statusMessage = from.statusMessage,
            success = from.success
        )
    }
}