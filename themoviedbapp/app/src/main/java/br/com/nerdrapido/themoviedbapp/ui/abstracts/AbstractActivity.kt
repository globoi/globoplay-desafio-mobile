package br.com.nerdrapido.themoviedbapp.ui.abstracts

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.alerts.ErrorDialog
import br.com.nerdrapido.themoviedbapp.ui.home.HomeActivity
import br.com.nerdrapido.themoviedbapp.ui.login.LoginActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder


/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
abstract class AbstractActivity<V : View, P : Presenter<V>> : AppCompatActivity(), View {

    abstract val presenter: P

    private var loadingDialog: AlertDialog? = null

    private var errorDialog: ErrorDialog? = null
        get() {
            if (field == null) {
                field = ErrorDialog(this)
            }
            return field
        }

    /**
     * The layout id to be used in this Activity.
     */
    abstract val layoutId: Int

    abstract fun getActivityTitle(): String

    override fun goBackToLogin() {
        runOnUiThread {
            val newIntent = Intent(this, LoginActivity::class.java)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(newIntent)
        }
    }

    override fun goHome() {
        val newIntent = Intent(this, HomeActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        startActivity(newIntent)
        this.finish()
    }

    override fun showLoading() {
        runOnUiThread {
            if (loadingDialog == null) {
                loadingDialog = MaterialAlertDialogBuilder(this)
                    .create()
            }
            loadingDialog?.show()
        }
    }

    override fun showNetworkError() {
        runOnUiThread {
            errorDialog?.setTitle("showNetworkError")
            errorDialog?.setMessage("showNetworkError")
            errorDialog?.show()
        }
    }

    override fun showApiErrorResponse() {
        runOnUiThread {
            errorDialog?.setTitle("showApiErrorResponse")
            errorDialog?.setMessage("showApiErrorResponse")
            errorDialog?.show()
        }
    }

    override fun showUnknownError() {
        runOnUiThread {
            errorDialog?.setTitle("showUnknownError")
            errorDialog?.setMessage("showUnknownError")
            errorDialog?.show()
        }
    }

    override fun dismissLoading() {
        runOnUiThread { loadingDialog?.dismiss() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        onCreateCall()
    }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        onCreateCall()
    }

    private fun onCreateCall() {
        @Suppress("UNCHECKED_CAST")
        presenter.initializeView(this as V)
        presenter.viewIsInvoked()
        // Here the device nav gets its color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window: Window = window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.navigationBarColor = resources.getColor(R.color.colorPrimaryDark)
        }
        setContentView(layoutId)
        title = getActivityTitle()
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