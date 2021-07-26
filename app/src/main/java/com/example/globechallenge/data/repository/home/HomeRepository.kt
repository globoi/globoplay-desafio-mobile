package com.example.globechallenge.data.repository.home

import com.example.globechallenge.data.model.models.home.Genre
import com.example.globechallenge.data.model.models.home.Movie
import com.example.globechallenge.data.model.models.home.MovieToGenre

interface HomeRepository {
    suspend fun getGenre(): List<Genre>
    suspend fun getMovie(): List<Movie>
    suspend fun getMovieByGenre(): ArrayList<MovieToGenre>
    suspend fun selectMovieToGenre(id: Int): MutableList<Movie>
}
