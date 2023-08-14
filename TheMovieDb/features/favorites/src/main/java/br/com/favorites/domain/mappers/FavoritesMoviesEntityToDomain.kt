package br.com.favorites.domain.mappers

import android.util.Log
import br.com.common.domain.model.Movie
import br.com.common.util.BASE_URL_IMAGE
import br.com.common.util.Mapper
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.network.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject


private const val FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER = "dd MMM yyyy"
const val FORMAT_DATE_MOVIE_RELEASE_PARSER = "yyyy-MM-dd"

class FavoritesMoviesEntityToDomain @Inject constructor() :
    Mapper<FavoritiesMovieEntity, Movie> {

    override suspend fun map(from: FavoritiesMovieEntity): Movie {
        val parser = SimpleDateFormat(FORMAT_DATE_MOVIE_RELEASE_PARSER, Locale.ENGLISH)
        val formatter = SimpleDateFormat(FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER, Locale.ENGLISH)
        val movieEntity = from.movie
        return Movie(
            adult = movieEntity.adult,
            backdropPath  = movieEntity.backdropPath,
            firstAirDate  = movieEntity.firstAirDate,
            genreIds  = movieEntity.genreIds,
            id  = from.id,
            mediaType  = movieEntity.mediaType,
            name  = movieEntity.name,
            originCountry  =  movieEntity.originCountry ,
            originalLanguage  =  movieEntity.originalLanguage,
            originalName  = movieEntity.originalName,
            originalTitle  = movieEntity.originalTitle,
            overview  = movieEntity.overview,
            popularity  = movieEntity.popularity,
            posterPath  = BASE_URL_IMAGE+ movieEntity.posterPath,
            releaseDate  =  runCatching {
                parser.parse(movieEntity.releaseDate.orEmpty())
            }.getOrNull()?.let { releaseDate ->
                formatter.format(releaseDate)
            }.orEmpty(),
            title  = movieEntity.title,
            video  = movieEntity.video,
            voteAverage  = movieEntity.voteAverage?.let { voteAverage ->
                String.format(Locale.ENGLISH, "%.1f", voteAverage)
            }.orEmpty(),
            voteCount  = movieEntity.voteCount)
    }
}