package com.simonassi.globoplay.ui.highlights.morecontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.simonassi.globoplay.R
import com.simonassi.globoplay.databinding.FragmentHomeBinding
import com.simonassi.globoplay.databinding.FragmentMoreContentBinding
import com.simonassi.globoplay.ui.main.home.MoreContentAdapter
import com.simonassi.globoplay.ui.main.home.MovieAdapter
import com.simonassi.globoplay.ui.main.home.TvAdapter
import com.simonassi.globoplay.viewmodels.MoreContentViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoreContentFragment : Fragment() {

    private val moreContentViewModel: MoreContentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        moreContentViewModel.getTrendingMovies()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentMoreContentBinding?
        binding = FragmentMoreContentBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val moreContentAdapter = MoreContentAdapter()
        binding.moreContentList.adapter = moreContentAdapter

        subscribeUi(moreContentAdapter)
        setHasOptionsMenu(true)
        return binding.root

    }

    private fun subscribeUi(moreContentAdapter: MoreContentAdapter) {
        moreContentViewModel.moreContentLiveData.observe(viewLifecycleOwner, Observer { movies ->
            moreContentAdapter.submitList(movies)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = MoreContentFragment()
    }
}