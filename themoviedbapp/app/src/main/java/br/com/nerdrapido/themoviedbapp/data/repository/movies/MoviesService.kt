package br.com.nerdrapido.themoviedbapp.data.repository.movies

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
interface MoviesService {

    /**
     * https://developers.themoviedb.org/3/movies/get-movie-details
     */
    @GET("movie/{movie_id}")
    suspend fun moviesDetail(
        @Path("movie_id") movieId: String?,
        @Query("language") language: String
    ): MovieResponse

}