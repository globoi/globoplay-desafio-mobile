package me.davidpcosta.tmdb.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import me.davidpcosta.tmdb.data.api.Api
import me.davidpcosta.tmdb.data.model.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoviesRepository(private val api: Api) {

    fun genres(): LiveData<List<Genre>> {
        val genres = MutableLiveData<List<Genre>>()

        api.genres().enqueue(object: Callback<Generes> {
            override fun onResponse(call: Call<Generes>, response: Response<Generes>) {
                response.body()?.let {
                    genres.value = it.genres
                }
            }
            override fun onFailure(call: Call<Generes>, t: Throwable) {
                throw t
            }
        })

        return genres
    }

    fun moviesByGenre(genreId: Long): LiveData<List<Movie>> {
        val result = MutableLiveData<List<Movie>>()

        api.moviesByGenre(genreId).enqueue(object: Callback<PagedResult<Movie>> {
            override fun onResponse(call: Call<PagedResult<Movie>>, response: Response<PagedResult<Movie>>) {
                response.body()?.let {
                    result.value = it.results
                }
            }
            override fun onFailure(call: Call<PagedResult<Movie>>, t: Throwable) {
                throw t
            }
        })

        return result
    }

    fun similarMovies(movieId: Long): LiveData<List<Movie>> {
        val result = MutableLiveData<List<Movie>>()

        api.similarMovies(movieId).enqueue(object: Callback<PagedResult<Movie>> {
            override fun onResponse(call: Call<PagedResult<Movie>>, response: Response<PagedResult<Movie>>) {
                response.body()?.let {
                    result.value = it.results
                }
            }
            override fun onFailure(call: Call<PagedResult<Movie>>, t: Throwable) {
                throw t
            }
        })

        return result
    }

    fun credits(movieId: Long): LiveData<List<Cast>> {
        val credits = MutableLiveData<List<Cast>>()

        api.movieCredits(movieId).enqueue(object: Callback<Credits> {
            override fun onResponse(call: Call<Credits>, response: Response<Credits>) {
                response.body()?.let {
                    credits.value = it.cast
                }
            }
            override fun onFailure(call: Call<Credits>, t: Throwable) {
                throw t
            }
        })

        return credits
    }

    fun movieDetails(movieId: Long): LiveData<MovieDetails> {
        val movieDetails = MutableLiveData<MovieDetails>()

        api.movieDetails(movieId).enqueue(object: Callback<MovieDetails> {
            override fun onResponse(call: Call<MovieDetails>, response: Response<MovieDetails>) {
                response.body()?.let {
                    movieDetails.value = response.body()
                }
            }
            override fun onFailure(call: Call<MovieDetails>, t: Throwable) {
                throw t
            }
        })

        return movieDetails
    }

}
