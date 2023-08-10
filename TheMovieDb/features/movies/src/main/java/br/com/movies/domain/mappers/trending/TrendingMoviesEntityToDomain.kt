package br.com.movies.domain.mappers.trending

import br.com.common.util.Mapper
import br.com.local.model.trending.TrendingMovieEntity
import br.com.common.domain.model.Movie
import br.com.network.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

private const val FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER = "dd MMM yyyy"
const val FORMAT_DATE_MOVIE_RELEASE_PARSER = "yyyy-MM-dd"

class TrendingMoviesEntityToDomain @Inject constructor() :
    Mapper<TrendingMovieEntity, Movie> {

    override suspend fun map(from: TrendingMovieEntity): Movie {
        val parser = SimpleDateFormat(FORMAT_DATE_MOVIE_RELEASE_PARSER, Locale.ENGLISH)
        val formatter = SimpleDateFormat(FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER, Locale.ENGLISH)
        val movie = from.movie
        return Movie(
            adult = movie.adult,
            backdropPath  = movie.backdropPath,
            firstAirDate  = movie.firstAirDate,
            genreIds  = movie.genreIds,
            id  = from.id,
            mediaType  = movie.mediaType,
            name  = movie.name,
            originCountry  =  movie.originCountry ,
            originalLanguage  =  movie.originalLanguage,
            originalName  = movie.originalName,
            originalTitle  = movie.originalTitle,
            overview  = movie.overview,
            popularity  = movie.popularity,
            posterPath  = BuildConfig.BASE_URL_IMAGE+ movie.posterPath,
            releaseDate  =  runCatching {
                parser.parse(movie.releaseDate.orEmpty())
            }.getOrNull()?.let { releaseDate ->
                formatter.format(releaseDate)
            }.orEmpty(),
            title  = movie.title,
            video  = movie.video,
            voteAverage  = movie.voteAverage?.let { voteAverage ->
                String.format(Locale.ENGLISH, "%.1f", voteAverage)
            }.orEmpty(),
            voteCount  = movie.voteCount)
    }
}