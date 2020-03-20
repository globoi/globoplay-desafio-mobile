package br.com.nerdrapido.themoviedbapp.ui.login

import android.os.Bundle
import android.view.View.*
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.login.RequestTokenResponse
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
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
            loginDeniedContainer?.visibility = GONE
            showLoading()
            presenter.loginWasCalled()
        }
    }

    override fun showLoading() {
        runOnUiThread {
            loading?.visibility = VISIBLE
        }
    }

    override fun dismissLoading() {
        runOnUiThread {
            loading?.visibility = GONE
        }
    }

    override fun showMdbDialog(requestTokenResponse: RequestTokenResponse) {
        runOnUiThread {
            showLoading()
            tmdbWebView?.loadUrl(
                "https://www.themoviedb.org/authenticate/${requestTokenResponse.requestToken}?redirect_to=http://$LOGIN_SUCCESS"
            )
            tmdbWebView?.settings?.domStorageEnabled = true
            tmdbWebView?.settings?.allowContentAccess = true
            tmdbWebView?.settings?.allowFileAccess = true
            tmdbWebView?.settings?.allowFileAccessFromFileURLs = true
            tmdbWebView.settings?.allowUniversalAccessFromFileURLs = true
            tmdbWebView?.settings?.javaScriptEnabled = true
            tmdbWebView?.settings?.setSupportZoom(true)
            tmdbWebView?.settings?.domStorageEnabled = true
            tmdbWebView?.settings?.databaseEnabled = true
            tmdbWebView?.settings?.minimumFontSize = 1
            tmdbWebView?.settings?.minimumLogicalFontSize = 1
            tmdbWebView?.isClickable = true
            tmdbWebView?.webChromeClient = WebChromeClient()

            tmdbWebView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                    Timber.d(url)
                    loginDeniedContainer?.visibility = GONE
                    tmdbWebView?.visibility = VISIBLE
                    dismissLoading()
                    if (url != null && url.contains("approved=true")) {
                        tmdbWebView.visibility = INVISIBLE
                        showLoading()
                        presenter.loginSuccess(requestTokenResponse)
                    } else if (url != null && url.contains("denied=true")) {
                        tmdbWebView.visibility = INVISIBLE
                        showLoading()
                        presenter.loginDenied()
                    }
                }
            }

        }

    }

    override fun showLoginDenied() {
        runOnUiThread {
            dismissLoading()
            tmdbWebView?.visibility = INVISIBLE
            loginDeniedContainer?.visibility = VISIBLE
        }
    }
}
