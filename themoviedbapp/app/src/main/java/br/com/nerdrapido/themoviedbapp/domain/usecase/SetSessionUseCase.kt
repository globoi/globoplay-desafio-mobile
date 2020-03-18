package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper
import br.com.nerdrapido.themoviedbapp.data.model.account.AccountRequest
import br.com.nerdrapido.themoviedbapp.data.model.account.AccountResponse
import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionResponse
import br.com.nerdrapido.themoviedbapp.data.repository.account.AccountRepository
import br.com.nerdrapido.themoviedbapp.data.repository.session.SessionRepository

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class SetSessionUseCase(
    private val sessionRepository: SessionRepository,
    private val accountRepository: AccountRepository
) {

    suspend fun execute(createSessionResponse: CreateSessionResponse) {

        val sessionId = createSessionResponse.sessionId ?: "0"
        when(val response = accountRepository.getAccount(AccountRequest(sessionId))) {
            is ResponseWrapper.Success -> {saveSession(sessionId ,response.value)}
            else -> deleteSession()
        }
    }

    /**
     * Saves an user session in the data cache
     */
    private fun saveSession(sessionId: String, accountResponse: AccountResponse) {
        sessionRepository.setIso6391(accountResponse.iso6391)
        sessionRepository.setIso3161(accountResponse.iso31661)
        sessionRepository.setName(accountResponse.name)
        sessionRepository.setIncludeAdult(accountResponse.includeAdult ?: false)
        sessionRepository.setUserName(accountResponse.username)
        sessionRepository.setAccountId(accountResponse.id ?: 0)
        sessionRepository.setSessionId(sessionId)
    }

    /**
     * Deletes an user session in the data cache
     */
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