package br.com.nerdrapido.themoviedbapp.ui.login

import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionResponse
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenResponse
import br.com.nerdrapido.themoviedbapp.domain.usecase.CreateSessionUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.RequestLoginUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.SetSessionUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class LoginPresenterImpl(
    private val requestLoginUseCase: RequestLoginUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val setSessionUseCase: SetSessionUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) : AbstractPresenterImpl<LoginView>(
    getLogInStateUseCase
), LoginPresenter {

    companion object {
        const val LOGIN_SUCCESS = "LOGINSUCCESS.com"
    }

    override fun viewIsAboutToBeShown() {
        super.viewIsAboutToBeShown()
        GlobalScope.launch {
            onResponseWrapper(
                requestLoginUseCase
                    .execute(RequestTokenRequest())
            ) {
                view.showMdbDialog(it)
            }
        }
    }
    /**
     * See overridden fun
     */
    override fun needsToBeLoggedIn(): Boolean {
        return false
    }

    override fun loginWasCalled() {
        GlobalScope.launch {
            onResponseWrapper(
                requestLoginUseCase
                    .execute(RequestTokenRequest())
            ) {
                view.showMdbDialog(it)
            }
        }
    }

    override fun loginSuccess(requestTokenResponse: RequestTokenResponse) {
        GlobalScope.launch {
            onResponseWrapper(
                createSessionUseCase.execute(
                    CreateSessionRequest(
                        requestTokenResponse.requestToken
                    )
                )
            ) {
                GlobalScope.launch {
                    setSessionUseCase.execute(it)
                    view.goHome()
                }
            }
        }
    }

    override fun loginDenied() {
        GlobalScope.launch {
            setSessionUseCase.execute(CreateSessionResponse(null, null))
            view.showLoginDenied()
        }

    }
}