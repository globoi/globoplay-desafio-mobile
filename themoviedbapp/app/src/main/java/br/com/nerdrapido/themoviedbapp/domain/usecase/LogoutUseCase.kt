package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.login.DeleteAccessTokenRequest
import br.com.nerdrapido.themoviedbapp.data.repository.login.LoginRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 11/03/2020
 */
class LogoutUseCase(private val loginRepository: LoginRepository, private val sessionRepository: SessionRepository ) {

    suspend fun execute(): Boolean {
        var result = true
        val sessionId = sessionRepository.getSessionId()
        if (sessionId != null) {
            val deleteAccessTokenResponse = loginRepository.deleteAccessToken(DeleteAccessTokenRequest(
                sessionId
            ))
            result = deleteAccessTokenResponse.success
        }
        sessionRepository.setSessionID(null)
        return result
    }
}