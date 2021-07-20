package com.example.globechallenge.ui.mylist.fragments

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globechallenge.R
import com.example.globechallenge.application.GlobeChallengeApplication
import com.example.globechallenge.databinding.FragmentMyListBinding
import com.example.globechallenge.ui.FavoritesViewModel
import com.example.globechallenge.ui.FavoritiesViewModelFactory
import com.example.globechallenge.ui.details.fragments.WatchTooFragment
import com.example.globechallenge.ui.mylist.adapter.MyListAdapter

class MyListFragment : Fragment() {
    private lateinit var binding: FragmentMyListBinding
    private lateinit var recyclerViewMyList: RecyclerView
    private val adapterMyList = MyListAdapter()

    private val favoritesViewModel: FavoritesViewModel by viewModels {
        FavoritiesViewModelFactory(
            (context?.applicationContext as GlobeChallengeApplication)
                .repository
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMyListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        getFavorities()
        viewBind()
        setupRecyclerMyList()
    }

    private fun viewBind() {
        with(binding.include.toolbarTxt) {
            gravity = Gravity.START
            text = getString(R.string.toolbar_title)
        }
        recyclerViewMyList = binding.rvMyList
    }

    private fun setupRecyclerMyList() {
        recyclerViewMyList.run {
            layoutManager = GridLayoutManager(
                context,
                WatchTooFragment.GRID_LAYOUT_SPAN_COUNT,
                RecyclerView.VERTICAL,
                false
            )
            adapter = adapterMyList
        }
    }

    private fun getFavorities() {
        favoritesViewModel.allFavoriteMovies.observe(viewLifecycleOwner) { favoriteMoviesEntity ->
            favoriteMoviesEntity?.let {
                adapterMyList.addMoviesToMyList(it)
            }
        }
    }

    companion object {
        const val TITLE_MY_LIST = "My List"
    }
}