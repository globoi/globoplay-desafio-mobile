package com.simonassi.globoplay.ui.main.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import com.simonassi.globoplay.data.favorite.entity.Favorite
import com.simonassi.globoplay.databinding.FragmentFavoritesBinding
import com.simonassi.globoplay.ui.main.home.MovieAdapter
import com.simonassi.globoplay.ui.main.home.TvAdapter
import com.simonassi.globoplay.viewmodels.FavoriteViewModel
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoritesFragment : Fragment(), LifecycleObserver {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favoriteViewModel.getFavorites()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding: FragmentFavoritesBinding?
        binding = FragmentFavoritesBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val favoriteAdapter = FavoriteAdapter()
        binding.favoriteList.adapter = favoriteAdapter

        subscribeUi(favoriteAdapter)
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi(favoriteAdapter: FavoriteAdapter) {
        favoriteViewModel.favoriteLiveData.observe(viewLifecycleOwner, Observer { favorites ->
            favoriteAdapter.submitList(favorites)
        })
    }
}