package com.tiagopereira.globotmdb.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tiagopereira.globotmdb.data.DetailsResponse
import com.tiagopereira.globotmdb.database.entities.Movie
import com.tiagopereira.globotmdb.viewmodel.repository.DetailsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailsMovieViewModel constructor(private val repository: DetailsRepository) : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _star = MutableLiveData<Boolean>()
    val star: LiveData<Boolean> = _star

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    private val _detailsMovie = MutableLiveData<DetailsResponse>()
    val detailsMovie: LiveData<DetailsResponse> = _detailsMovie

    fun loadDetailsMovie(id: Int) = viewModelScope.launch {
        _loading.postValue(true)
        val response = repository.getMovieDetails(id)
        if (response.isSuccessful) {
            _detailsMovie.postValue(response.body())
        }
        _loading.postValue(false)
    }

    fun favorite(response: DetailsResponse) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = Movie(
                id = response.id,
                adult = response.adult,
                backdropPath = response.backdropPath,
                budget = response.budget,
                homepage = response.homepage,
                imdbId = response.imdbId,
                originalLanguage = response.originalLanguage,
                originalTitle = response.originalTitle,
                overview = response.overview,
                popularity = response.popularity,
                posterPath = response.posterPath,
                releaseDate = response.releaseDate,
                revenue = response.revenue,
                runtime = response.runtime,
                status = response.status,
                tagline = response.tagline,
                title = response.title,
                video = response.video,
                voteAverage = response.voteAverage,
                voteCount = response.voteCount,
                visible = false
            )

            repository.insert(movie)

            _star.postValue(true)
            _isFavorite.postValue(true)
        }
    }

    fun removeFavorite(idMovie: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            repository.removeById(idMovie)
            _isFavorite.postValue(false)
            _star.postValue(false)
        }
    }

    fun isFavorite(idMovie: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            val movie = repository.isFavorite(idMovie)
            if (movie != null) {
                _star.postValue(true)
                _isFavorite.postValue(true)
            } else {
                _star.postValue(false)
                _isFavorite.postValue(false)
            }
        }
    }
}