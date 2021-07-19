package com.example.globechallenge.ui.mylist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.globechallenge.application.GlobeChallengeApplication
import com.example.globechallenge.databinding.FragmentMyListBinding
import com.example.globechallenge.ui.FavoritesViewModel
import com.example.globechallenge.ui.FavoritiesViewModelFactory

class MyListFragment : Fragment() {
    private lateinit var binding: FragmentMyListBinding
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
        getFavorities()
    }

    private fun getFavorities() {
        favoritesViewModel.allFavoriteMovies.observe(viewLifecycleOwner) { favoriteMoviesEntity ->
            favoriteMoviesEntity?.let {
                val o = it
            }
        }
    }

    companion object {
        const val TITLE_MY_LIST = "My List"
    }
}