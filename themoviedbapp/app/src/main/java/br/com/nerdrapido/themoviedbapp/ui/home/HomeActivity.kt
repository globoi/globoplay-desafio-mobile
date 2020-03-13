package br.com.nerdrapido.themoviedbapp.ui.home

import android.os.Bundle
import android.view.MenuItem
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist.HorizontalMovieList
import br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist.HorizontalMovieList.OnLoadNextPage
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.koin.android.ext.android.inject


/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class HomeActivity : AbstractActivity<HomeView, HomePresenter>(), HomeView,
    BottomNavigationView.OnNavigationItemSelectedListener {


    override val presenter: HomePresenter by inject()

    override val layoutId = R.layout.activity_home

    override fun getActivityTitle(): String {
        return getString(R.string.home_title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (navigationView as BottomNavigationView).setOnNavigationItemSelectedListener(this)

        //Does the lists setup
        setupList(
            getString(R.string.em_alta),
            homeListA
        ) { page -> presenter.loadDiscoverPage(page) }
        setupList(
            getString(R.string.filmes_de, getString(R.string.acao)),
            homeListB
        ) { page -> presenter.loadActionPage(page) }
        setupList(
            getString(R.string.filmes_de, getString(R.string.comedia)),
            homeListC
        ) { page -> presenter.loadComedyPage(page) }
        setupList(
            getString(R.string.filmes_de, getString(R.string.ficcao_cientifica)),
            homeListD
        ) { page -> presenter.loadScienceFictionPage(page) }

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            presenter.logoutWasCalled()
        }
        return true
    }

    private fun setupList(
        title: String? = null,
        view: HorizontalMovieList,
        loadPage: suspend (page: Int) -> List<MovieListResultObject>
    ) {
        view.titleText = title
        view.setOnPageChangeListener(object : OnLoadNextPage {
            override fun onLoadNextPage(page: Int) {
                runBlocking {
                    async(coroutineContext) {
                        view.addItemList(loadPage(page))
                    }
                }
            }
        })
    }
}