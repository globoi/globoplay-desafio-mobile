package com.app.fakegloboplay.features.detailmovie.repository

import com.app.fakegloboplay.network.APIPlay
import com.app.fakegloboplay.network.response.DetailsMove
import com.app.fakegloboplay.network.response.Movie
import com.app.fakegloboplay.network.response.MyFavorite
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext

class DetailMoveRepository(
    private val remoteData: APIPlay
) {

    suspend fun getRecommendations(id: Int, mediaType: String?, page: Int): Flow<List<Movie>?> =
        flow {
            when (mediaType) {
                MEDIA_TYPE -> {
                    val results = remoteData.getTvRecommendations(id, page).body()?.results
                    emit(results)
                }

                MEDIA_TYPE_MOVIE -> {
                    val results = remoteData.getMovieRecommendations(id, page).body()?.results
                    emit(results)
                }
            }

        }

    suspend fun postMyFavorite(myFavorite: MyFavorite) =
        withContext(Dispatchers.IO) {
            remoteData.postMyFavorite(myFavorite).body()
        }

    suspend fun getDetails(seriesId: Int, mediaType: String?): Flow<DetailsMove?> =
        flow {
            when (mediaType) {
                MEDIA_TYPE -> {
                    val credits = remoteData.getCreditsTv(seriesId).body()
                    val detail = remoteData.getDetailsTv(seriesId).body()
                    detail?.credits = credits
                    emit(detail)
                }

                MEDIA_TYPE_MOVIE -> {
                    val credits = remoteData.getCreditsMovie(seriesId).body()
                    val detail = remoteData.getDetailsMovie(seriesId).body()
                    detail?.credits = credits
                    emit(detail)
                }
            }

        }

    companion object {
        private const val MEDIA_TYPE = "tv"
        private const val MEDIA_TYPE_MOVIE = "movie"
    }
}