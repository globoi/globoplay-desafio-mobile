package br.com.nerdrapido.themoviedbapp.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import br.com.nerdrapido.themoviedbapp.ui.home.adapter.DiscoverAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_home.*
import org.koin.android.ext.android.inject


/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class HomeActivity : AbstractActivity<HomeView, HomePresenter>(), HomeView,
    BottomNavigationView.OnNavigationItemSelectedListener {



    private val discoverItemList = mutableListOf<MovieListResultObject>(
        MovieListResultObject(
        "",
        false,
        "",
        "",
        emptyList(),
        1,
        "",
        "",
        "Carregando",
        "",
        null,
            null,
            null,
            null
    )
    )

    private val discoverAdapter = DiscoverAdapter(discoverItemList, this)

    private lateinit var discoverRecyclerView: RecyclerView

    override val presenter: HomePresenter by inject()

    override val layoutId = R.layout.activity_home

    override fun getActivityTitle(): String {
        return getString(R.string.home_title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (navigationView as BottomNavigationView).setOnNavigationItemSelectedListener(this)

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        discoverRecyclerView = discoverRv
        discoverRecyclerView.layoutManager = layoutManager
        discoverRecyclerView.adapter = discoverAdapter
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.logout) {
            presenter.logoutWasCalled()
        }
        return true
    }

    override fun discoverPageLoaded(movieList: List<MovieListResultObject>) {
        discoverItemList.clear()
        discoverItemList.addAll(movieList)
        discoverAdapter.notifyDataSetChanged()
    }
}