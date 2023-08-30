package com.mazer.globoplayapp.presentation.ui.main.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.presentation.ui.main.home.HomeFragment

class MyListFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_list, container, false)
    }


    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}