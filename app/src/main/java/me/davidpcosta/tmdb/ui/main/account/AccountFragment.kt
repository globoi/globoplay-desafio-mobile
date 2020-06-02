package me.davidpcosta.tmdb.ui.main.account

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.squareup.picasso.Picasso
import me.davidpcosta.tmdb.BuildConfig
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.hide
import me.davidpcosta.tmdb.show
import me.davidpcosta.tmdb.ui.login.LoginActivity

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sessionId: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main_fragment_account, container, false)

        accountViewModel = ViewModelProvider(this, AccountViewModelFactory()).get(AccountViewModel::class.java)
        sharedPreferences = requireActivity().getSharedPreferences("user_login", Context.MODE_PRIVATE)
        sessionId = sharedPreferences.getString("session_id", "")!!

        val avatar: ImageView = view.findViewById(R.id.avatar)
        val name: TextView = view.findViewById(R.id.name)
        val username: TextView = view.findViewById(R.id.username)
        val loading: ProgressBar = view.findViewById(R.id.loading)
        val logoutButton: Button = view.findViewById(R.id.logout_button)

        logoutButton.setOnClickListener {
            handleLogoutButtonClicked()
        }

        accountViewModel.fetchAccountDetails(sessionId)
        accountViewModel.accountDetails.observe(viewLifecycleOwner, Observer {

            Picasso.with(activity)
                .load(BuildConfig.GRAVATAR_BASE_URL + it.avatar.gravatar.hash)
                .into(avatar)

            name.text = it.name
            username.text = it.username

            avatar.show()
            name.show()
            username.show()
            logoutButton.show()
            loading.hide()
        })
        return view
    }

    fun handleLogoutButtonClicked() {
        deleteUserInfo()
        goToLogin()
    }

    private fun deleteUserInfo() {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.remove("session_id")
        editor.remove("account_id")
        editor.apply()
    }

    private fun goToLogin() {
        val intent = Intent(requireActivity(), LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        requireActivity().startActivity(intent)
        requireActivity().finish()
    }


}
