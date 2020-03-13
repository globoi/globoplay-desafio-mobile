package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.data.model.Genres
import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetDiscoverUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import kotlinx.coroutines.async
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
            async(coroutineContext) {
                logoutUseCase.execute()
                view.dismissLoading()
                needToGoBackToLoginCheck()
            }
        }
    }

    override suspend fun loadDiscoverPage(page: Int): List<MovieListResultObject> {
        return getDiscoverUseCase.getDiscover(page)
    }

    override suspend fun loadActionPage(page: Int): List<MovieListResultObject> {
        return getDiscoverUseCase.getDiscover(page, Genres.ACTION)
    }

    override suspend fun loadComedyPage(page: Int): List<MovieListResultObject> {
        return getDiscoverUseCase.getDiscover(page, Genres.COMEDY)
    }

    override suspend fun loadScienceFictionPage(page: Int): List<MovieListResultObject> {
        return getDiscoverUseCase.getDiscover(page, Genres.SCIENCE_FICTION)
    }
}

