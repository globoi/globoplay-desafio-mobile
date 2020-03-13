package br.com.nerdrapido.themoviedbapp.data.repository.discover

import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverRequest
import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverResponse
import br.com.nerdrapido.themoviedbapp.data.repository.abstracts.AbstractMovieDbApiRepos
import retrofit2.Retrofit

/**
 * Created By FELIPE GUSBERTI @ 11/03/2020
 */
class DiscoverRepositoryImpl(retrofit: Retrofit) : AbstractMovieDbApiRepos(retrofit), DiscoverRepository {

    private val authService: DiscoverService = retrofit.create(DiscoverService::class.java)

    override suspend fun loadDiscover(discoverRequest: DiscoverRequest): DiscoverResponse {
       return authService.discoverMovies(
           discoverRequest.sortBy,
           discoverRequest.page.toString(),
           discoverRequest.withGenres,
           discoverRequest.language,
           discoverRequest.region
       )
    }
}