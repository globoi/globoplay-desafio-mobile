package com.app.fakegloboplay.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.app.fakegloboplay.BuildConfig
import com.app.fakegloboplay.R
import com.app.fakegloboplay.databinding.FragmentHomeBinding
import com.app.fakegloboplay.features.commons.customview.ImageLoaderView
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModel()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(homeViewModel) {
            account.observe(viewLifecycleOwner) {
                it?.let {
                    binding.imgAvatar
                        .loadImageCircle(BuildConfig.URL_IMG + it.avatar?.tmdb?.avatarPath)
                }
            }
            getAccount()
        }
    }
}