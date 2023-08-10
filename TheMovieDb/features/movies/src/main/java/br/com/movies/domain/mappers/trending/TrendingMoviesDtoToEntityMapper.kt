package br.com.movies.domain.mappers.trending

import br.com.common.util.Mapper
import br.com.local.model.MovieEntity
import br.com.local.model.trending.TrendingMovieEntity
import br.com.common.data.dto.MoviesDto
import javax.inject.Inject

class TrendingMoviesDtoToEntityMapper @Inject constructor() : Mapper<MoviesDto, TrendingMovieEntity> {

    override suspend fun map(from: MoviesDto): TrendingMovieEntity {
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
        return TrendingMovieEntity(
            id  = from.id,
            movie = movie
        )
    }
}