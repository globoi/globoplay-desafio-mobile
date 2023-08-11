package br.com.favorites.domain.mappers

import br.com.common.domain.model.Movie
import br.com.common.util.Mapper
import br.com.local.model.MovieEntity
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.local.model.movie_details.MovieDetailsEntity
import br.com.network.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject



class FavoritesMovieToEntityMapper @Inject constructor() :
    Mapper< MovieDetailsEntity,FavoritiesMovieEntity> {

    override suspend fun map(from: MovieDetailsEntity): FavoritiesMovieEntity {

        val movie = MovieEntity(
            adult = from.adult,
            genreIds  = from.genre.map { it.id },
            originalTitle  = from.originalTitle,
            overview  = from.description,
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