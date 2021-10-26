package com.com.ifood.repository

import com.com.ifood.repository.model.MoviesTitleCategory

interface MoviesTitleCategoryRepository {
    fun getListMoviesTitleCategory(): MutableList<MoviesTitleCategory>
}