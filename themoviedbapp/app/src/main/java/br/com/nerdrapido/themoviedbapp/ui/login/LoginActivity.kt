package br.com.nerdrapido.themoviedbapp.ui.login

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialog
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity() : AbstractActivity<LoginView, LoginPresenter>(), LoginView {

    override val presenter: LoginPresenter by inject()

    override val layoutId = R.layout.activity_login




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        login.setOnClickListener {
            presenter.loginWasCalled()
        }




    }


    override fun showMdbDialog(requestToken: String) {
        val alert = AppCompatDialog(this)
        alert.setTitle(resources.getString(R.string.web_view_login_title))

        @SuppressLint("InflateParams")
        val wv: WebView = layoutInflater.inflate(R.layout.view_webview_login, null) as WebView
        wv.loadUrl("https://www.themoviedb.org/auth/access?request_token=$requestToken")

        wv.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view: WebView,
                url: String?
            ): Boolean {
                if (url != null && url.contains("string")) {
                    alert.dismiss()
                    presenter.loginSuccess()
                }
                view.loadUrl(url)
                return true
            }
        }

        alert.setContentView(wv)
        alert.setCancelable(true)
        alert.show()
    }


}
