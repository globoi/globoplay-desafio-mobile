package com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.domain.use_cases.GetMovieListUseCase
import com.mazer.globoplayapp.utils.AppConstants
import kotlinx.coroutines.launch

class RecommendationViewModel(private val getMovieListUseCase: GetMovieListUseCase): ViewModel() {

    private val _recommendationList = MutableLiveData<List<Movie>>()
    val recommendationList: LiveData<List<Movie>> = _recommendationList

    fun loadRecommendationList(movieId: Int) {
        viewModelScope.launch {
            val movies = getMovieListUseCase.getRecommendationList(movieId)
            _recommendationList.postValue(movies)
        }
    }

    fun setExtras(bundle: Bundle){
        val movieId = bundle.getInt(AppConstants.RECOMENDATION_EXTRA)
        loadRecommendationList(movieId)
    }
}