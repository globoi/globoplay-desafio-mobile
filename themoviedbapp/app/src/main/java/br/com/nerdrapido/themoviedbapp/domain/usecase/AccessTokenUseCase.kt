package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.login.AccessTokenRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.AccessTokenResponse
import br.com.nerdrapido.themoviedbapp.data.repository.login.LoginRepository

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class AccessTokenUseCase(private val loginRepository: LoginRepository) {

    suspend fun execute(accessTokenRequest: AccessTokenRequest): AccessTokenResponse {
        return loginRepository.createAccessToken(accessTokenRequest)
    }
}