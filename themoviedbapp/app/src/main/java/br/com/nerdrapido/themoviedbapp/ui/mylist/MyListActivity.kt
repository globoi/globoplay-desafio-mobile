package br.com.nerdrapido.themoviedbapp.ui.mylist

import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationActivity
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListView
import kotlinx.android.synthetic.main.activity_my_list.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

    override fun listSizeLoaded(totalPages: Int, pageSize: Int) {
        myListV?.setOnPageChangeListener(
            totalPages,
            pageSize,
            object : MovieListView.OnNextPageNeeded {
                override fun onNextPageNeeded(page: Int) {
                    GlobalScope.launch {
                        val list = presenter.loadPage(page)
                        runOnUiThread {
                            myListV?.addItemList(list)
                            dismissLoading()
                        }
                    }
                }
            }
        )
    }

    override fun listPageLoaded(list: List<MovieListResultObject>) {
        runOnUiThread { myListV.addItemList(list) }
    }

    override fun goMyList() {
        Timber.i("Already in My list")
    }


}