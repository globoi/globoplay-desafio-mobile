package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.data.model.Genres
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.data.model.discover.DiscoverResponse
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetDiscoverUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.domain.usecase.LogoutUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationPresenterImpl
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

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

    override fun viewIsAboutToBeShown() {
        super.viewIsAboutToBeShown()
        GlobalScope
        loadDiscoverPage(1)
        loadActionPage(1)
        loadComedyPage(1)
        loadScienceFictionPage(1)
    }

    override fun loadDiscoverPage(page: Int) {
        GlobalScope.launch {
            var response: DiscoverResponse? = null
            onResponseWrapper(getDiscoverUseCase.getDiscover(page)) { value -> response = value }
            view.loadedDiscoverPage(response?.results ?: emptyList())
        }
    }

    override fun loadActionPage(page: Int) {
        GlobalScope.launch { view.loadedActionPage(loadDiscoverGenre(page, Genres.ACTION)) }
    }

    override fun loadComedyPage(page: Int) {
        GlobalScope.launch { view.loadedComedyPage(loadDiscoverGenre(page, Genres.COMEDY)) }
    }

    override fun loadScienceFictionPage(page: Int) {
        GlobalScope.launch {
            view.loadedScienceFictionPage(
                loadDiscoverGenre(page, Genres.SCIENCE_FICTION)
            )
        }
    }

    private suspend fun loadDiscoverGenre(page: Int, genre: Genres): List<MovieListResultObject> {
        var response: DiscoverResponse? = null
        onResponseWrapper(getDiscoverUseCase.getDiscover(page, genre)) { value -> response = value }
        return response?.results ?: emptyList()
    }
}

