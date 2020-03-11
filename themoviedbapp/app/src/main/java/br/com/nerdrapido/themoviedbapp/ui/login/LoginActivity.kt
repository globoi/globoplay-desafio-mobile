package br.com.nerdrapido.themoviedbapp.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatDialog
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenResponse
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import br.com.nerdrapido.themoviedbapp.ui.home.HomeActivity
import br.com.nerdrapido.themoviedbapp.ui.login.LoginPresenterImpl.Companion.LOGIN_SUCCESS
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class LoginActivity : AbstractActivity<LoginView, LoginPresenter>(), LoginView {

    override val activityTitle = "Login"

    override val presenter: LoginPresenter by inject()

    override val layoutId = R.layout.activity_login

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login.setOnClickListener {
            presenter.loginWasCalled()
        }
    }

    override fun showMdbDialog(requestTokenResponse: RequestTokenResponse) {
        val alert = AppCompatDialog(this)
        alert.setTitle(resources.getString(R.string.web_view_login_title))

        @SuppressLint("InflateParams")
        val webView: WebView = layoutInflater.inflate(R.layout.view_webview_login, null) as WebView
        webView.loadUrl(
            "https://www.themoviedb.org/auth/access?request_token=${requestTokenResponse.requestToken}"
        )

        webView.webViewClient = object : WebViewClient() {

            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String?
            ): Boolean {
                //if the login is successful it wll contain the [LOGIN_SUCCESS] value
                Timber.d(url)
                if (url != null && url.contains(LOGIN_SUCCESS)) {
                    alert.dismiss()
                    presenter.loginSuccess(requestTokenResponse)
                } else {
                    view.loadUrl(url)
                }
                return true
            }
        }

        alert.setContentView(webView)
        alert.setCancelable(true)
        alert.show()
    }

    override fun goHome() {
        val newIntent = Intent(this, HomeActivity::class.java)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        newIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        startActivity(newIntent)
        this.finish()
    }
}
