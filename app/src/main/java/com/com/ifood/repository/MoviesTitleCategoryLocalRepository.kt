package com.com.ifood.repository

import com.com.ifood.repository.model.MoviesTitleCategory

class MoviesTitleCategoryLocalRepository : MoviesTitleCategoryRepository {

    override fun getListMoviesTitleCategory(): MutableList<MoviesTitleCategory> {
        return MoviesTitleCategory.values().toMutableList()
    }
}