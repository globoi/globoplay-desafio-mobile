package com.com.globo.repository

import com.com.globo.repository.model.MoviesTitleCategory

interface MoviesTitleCategoryRepository {
    fun getListMoviesTitleCategory(): MutableList<MoviesTitleCategory>
}