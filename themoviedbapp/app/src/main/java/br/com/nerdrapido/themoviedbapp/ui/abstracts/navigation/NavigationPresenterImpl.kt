package br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
abstract class NavigationPresenterImpl<V : NavigationView>(
    logoutUseCase: LogoutUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) :
    AbstractPresenterImpl<V>(logoutUseCase, getLogInStateUseCase), NavigationPresenter<V> {

    override fun logoutWasCalled() {
        view.showLoading()
        GlobalScope.launch {
            logoutUseCase.execute()
            view.dismissLoading()
            needToGoBackToLoginCheck()
        }
    }

}