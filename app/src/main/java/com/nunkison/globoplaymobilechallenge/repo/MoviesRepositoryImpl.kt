package com.nunkison.globoplaymobilechallenge.repo

import com.nunkison.globoplaymobilechallenge.project.api.TmdbService
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesRepository
import com.nunkison.globoplaymobilechallenge.thumb
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MovieCover
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup
import retrofit2.Retrofit

class MoviesRepositoryImpl(
    private val api: Retrofit
) : MoviesRepository {
    override suspend fun getMovies() = arrayListOf<MoviesGroup>().apply {
        api.create(TmdbService::class.java)
            .genreList().body()?.genres?.map {
                add(MoviesGroup(
                    category = it.name,
                    movieCovers = api.create(TmdbService::class.java)
                        .discoverMovie(it.id).body()?.results?.mapTo(arrayListOf()) { dm ->
                            MovieCover(
                                id = dm.id,
                                name = dm.title,
                                cover = thumb(dm.poster_path)
                            )
                        } ?: arrayListOf()
                ))
            }
    }
}