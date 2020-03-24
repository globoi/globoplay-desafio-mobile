package br.com.nerdrapido.themoviedbapp.data.repository.discover

import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverResponse
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created By FELIPE GUSBERTI @ 11/03/2020
 */
interface DiscoverService {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("sort_by") sortBy: String?,
        @Query("page") page: String?,
        @Query("with_genres") withGenres: String?,
        @Query("language") language: String?,
        @Query("region") region: String?
    ): DiscoverResponse

}