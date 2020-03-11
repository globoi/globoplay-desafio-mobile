package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import org.koin.android.ext.android.inject

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class HomeActivity : AbstractActivity<HomeView, HomePresenter>(), HomeView {

    override val presenter: HomePresenter by inject()

    override val layoutId = R.layout.activity_home

    override val activityTitle = "Home"
}