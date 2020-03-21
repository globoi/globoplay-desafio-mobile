package br.com.nerdrapido.themoviedbapp.ui.mylist

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.data.model.watchlistmovies.WatchlistMoviesResponse
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.WatchlistMoviesUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.math.ceil

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
        view.showLoading()
        GlobalScope.launch {
            onResponseWrapper(
                getWatchlistMoviesUseCase.getWatchlistMovies(1)
            ) { responseObject ->
                val totalPages = responseObject.totalPages ?: 1
                val totalResults = responseObject.totalResults ?: 1
                val pageSize = ceil(totalResults.toDouble() / totalPages.toDouble()).toInt()
                view.listSizeLoaded(totalPages, pageSize)
                responseObject.results?.let {
                    view.dismissLoading()
                    view.listPageLoaded(it)
                }
            }
        }
    }

    override suspend fun loadPage(page: Int): List<MovieListResultObject> {
        var response: WatchlistMoviesResponse? = null
        onResponseWrapper(getWatchlistMoviesUseCase.getWatchlistMovies(1)) { value ->
            response = value
        }
        return response?.results ?: emptyList()
    }
}