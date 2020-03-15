package br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
abstract class NavigationPresenterImpl<V : NavigationView>(private val logoutUseCase: LogoutUseCase, getLogInStateUseCase: GetLogInStateUseCase) :
    AbstractPresenterImpl<V>(getLogInStateUseCase), NavigationPresenter<V> {

    override fun logoutWasCalled() {
        view.showLoading()
        runBlocking {
            async(coroutineContext) {
                logoutUseCase.execute()
                view.dismissLoading()
                needToGoBackToLoginCheck()
            }
        }
    }

}