package com.example.globoplay.views

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.globoplay.R
import com.example.globoplay.databinding.FragmentDetalhesBinding


class Details(private val cast:String) : Fragment() {
    private lateinit var binding:FragmentDetalhesBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentDetalhesBinding.inflate(layoutInflater)
        binding.texto.text = binding.texto.text.toString() + cast
        return binding.root
    }

}