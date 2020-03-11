package br.com.nerdrapido.themoviedbapp.ui.abstracts

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.nerdrapido.themoviedbapp.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
abstract class AbstractActivity<V: View, P: Presenter<V>>: AppCompatActivity(), View {

    abstract val presenter: P

    abstract val activityTitle: String

    var loadingDialog: AlertDialog? = null

    /**
     * The layout id to be used in this Activity.
     */
    abstract val layoutId: Int

    override fun goBackToLogin() {
        val newIntent = Intent(this, LoginActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(newIntent)
    }

    override fun showLoading() {
        if (loadingDialog == null) {
            loadingDialog = MaterialAlertDialogBuilder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setPositiveButton("Ok", null)
                .create()
        }
        loadingDialog?.show()
    }

    override fun dismissLoading() {
        loadingDialog?.dismiss()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        @Suppress("UNCHECKED_CAST")
        presenter.initializeView(this as V)
        presenter.viewIsInvoked()
        super.onCreate(savedInstanceState)
        setContentView(layoutId)
        title = activityTitle
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