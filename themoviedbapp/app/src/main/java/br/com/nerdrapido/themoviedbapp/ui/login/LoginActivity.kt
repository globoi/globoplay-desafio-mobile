package br.com.nerdrapido.themoviedbapp.ui.login

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.webkit.WebChromeClient
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

    override val presenter: LoginPresenter by inject()

    override val layoutId = R.layout.activity_login

    override fun getActivityTitle(): String {
        return getString(R.string.login_title)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBt.setOnClickListener {
            presenter.loginWasCalled()
        }
    }

    override fun showLoading() {
        runOnUiThread {
            loading.visibility = VISIBLE
            loginBt.visibility = GONE
        }
    }

    override fun dismissLoading() {
        runOnUiThread {
            loading.visibility = GONE
            loginBt.visibility = VISIBLE
        }
    }

    override fun showMdbDialog(requestTokenResponse: RequestTokenResponse) {
        runOnUiThread {
            val alert = AppCompatDialog(this)
            alert.setTitle(resources.getString(R.string.web_view_login_title))

            @SuppressLint("InflateParams")
            val webView: WebView = layoutInflater.inflate(R.layout.view_webview_login, null) as WebView
            webView.loadUrl(
                "https://www.themoviedb.org/authenticate/${requestTokenResponse.requestToken}?redirect_to=http://$LOGIN_SUCCESS"
            )
            webView.settings.domStorageEnabled = true
            webView.settings.allowContentAccess = true
            webView.settings.allowFileAccess = true
            webView.settings.allowFileAccessFromFileURLs = true
            webView.settings.allowUniversalAccessFromFileURLs = true
            webView.settings.javaScriptEnabled = true
            webView.settings.setSupportZoom(true)
            webView.settings.domStorageEnabled = true
            webView.settings.databaseEnabled = true
            webView.settings.minimumFontSize = 1
            webView.settings.minimumLogicalFontSize = 1
            webView.isClickable = true
            webView.webChromeClient = WebChromeClient()

            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Timber.d(url)
                    if (url != null && url.contains("approved=true")) {
                        alert.dismiss()
                        presenter.loginSuccess(requestTokenResponse)
                    } else if (url != null && url.contains("denied=true")) {
                        alert.dismiss()
                        presenter.loginDenied()
                    }
                }
            }
            alert.setContentView(webView)
            alert.setCancelable(true)
            alert.show()
        }

    }
}
