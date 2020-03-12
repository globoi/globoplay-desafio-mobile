package br.com.nerdrapido.themoviedbapp.data.repository.discover

import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverRequest
import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverResponse

/**
 * Created By FELIPE GUSBERTI @ 11/03/2020
 */
interface DiscoverRepository {

    suspend fun loadDiscover(discoverRequest: DiscoverRequest): DiscoverResponse
}