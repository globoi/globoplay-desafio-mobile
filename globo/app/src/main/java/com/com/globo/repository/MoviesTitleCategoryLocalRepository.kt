package com.com.globo.repository

import com.com.globo.repository.model.MoviesTitleCategory

class MoviesTitleCategoryLocalRepository : MoviesTitleCategoryRepository {

    override fun getListMoviesTitleCategory(): MutableList<MoviesTitleCategory> {
        return MoviesTitleCategory.values().toMutableList()
    }
}