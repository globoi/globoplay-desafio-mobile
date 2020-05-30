package me.davidpcosta.tmdb.ui.main.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.R

class AccountFragment : Fragment() {

    private lateinit var accountViewModel: AccountViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        accountViewModel = ViewModelProvider(this).get(AccountViewModel::class.java)
        val view = inflater.inflate(R.layout.activity_main_fragment_account, container, false)
        val textView: TextView = view.findViewById(R.id.text_notifications)
        accountViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return view
    }
}
