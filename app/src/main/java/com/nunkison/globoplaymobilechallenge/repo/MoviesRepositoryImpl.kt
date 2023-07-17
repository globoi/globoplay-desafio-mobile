package com.nunkison.globoplaymobilechallenge.repo

import com.nunkison.globoplaymobilechallenge.getYear
import com.nunkison.globoplaymobilechallenge.originalImage
import com.nunkison.globoplaymobilechallenge.project.api.DiscoverMovieResponse
import com.nunkison.globoplaymobilechallenge.project.api.Genre
import com.nunkison.globoplaymobilechallenge.project.api.ProductionCompany
import com.nunkison.globoplaymobilechallenge.project.api.ProductionCountry
import com.nunkison.globoplaymobilechallenge.project.api.TmdbService
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesRepository
import com.nunkison.globoplaymobilechallenge.thumbImage
import com.nunkison.globoplaymobilechallenge.ui.movie_detail.data.MovieDetailData
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MovieCover
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup

class MoviesRepositoryImpl(
    private val service: TmdbService,
) : MoviesRepository {

    override suspend fun getMovies(): List<MoviesGroup> =
        service.genreList().body()?.genres?.mapTo(arrayListOf()) {
            MoviesGroup(
                category = it.name,
                movieCovers = mapToMovieCover(
                    service.discoverMovie(it.id).body()?.results
                )
            )
        } ?: arrayListOf()

    override suspend fun getMovie(id: String) = service.movie(id).body()?.let {
        MovieDetailData(
            name = it.original_title,
            coverPath = originalImage(it.poster_path),
            category = genreToCommaString(it.genres),
            description = it.overview,
            isFavorite = false,
            year = getYear(it.release_date),
            country = productionCountriesToCommaString(it.production_countries),
            producer = productionCompaniesToCommaString(it.production_companies),
            youtubeKey = getYoutubeKey(it.id) ?: "",
            relatedMovies = getRelatedMovies(it.genres),
            tabSelected = 0,
        )
    }

    override suspend fun getRelatedMovies(genres: List<Genre>) = arrayListOf<MovieCover>().apply {
        genres.map { genre ->
            addAll(
                mapToMovieCover(
                    service.discoverMovie(genre.id).body()?.results
                )
            )
        }
    }

    override suspend fun getYoutubeKey(id: String) =
        service.movieVideos(id).body()?.results?.firstOrNull {
            it.site == "YouTube"
        }?.key

    private fun mapToMovieCover(
        results: List<DiscoverMovieResponse.DiscoverMovie>?
    ) = results?.mapTo(arrayListOf()) { dm ->
        MovieCover(
            id = dm.id,
            name = dm.title,
            cover = thumbImage(dm.poster_path)
        )
    } ?: arrayListOf()

    private fun genreToCommaString(genres: List<Genre>) =
        genres.mapTo(arrayListOf()) {
            it.name
        }.joinToString(", ")

    private fun productionCountriesToCommaString(genres: List<ProductionCountry>) =
        genres.mapTo(arrayListOf()) {
            it.iso_3166_1
        }.joinToString(", ")

    private fun productionCompaniesToCommaString(companies: List<ProductionCompany>) =
        companies.mapTo(arrayListOf()) {
            it.name
        }.joinToString(", ")

}


