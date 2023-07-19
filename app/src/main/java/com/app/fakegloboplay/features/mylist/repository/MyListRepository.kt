package com.app.fakegloboplay.features.mylist.repository

import com.app.fakegloboplay.network.APIPlay
import com.app.fakegloboplay.network.response.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MyListRepository(
    private val remoteData: APIPlay
) {
    suspend fun getMyFavorite(): Flow<List<Movie>?> =
        flow {
            val resultsTv = remoteData.getMyFavoriteTv().body()?.results
            resultsTv?.forEach {
                it.mediaType = MEDIA_TYPE_TV
            }
            val resultsMovie = remoteData.getMyFavoriteMovie().body()?.results
            resultsMovie?.forEach {
                it.mediaType = MEDIA_TYPE_MOVIE
            }
            val results = resultsTv.orEmpty() + resultsMovie.orEmpty()
            emit(results)
        }

    companion object {
        private const val MEDIA_TYPE_TV = "tv"
        private const val MEDIA_TYPE_MOVIE = "movie"
    }
}