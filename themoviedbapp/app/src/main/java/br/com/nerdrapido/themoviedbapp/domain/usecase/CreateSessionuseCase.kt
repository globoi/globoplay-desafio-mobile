package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionResponse
import br.com.nerdrapido.themoviedbapp.data.repository.login.LoginRepository

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class CreateSessionuseCase(private val loginRepository: LoginRepository) {

    suspend fun execute(createSessionRequest: CreateSessionRequest): CreateSessionResponse {
        return loginRepository.createSession(createSessionRequest)
    }
}