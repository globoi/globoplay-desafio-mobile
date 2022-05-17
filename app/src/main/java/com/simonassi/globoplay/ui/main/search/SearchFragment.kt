package com.simonassi.globoplay.ui.main.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.simonassi.globoplay.databinding.FragmentSearchBinding
import com.simonassi.globoplay.databinding.NetworkErrorLayoutBinding
import com.simonassi.globoplay.utilities.Utils
import com.simonassi.globoplay.viewmodels.SearchViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SearchFragment : Fragment() {
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if(context?.let { Utils.isNetworkAvailable(it) } != true){
            val errorBinding = NetworkErrorLayoutBinding.inflate(inflater, container, false)
            return errorBinding.root
        }

        val binding: FragmentSearchBinding?
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val searchAdapter = SearchResultAdapter()
        binding.resultSearchList.adapter = searchAdapter
        val progressBar = binding.progressBar
        progressBar.visibility = View.INVISIBLE
        subscribeUi(searchAdapter, progressBar)

        binding.searchBtn.setOnClickListener {
            val key = binding.searchTextInput.text.toString()
            if (key.isNotEmpty()) {
                searchViewModel.getMovieByKeyword(key)
                progressBar.visibility = View.VISIBLE
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(searchAdapter: SearchResultAdapter, progressBar: ProgressBar) {
       searchViewModel.resultSearchLiveData.observe(viewLifecycleOwner, Observer { movies ->
           searchAdapter.submitList(movies)
           progressBar.visibility = View.INVISIBLE
       })
    }
}