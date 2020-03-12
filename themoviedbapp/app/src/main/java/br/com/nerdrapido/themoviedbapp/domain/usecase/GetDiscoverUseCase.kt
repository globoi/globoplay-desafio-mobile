package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverRequest
import br.com.nerdrapido.themoviedbapp.data.repository.discover.DiscoverRepository

/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class GetDiscoverUseCase(private val discoverRepository: DiscoverRepository) {

    suspend fun execute(discoverRequest: DiscoverRequest): List<MovieListResultObject> {
        discoverRequest.sortBy = "popularity.desc"
        return discoverRepository.loadDiscover(discoverRequest).results!!

    }
}