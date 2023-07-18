package com.nunkison.globoplaymobilechallenge.repo

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nunkison.globoplaymobilechallenge.getYear
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
    private val prefs: SharedPreferences
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
            id = it.id,
            name = it.original_title,
            coverPath = thumbImage(it.poster_path),
            category = genreToCommaString(it.genres),
            description = it.overview,
            isFavorite = isFavorite(id),
            year = getYear(it.release_date),
            country = productionCountriesToCommaString(it.production_countries),
            producer = productionCompaniesToCommaString(it.production_companies),
            youtubeKey = getYoutubeKey(it.id) ?: "",
            relatedMovies = getRelatedMovies(it.genres),
            revenue = it.revenue,
            vote_average = it.vote_average,
            runtime = it.runtime,
            budget = it.budget
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

    override suspend fun addFavorite(id: String) {
        editFavorites {
            it.add(id)
        }
    }

    override suspend fun removeFavorite(id: String) {
        editFavorites {
            it.remove(id)
        }
    }

    private fun getCurrentFavorites() = Gson().fromJson<LinkedHashSet<String>>(
        prefs.getString(FAVORITES_SHARED_PREF_KEY, "[]"),
        object : TypeToken<LinkedHashSet<String>>() {}.type
    )

    private fun isFavorite(id: String) = getCurrentFavorites().contains(id)

    private fun editFavorites(edit: (favorites: MutableSet<String>) -> Unit) {
        prefs.edit().putString(
            FAVORITES_SHARED_PREF_KEY,
            Gson().toJson(
                getCurrentFavorites().toMutableSet().also {
                    edit(it)
                }
            )
        ).apply()
    }

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
            it.name
        }.joinToString(", ")

    private fun productionCompaniesToCommaString(companies: List<ProductionCompany>) =
        companies.mapTo(arrayListOf()) {
            it.name
        }.joinToString(", ")

    companion object {
        const val FAVORITES_SHARED_PREF_KEY = "FAVORITES_SHARED_PREF_KEY"
    }
}


