package me.davidpcosta.tmdb.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.davidpcosta.tmdb.data.api.Api
import me.davidpcosta.tmdb.data.dao.MovieDao
import me.davidpcosta.tmdb.data.model.Media
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.data.model.PagedResult
import me.davidpcosta.tmdb.data.model.WatchlistOperationResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchlistRepository(private val api: Api, private val movieDao: MovieDao) {

    fun watchlist(accountId: Long, sessionId: String): LiveData<List<Movie>> {
        val movies = MutableLiveData<List<Movie>>()

        api.watchlist(accountId, sessionId).enqueue(object: Callback<PagedResult<Movie>> {
            override fun onResponse(call: Call<PagedResult<Movie>>, response: Response<PagedResult<Movie>>) {
                response.body()?.let {
                    movies.value = it.results

                    // Refresh na watchlist para garantir sincronização com web
                    movieDao.deleteAll()
                    movieDao.insertAll(it.results)
                }
            }
            override fun onFailure(call: Call<PagedResult<Movie>>, t: Throwable) {
                throw t
            }
        })

        return movies
    }

    fun localWatchlist(): List<Movie> {
        return movieDao.getAll()
    }

    fun addToWatchlist(accountId: Long, sessionId: String, movie: Movie): LiveData<WatchlistOperationResponse> {
        val operationResponse = MutableLiveData<WatchlistOperationResponse>()

        val m = Media(mediaType = "movie", mediaId = movie.id, watchlist = true)
        api.addToWatchlist(accountId = accountId, sessionId = sessionId, media = m).enqueue(object: Callback<WatchlistOperationResponse> {
            override fun onResponse(call: Call<WatchlistOperationResponse>, response: Response<WatchlistOperationResponse>) {
                response.body()?.let {
                    if (it.statusCode == 1 || it.statusCode == 12) {
                        movieDao.insert(movie)
                        operationResponse.value = it
                    }
                }
            }
            override fun onFailure(call: Call<WatchlistOperationResponse>, t: Throwable) {
                throw t
            }
        })

        return operationResponse
    }

    fun removeFromWatchlist(accountId: Long, sessionId: String, movie: Movie): LiveData<WatchlistOperationResponse> {
        val operationResponse = MutableLiveData<WatchlistOperationResponse>()

        val m = Media(mediaType = "movie", mediaId = movie.id, watchlist = false)
        api.addToWatchlist(accountId = accountId, sessionId = sessionId, media = m).enqueue(object: Callback<WatchlistOperationResponse> {
            override fun onResponse(call: Call<WatchlistOperationResponse>, response: Response<WatchlistOperationResponse>) {
                response.body()?.let {
                    if (it.statusCode == 13) {
                        movieDao.delete(movie)
                        operationResponse.value = it
                    }
                }
            }
            override fun onFailure(call: Call<WatchlistOperationResponse>, t: Throwable) {
                throw t
            }
        })

        return operationResponse
    }

    fun isInWatchlist(movie: Movie): Boolean {
        return movieDao.findById(movie.id) != null
    }

}
