package com.app.fakegloboplay.features.home.repository

import com.app.fakegloboplay.network.APIPlay
import com.app.fakegloboplay.network.response.AccountResponse
import com.app.fakegloboplay.network.response.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HomeRepository(
    private val remoteData: APIPlay
) {
    suspend fun getTvPopular(page:Int): Flow<MovieListPage> =
        flow {
            val listPage = MovieListPage()
            val body = remoteData.getTvPopular(page).body()
            body?.results?.forEach {
                it.mediaType = MEDIA_TYPE_TV
            }
            body?.let {
                listPage.page = it.page
                listPage.results = it.results
                listPage.totalPage = it.totalPage
            }
             emit(listPage)
        }

    suspend fun getMoviesPopular(page:Int): Flow<MovieListPage> =
        flow {
            val listPage = MovieListPage()
            val body = remoteData.getMoviePopular(page).body()
            body?.results?.forEach {
                it.mediaType = MEDIA_TYPE_MOVIE
            }
            body?.let {
                listPage.page = it.page
                listPage.results = it.results
                listPage.totalPage = it.totalPage
            }
            emit(listPage)
        }

    suspend fun getAccount(): Flow<AccountResponse?> =
        flow {
            emit(remoteData.getAccount().body())
        }

    companion object {
        private const val MEDIA_TYPE_TV = "tv"
        private const val MEDIA_TYPE_MOVIE = "movie"
    }
}