package br.com.nerdrapido.themoviedbapp.ui.mylist

import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationActivity
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_my_list.*
import org.koin.android.ext.android.inject
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
class MyListActivity : NavigationActivity<MyListView, MyListPresenter>(),
    MyListView {

    override val nestedActivityLayoutId = R.layout.activity_my_list

    override val presenter: MyListPresenter by inject()

    override fun getActivityTitle(): String {
        return getString(R.string.my_list_title)
    }

    override fun listPageLoaded(list: List<MovieListResultObject>) {
        runOnUiThread { myListV.replaceItemList(list) }
    }

    override fun goMyList() {
        Timber.i("Already in My list")
    }


}