package br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import br.com.nerdrapido.themoviedbapp.ui.mylist.MyListActivity
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

    override fun goMyList() {
        val newIntent = Intent(this, MyListActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(newIntent)
        this.finish()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.home -> goHome()
            R.id.list -> goMyList()
//            R.id.favorite -> goMyList()
            R.id.logout -> presenter.logoutWasCalled()
            else -> {
                Timber.w("Nav Menu Wass called but no option found")
            }
        }
        return true
    }

}