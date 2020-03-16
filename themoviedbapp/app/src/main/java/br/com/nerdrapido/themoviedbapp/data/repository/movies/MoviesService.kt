package br.com.nerdrapido.themoviedbapp.data.repository.movies

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.model.movieaccountstates.MovieAccountStateResponse
import br.com.nerdrapido.themoviedbapp.data.model.movievideo.MovieVideoResponse
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationResponse
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
        @Query("language") language: String?
    ): MovieResponse

    /**
     * https://developers.themoviedb.org/3/movies/get-movie-recommendations
     */
    @GET("movie/{movie_id}/recommendations")
    suspend fun moviesRecommendation(
        @Path("movie_id") movieId: String?,
        @Query("language") language: String?,
        @Query("page") page: Int?
    ): RecommendationResponse

    /**
     * https://developers.themoviedb.org/3/movies/get-movie-account-states
     */
    @GET("movie/{movie_id}/account_states")
    suspend fun movieAccountState(
        @Path("movie_id") movieId: String?,
        @Query("session_id") language: String?
    ): MovieAccountStateResponse

    /**
     * https://developers.themoviedb.org/3/movies/get-movie-videos
     */
    @GET("movie/{movie_id}/videos")
    suspend fun movieVideos(
        @Path("movie_id") movieId: String?,
        @Query("language") language: String?
    ): MovieVideoResponse

}