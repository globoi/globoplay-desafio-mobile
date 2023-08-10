package br.com.favorites.domain.mappers

import br.com.common.util.Mapper
import br.com.local.model.MovieEntity
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.common.data.dto.MoviesDto
import javax.inject.Inject

class FavoritiesMoviesDtoToEntityMapper  @Inject constructor() :
    Mapper<MoviesDto, FavoritiesMovieEntity> {

    override suspend fun map(from: MoviesDto): FavoritiesMovieEntity {
        val movie = MovieEntity(
            adult = from.adult,
            backdropPath  = from.backdropPath,
            firstAirDate  = from.firstAirDate,
            genreIds  = from.genreIds,
            mediaType  = from.mediaType,
            name  = from.name,
            originCountry  =  from.originCountry ,
            originalLanguage  =  from.originalLanguage,
            originalName  = from.originalName,
            originalTitle  = from.originalTitle,
            overview  = from.overview,
            popularity  = from.popularity,
            posterPath  = from.posterPath,
            releaseDate  = from.releaseDate,
            title  = from.title,
            video  = from.video,
            voteAverage  = from.voteAverage,
            voteCount  = from.voteCount
        )

        return FavoritiesMovieEntity(
            id  = from.id,
            movie = movie
        )
    }
}