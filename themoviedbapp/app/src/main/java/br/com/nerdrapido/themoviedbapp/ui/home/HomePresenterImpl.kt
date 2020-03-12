package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverRequest
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetDiscoverUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class HomePresenterImpl(
    private val getDiscoverUseCase: GetDiscoverUseCase,
    private val logoutUseCase: LogoutUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) :
    AbstractPresenterImpl<HomeView>(
        getLogInStateUseCase
    ), HomePresenter {

    override fun logoutWasCalled() {
        view.showLoading()
        runBlocking {
            launch(coroutineContext) {
                logoutUseCase.execute()
                view.dismissLoading()
                needToGoBackToLoginCheck()
            }
        }
    }

    override fun viewIsAboutToBeShown() {
        super.viewIsAboutToBeShown()
        runBlocking {
            launch(coroutineContext) {
                val movieList = getDiscoverUseCase.execute(DiscoverRequest(
                    null, 1
                ))
                view.discoverPageLoaded(movieList)
            }
        }

    }

}

