package com.example.globechallenge.ui.mylist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.globechallenge.databinding.FragmentMyListBinding

class MyListFragment : Fragment() {
    private lateinit var binding: FragmentMyListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyListBinding.inflate(layoutInflater)
        val root = binding.root
        return root
    }

    companion object {
        const val TITLE_MY_LIST = "My List"
    }
}