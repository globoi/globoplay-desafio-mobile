package com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation

import com.mazer.globoplayapp.domain.entities.Movie

interface GetRecommendation {
    fun onGetMovieList(recommendationList: List<Movie>)
}