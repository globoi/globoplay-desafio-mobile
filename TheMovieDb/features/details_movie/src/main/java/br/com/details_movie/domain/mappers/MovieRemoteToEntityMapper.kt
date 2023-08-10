package br.com.details_movie.domain.mappers

import br.com.common.util.Mapper
import br.com.details_movie.data.remote.dto.MovieDto
import br.com.local.model.movie_details.MovieEntity
import javax.inject.Inject

class MovieRemoteToEntityMapper @Inject constructor() : Mapper<MovieDto, MovieEntity> {

    override suspend fun map(from: MovieDto): MovieEntity = MovieEntity(
        id = from.id,
        description = from.description,
        tagline = from.tagline,
        posterPath = from.posterPath,
        releaseDate = from.releaseDate,
        title = from.title,
        voteAverage = from.voteAverage,
        genre = from.genres?.firstOrNull()?.name
    )
}