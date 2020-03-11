package br.com.nerdrapido.themoviedbapp.ui.login

import br.com.nerdrapido.themoviedbapp.data.model.login.AccessTokenRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenRequest
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenResponse
import br.com.nerdrapido.themoviedbapp.domain.usecase.AccessTokenUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.RequestLoginUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.SetAccessTokenUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class LoginPresenterImpl(
    private val requestLoginUseCase: RequestLoginUseCase,
    private val accessLoginUseCase: AccessTokenUseCase,
    private val setAccessTokenUseCase: SetAccessTokenUseCase,
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
        view.showLoading()
        runBlocking {
            launch(coroutineContext) {
                val requestLoginResponse = requestLoginUseCase
                    .execute(RequestTokenRequest("url://$LOGIN_SUCCESS"))
                view.dismissLoading()
                view.showMdbDialog(requestLoginResponse)
            }
        }
    }

    override fun loginAttemptHasResponse(response: Any) {
        TODO("Not yet implemented")
    }

    override fun loginSuccess(requestTokenResponse: RequestTokenResponse) {
        view.showLoading()
        runBlocking {
            launch(coroutineContext) {
                val accessTokenResponse = accessLoginUseCase.execute(AccessTokenRequest(
                    requestTokenResponse.requestToken
                ))
                setAccessTokenUseCase.execute(accessTokenResponse)
                view.dismissLoading()
                view.goHome()
            }
        }
    }
}