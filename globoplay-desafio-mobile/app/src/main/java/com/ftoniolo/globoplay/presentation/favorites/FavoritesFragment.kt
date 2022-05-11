package com.ftoniolo.globoplay.presentation.favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.ftoniolo.globoplay.databinding.FragmentFavoritesBinding
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class FavoritesFragment : Fragment() {
    private var _binding: FragmentFavoritesBinding? = null
    private val binding: FragmentFavoritesBinding get() = _binding!!
    private val viewModel: FavoritesViewModel by viewModels()

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentFavoritesBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    @Suppress("MagicNumber")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner) { uiState ->
            binding.flipperFavorites.displayedChild = when (uiState) {
                is FavoritesViewModel.UiState.ShowFavorite -> {
                    binding.rvFavorite.run {
                        setHasFixedSize(true)
                        layoutManager = GridLayoutManager(context, 3)
                        adapter = FavoritesGridAdapter(uiState.favorites, imageLoader)
                    }
                    FLIPPER_CHILD_FILMS
                }
                FavoritesViewModel.UiState.ShowEmpty -> {
                    FLIPPER_CHILD_EMPTY
                }
            }

        }
        viewModel.getAll()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    companion object {
        private const val FLIPPER_CHILD_FILMS = 0
        private const val FLIPPER_CHILD_EMPTY = 1
    }
}