package com.mazer.globoplayapp.presentation.ui.details.tabs.details

import android.os.Build
import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.utils.AppConstants

class DetailsViewModel: ViewModel() {

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> = _movieDetails


    fun setExtras(bundle: Bundle){
        val movie = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            bundle.getParcelable(AppConstants.DETAILS_EXTRA, Movie::class.java)
        } else {
            bundle.getParcelable(AppConstants.DETAILS_EXTRA)
        }
        _movieDetails.postValue(movie ?: return)
    }
}