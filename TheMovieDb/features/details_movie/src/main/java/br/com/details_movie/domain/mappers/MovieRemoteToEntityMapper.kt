package br.com.details_movie.domain.mappers

import br.com.common.util.Mapper
import br.com.details_movie.data.remote.dto.MovieDetailsDto
import br.com.local.model.movie_details.GenreEntity
import br.com.local.model.movie_details.MovieDetailsEntity
import javax.inject.Inject

class MovieRemoteToEntityMapper @Inject constructor() : Mapper<MovieDetailsDto, MovieDetailsEntity> {

    override suspend fun map(from: MovieDetailsDto): MovieDetailsEntity = MovieDetailsEntity(
        id = from.id,
        overview = from.overview,
        tagline = from.tagline,
        posterPath = from.posterPath,
        releaseDate = from.releaseDate,
        title = from.title,
        originalTitle = from.originalTitle,
        voteAverage = from.voteAverage,
        voteCount = from.voteCount,
        genre = from.genres.map {
            GenreEntity(it.id,it.name)
        },
        video = from.video,
        popularity = from.popularity,
        adult = from.adult
    )
}