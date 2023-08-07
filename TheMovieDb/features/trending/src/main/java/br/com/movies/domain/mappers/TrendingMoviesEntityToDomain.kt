package br.com.movies.domain.mappers

import br.com.common.util.Mapper
import br.com.local.model.TrendingMovieEntity
import br.com.movies.data.remote.dto.TrendingMoviesDto
import br.com.movies.domain.model.TrendingMovies
import br.com.network.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

private const val FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER = "dd MMM yyyy"
const val FORMAT_DATE_MOVIE_RELEASE_PARSER = "yyyy-MM-dd"

class TrendingMoviesEntityToDomain @Inject constructor() :
    Mapper<TrendingMovieEntity, TrendingMovies> {

    override suspend fun map(from: TrendingMovieEntity): TrendingMovies {
        val parser = SimpleDateFormat(FORMAT_DATE_MOVIE_RELEASE_PARSER, Locale.ENGLISH)
        val formatter = SimpleDateFormat(FORMAT_DATE_TRENDING_MOVIE_RELEASE_FORMATTER, Locale.ENGLISH)

        return TrendingMovies(
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
            posterPath  = BuildConfig.BASE_URL_IMAGE+ from.posterPath,
            releaseDate  =  runCatching {
                parser.parse(from.releaseDate.orEmpty())
            }.getOrNull()?.let { releaseDate ->
                formatter.format(releaseDate)
            }.orEmpty(),
            title  = from.title,
            video  = from.video,
            voteAverage  = from.voteAverage?.let { voteAverage ->
                String.format(Locale.ENGLISH, "%.1f", voteAverage)
            }.orEmpty(),
            voteCount  = from.voteCount)
    }
}