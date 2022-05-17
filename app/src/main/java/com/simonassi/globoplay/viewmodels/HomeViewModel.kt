package com.simonassi.globoplay.viewmodels

import androidx.lifecycle.*
import androidx.paging.*
import com.simonassi.globoplay.api.TMDBService
import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.TMDBRepository
import com.simonassi.globoplay.data.movie.MoviePagingSource
import com.simonassi.globoplay.data.tv.Tv
import com.simonassi.globoplay.data.tv.TvPagingSource
import com.simonassi.globoplay.utilities.Generator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: TMDBRepository,
    private val service: TMDBService
) : ViewModel() {

    val trendingLiveData = MutableLiveData<List<Movie>>()

    fun getMovies(): Flow<PagingData<Movie>> {
        return Pager(config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = { MoviePagingSource(service) })
            .flow.cachedIn(viewModelScope)
    }

    fun getTvs(): Flow<PagingData<Tv>> {
        return Pager(config = PagingConfig(pageSize = 10, maxSize = 200),
            pagingSourceFactory = { TvPagingSource(service) })
            .flow.cachedIn(viewModelScope)
    }


    fun getTrending() {
        CoroutineScope(Dispatchers.Main).launch {
            val movies = withContext(Dispatchers.Default) {
                repository.getTrendingMovies()
            }
            val homeMovies: MutableList<Movie> = movies.subList(0, 4) as MutableList<Movie>
            trendingLiveData.value = homeMovies
        }
    }
}