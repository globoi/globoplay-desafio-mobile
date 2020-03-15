package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.account.AccountRequest
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

        val sessionId = createSessionResponse.sessionId
        if (sessionId != null) {
            val account = accountRepository.getAccount(AccountRequest(sessionId))
            sessionRepository.setIso6391(account.iso6391)
            sessionRepository.setIso3161(account.iso31661)
            sessionRepository.setName(account.name)
            sessionRepository.setIncludeAdult(account.includeAdult ?: false)
            sessionRepository.setUserName(account.username)
            sessionRepository.setAccountId(account.id ?: 0)
            sessionRepository.setSessionId(sessionId)
        } else {
            sessionRepository.setSessionId(null)
            sessionRepository.setIso6391(null)
            sessionRepository.setIso3161(null)
            sessionRepository.setName(null)
            sessionRepository.setIncludeAdult(false)
            sessionRepository.setUserName(null)
            sessionRepository.setAccountId(0)
        }



    }

}