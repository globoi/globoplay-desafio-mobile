package br.com.nerdrapido.themoviedbapp.ui.login

import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.CreateSessionResponse
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenResponse
import br.com.nerdrapido.themoviedbapp.domain.usecase.CreateSessionuseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.RequestLoginUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.SetSessionUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class LoginPresenterImpl(
    private val requestLoginUseCase: RequestLoginUseCase,
    private val createSessionuseCase: CreateSessionuseCase,
    private val setSessionUseCase: SetSessionUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) : AbstractPresenterImpl<LoginView>(
    getLogInStateUseCase
), LoginPresenter {

    companion object {
        const val LOGIN_SUCCESS = "LOGINSUCCESS.com"
    }

    /**
     * See overridden fun
     */
    override fun needsToBeLoggedIn(): Boolean {
        return false
    }

    override fun loginWasCalled() {
        GlobalScope.launch {
            val requestLoginResponse = requestLoginUseCase
                .execute(RequestTokenRequest())
            view.dismissLoading()
            view.showMdbDialog(requestLoginResponse)
        }
    }

    override fun loginAttemptHasResponse(response: Any) {
        TODO("Not yet implemented")
    }

    override fun loginSuccess(requestTokenResponse: RequestTokenResponse) {
        view.showLoading()
        GlobalScope.launch {
            val accessTokenResponse = createSessionuseCase.execute(
                CreateSessionRequest(
                    requestTokenResponse.requestToken
                )
            )
            setSessionUseCase.execute(accessTokenResponse)
            view.dismissLoading()
            view.goHome()
        }
    }

    override fun loginDenied() {
        view.showLoading()
        GlobalScope.launch {
            setSessionUseCase.execute(CreateSessionResponse(null, null))
            view.dismissLoading()

        }

    }
}