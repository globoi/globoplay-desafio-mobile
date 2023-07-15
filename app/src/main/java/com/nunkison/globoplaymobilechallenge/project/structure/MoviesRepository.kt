package com.nunkison.globoplaymobilechallenge.project.structure

import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup

interface MoviesRepository {
    suspend fun getMovies(): List<MoviesGroup>
}