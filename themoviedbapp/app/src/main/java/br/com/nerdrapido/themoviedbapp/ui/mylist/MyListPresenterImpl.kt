package br.com.nerdrapido.themoviedbapp.ui.mylist

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.WatchlistMoviesUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
class MyListPresenterImpl(
    private val getWatchlistMoviesUseCase: WatchlistMoviesUseCase,
    logoutUseCase: LogoutUseCase,
    getLogInStateUseCase: GetLogInStateUseCase
) : NavigationPresenterImpl<MyListView>(logoutUseCase, getLogInStateUseCase), MyListPresenter {

    override fun viewIsAboutToBeShown() {
        super.viewIsAboutToBeShown()
        GlobalScope.launch {
            onResponseWrapper(
                getWatchlistMoviesUseCase.getWatchlistMovies(1)
            ) { responseObject ->
                responseObject.results?.let {
                    view.listPageLoaded(it)
                }
            }
        }
    }
}