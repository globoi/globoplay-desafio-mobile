package br.com.details_movie.domain.mappers

import br.com.common.util.BASE_URL_IMAGE
import br.com.common.util.Mapper
import br.com.details_movie.domain.model.MovieDetails
import br.com.local.model.movie_details.MovieDetailsEntity
import br.com.network.BuildConfig
import java.text.SimpleDateFormat
import java.util.Locale
import javax.inject.Inject

const val FORMAT_DATE_MOVIE_RELEASE_PARSER = "yyyy-MM-dd"
private const val FORMAT_DATE_MOVIE_RELEASE_FORMATTER = "yyyy"
private const val SEPARATOR_SUBTITLE = " | "
private const val CHAR_STAR = '★'

class MovieToDomainMapper @Inject constructor() : Mapper<MovieDetailsEntity, MovieDetails> {

    override suspend fun map(from: MovieDetailsEntity): MovieDetails {

        val parser = SimpleDateFormat(FORMAT_DATE_MOVIE_RELEASE_PARSER, Locale.ENGLISH)
        val formatter = SimpleDateFormat(FORMAT_DATE_MOVIE_RELEASE_FORMATTER, Locale.ENGLISH)
        val releaseDate = from.releaseDate?.let { releaseDate ->
            parser.parse(releaseDate)?.let {
                formatter.format(it)
            }
        }.orEmpty()
        return MovieDetails(
            id = from.id,
            overview = from.overview.orEmpty(),
            tagline = from.tagline.orEmpty(),
            posterPath = BASE_URL_IMAGE + from.posterPath,
            title = from.title.orEmpty(),
            subtitle = listOfNotNull(
                releaseDate,
                from.genre.joinToString(separator = " | ") {  it.name },
                from.voteAverage?.let { voteAverage ->
                    String.format(Locale.ENGLISH, "%.1f", voteAverage).plus(CHAR_STAR)
                }
            ).joinToString(SEPARATOR_SUBTITLE),
        )
    }
}
