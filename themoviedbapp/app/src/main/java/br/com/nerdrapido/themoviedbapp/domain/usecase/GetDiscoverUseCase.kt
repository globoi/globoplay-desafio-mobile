package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.common.Genres
import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper
import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverRequest
import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverResponse
import br.com.nerdrapido.themoviedbapp.data.repository.discover.DiscoverRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class GetDiscoverUseCase(
    private val getLanguageUseCase: GetLanguageUseCase,
    private val sessionRepository: SessionRepository,
    private val discoverRepository: DiscoverRepository
) {

    suspend fun getDiscover(page: Int, genre: Genres? = null): ResponseWrapper<DiscoverResponse> {
        val discoverRequest = DiscoverRequest(
            "popularity.desc",
            page,
            genre?.id?.toString() ?: "",
            getLanguageUseCase.getLanguage(),
            sessionRepository.getIso31661()

        )
        return discoverRepository.loadDiscover(discoverRequest)
    }
}