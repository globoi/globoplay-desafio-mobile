package br.com.details_movie.domain.mappers

import br.com.common.util.Mapper
import br.com.details_movie.domain.model.Movie
import br.com.local.model.movie_details.MovieEntity
import br.com.network.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

const val FORMAT_DATE_MOVIE_RELEASE_PARSER = "yyyy-MM-dd"
private const val FORMAT_DATE_MOVIE_RELEASE_FORMATTER = "yyyy"
private const val SEPARATOR_SUBTITLE = " | "
private const val CHAR_STAR = '★'

class MovieToDomainMapper @Inject constructor() : Mapper<MovieEntity, Movie> {

    override suspend fun map(from: MovieEntity): Movie {

        val parser = SimpleDateFormat(FORMAT_DATE_MOVIE_RELEASE_PARSER, Locale.ENGLISH)
        val formatter = SimpleDateFormat(FORMAT_DATE_MOVIE_RELEASE_FORMATTER, Locale.ENGLISH)
        val releaseDate = from.releaseDate?.let { releaseDate ->
            parser.parse(releaseDate)?.let {
                formatter.format(it)
            }
        }.orEmpty()
        return Movie(
            id = from.id,
            description = from.description.orEmpty(),
            tagline = from.tagline.orEmpty(),
            posterUrl = BuildConfig.BASE_URL_IMAGE + from.posterPath,
            title = from.title.orEmpty(),
            subtitle = listOfNotNull(
                releaseDate,
                from.genre,
                from.voteAverage?.let { voteAverage ->
                    String.format(Locale.ENGLISH, "%.1f", voteAverage).plus(CHAR_STAR)
                }
            ).joinToString(SEPARATOR_SUBTITLE),
        )
    }
}
