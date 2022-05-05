package com.example.desafioglobo.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.desafioglobo.R
import com.example.desafioglobo.databinding.FragmentMyListBinding

class MyListFragment : Fragment(R.layout.fragment_my_list) {
    private lateinit var binding: FragmentMyListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentMyListBinding.bind(view)


    }
}