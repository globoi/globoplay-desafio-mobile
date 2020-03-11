package br.com.nerdrapido.themoviedbapp.ui.login

import android.content.DialogInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenResponse
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.RequestLoginUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class LoginPresenterImpl(
    private val requestLoginUseCase: RequestLoginUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) : AbstractPresenterImpl<LoginView>(
    getLogInStateUseCase
), LoginPresenter {

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
                val requestLoginResponse = requestLoginUseCase.execute()
                view.dismissLoading()
                view.showMdbDialog(requestLoginResponse.requestToken)

            }
        }
    }

    override fun loginAttemptHasResponse(response: Any) {
        TODO("Not yet implemented")
    }

    override fun loginSuccess() {
        TODO("Not yet implemented")
    }

    override fun endOfSplashScreen() {
        TODO("Not yet implemented")
    }
}