package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.data.model.Genres
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetDiscoverUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenterImpl
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class HomePresenterImpl(
    logoutUseCase: LogoutUseCase,
    private val getDiscoverUseCase: GetDiscoverUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) :
    NavigationPresenterImpl<HomeView>(
        logoutUseCase,
        getLogInStateUseCase
    ), HomePresenter {

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

