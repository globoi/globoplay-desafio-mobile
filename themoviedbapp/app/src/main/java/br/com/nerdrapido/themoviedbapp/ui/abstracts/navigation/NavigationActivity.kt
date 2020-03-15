package br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation

import android.os.Bundle
import android.view.MenuItem
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_navigation.*
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
abstract class NavigationActivity<V : NavigationView, P : NavigationPresenter<V>> :
    AbstractActivity<V, P>(), NavigationView,
    BottomNavigationView.OnNavigationItemSelectedListener {

    override val layoutId = R.layout.activity_navigation

    abstract val nestedActivityLayoutId: Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (navigationView as BottomNavigationView).setOnNavigationItemSelectedListener(this)
        layoutInflater.inflate(nestedActivityLayoutId, navigationActivityContainer)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.logout -> presenter.logoutWasCalled()
            R.id.home -> goHome()
            else -> {
                Timber.w("Nav Menu Wass called but nooption found")
            }
        }
        return true
    }

}