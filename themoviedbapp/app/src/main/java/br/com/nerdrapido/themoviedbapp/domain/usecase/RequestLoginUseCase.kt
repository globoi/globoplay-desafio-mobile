package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenResponse
import br.com.nerdrapido.themoviedbapp.data.repository.login.LoginRepository

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class RequestLoginUseCase(private val loginRepository: LoginRepository) {

    suspend fun execute(): RequestTokenResponse {
        val requestTokenRequest = RequestTokenRequest(null)
        return loginRepository.createRequestToken(requestTokenRequest)
    }
}