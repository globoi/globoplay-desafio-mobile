package br.com.movies.domain.mappers

import br.com.common.util.Mapper
import br.com.local.model.TrendingMovieEntity
import br.com.movies.data.remote.dto.TrendingMoviesDto
import br.com.movies.domain.model.TrendingMovies
import javax.inject.Inject

class TrendingMoviesDtoToEntityMapper @Inject constructor() : Mapper<TrendingMoviesDto,TrendingMovieEntity> {

    override suspend fun map(from: TrendingMoviesDto): TrendingMovieEntity {
        return TrendingMovieEntity(
            adult = from.adult,
        backdropPath  = from.backdropPath,
        firstAirDate  = from.firstAirDate,
        genreIds  = from.genreIds,
        id  = from.id,
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
        voteCount  = from.voteCount)
    }
}