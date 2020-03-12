package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class GetLogInStateUseCase(private val sessionRepository: SessionRepository) {

    /**
     * Returns true if the user is logged based on the AccessToken value stored
     */
    fun isLoggedIn(): Boolean {
        return sessionRepository.getSessionId() !== null
    }
}