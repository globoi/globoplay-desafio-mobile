package com.simonassi.globoplay.ui.highlights.morecontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.simonassi.globoplay.databinding.FragmentMoreContentBinding
import com.simonassi.globoplay.ui.main.home.MoreContentAdapter
import com.simonassi.globoplay.viewmodels.HighlightsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MoreContentFragment : Fragment() {

    private val highlightsViewModel: HighlightsViewModel by activityViewModels()

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
        highlightsViewModel.relatedMovies.observe(viewLifecycleOwner, Observer { movies ->
            moreContentAdapter.submitList(movies)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = MoreContentFragment()
    }
}