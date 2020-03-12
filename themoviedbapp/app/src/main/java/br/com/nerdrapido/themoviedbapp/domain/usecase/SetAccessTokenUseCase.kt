package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionResponse
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class SetAccessTokenUseCase(private val sessionRepository: SessionRepository) {

    fun execute(createSessionResponse: CreateSessionResponse) {
        sessionRepository.setSessionID(createSessionResponse.sessionId)
    }

}