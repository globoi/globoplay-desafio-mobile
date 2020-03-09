package br.com.nerdrapido.themoviedbapp.ui.abstracts

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
abstract class AbstractActivity<P: Presenter>: AppCompatActivity(), View {

    abstract val presenter: P

    override fun goBackToLogin() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        presenter.viewIsInvoked()
        super.onCreate(savedInstanceState, persistentState)
    }

    override fun onResume() {
        presenter.viewIsAboutToBeShown()
        super.onResume()
    }

    override fun onDestroy() {
        presenter.viewIsClosed()
        super.onDestroy()
    }

}