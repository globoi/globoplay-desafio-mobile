package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper
import br.com.nerdrapido.themoviedbapp.data.model.login.DeleteAccessTokenRequest
import br.com.nerdrapido.themoviedbapp.data.repository.login.LoginRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 11/03/2020
 */
class LogoutUseCase(
    private val loginRepository: LoginRepository,
    private val sessionRepository: SessionRepository
) {

    suspend fun execute(): Boolean {
        var result = true
        val sessionId = sessionRepository.getSessionId()
        if (sessionId != null) {
            val deleteAccessTokenResponse = loginRepository.deleteAccessToken(
                DeleteAccessTokenRequest(
                    sessionId
                )
            )
            result = when (deleteAccessTokenResponse) {
                is ResponseWrapper.Success -> deleteAccessTokenResponse.value.success
                else -> false
            }
        }
        deleteSession()

        return result
    }

    private fun deleteSession() {
        sessionRepository.setSessionId(null)
        sessionRepository.setIso6391(null)
        sessionRepository.setIso3161(null)
        sessionRepository.setName(null)
        sessionRepository.setIncludeAdult(false)
        sessionRepository.setUserName(null)
        sessionRepository.setAccountId(0)
    }
}