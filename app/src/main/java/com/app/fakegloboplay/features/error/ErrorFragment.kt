package com.app.fakegloboplay.features.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.app.fakegloboplay.R
import com.app.fakegloboplay.databinding.FragmentErrorBinding

class ErrorFragment : Fragment() {

    private var _binding: FragmentErrorBinding? = null
    private val binding: FragmentErrorBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentErrorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnErroClose.setOnClickListener {
            navHome()
        }
    }

    private fun navHome() {
        this.findNavController()
            .popBackStack(R.id.homeFragment, false)
    }
}