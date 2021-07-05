package com.maslima.globo_play_recrutamento.presentation.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.maslima.globo_play_recrutamento.presentation.components.BottomBar
import com.maslima.globo_play_recrutamento.presentation.components.MovieListComponent
import com.maslima.globo_play_recrutamento.presentation.components.SearchAppBar
import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.MovieListEvent.NextPageEvent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    val viewModel: MovieListViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val movies = viewModel.movies.value
                val query = viewModel.query.value
                val categoryID = viewModel.categoryId.value
                val selectedCategory = viewModel.selectedCategory.value
                val categoryScrollPosition = viewModel.categoryScrollPosition
                val loading = viewModel.loading.value
                val page = viewModel.page.value
                val selectedItem = remember { mutableStateOf("home") }
                val result = remember { mutableStateOf("") }

                Scaffold(
                    topBar = {
                        Toolbar(query, selectedCategory)
                    },
                    bodyContent = {
                        MovieListComponent(
                            movies,
                            page,
                            loading,
                            onChangeMovieScrollPosition = viewModel::onChangeMovieScrollPosition,
                            onTriggerEvent = { viewModel.onTriggerEvent(NextPageEvent) },
                            navController = findNavController()
                        )
                    },
                    bottomBar = {
                        BottomBar(selectedItem, result)
                    }
                )
            }
        }
    }

    @Composable
    private fun Toolbar(query: String, selectedCategory: MovieCategory?) {
        SearchAppBar(
            query = query,
            onQueryChange = viewModel::onQueryChanged,
            onExecuteSearch = { viewModel.onTriggerEvent(MovieListEvent.NewSearchEvent) },
            scrollPosition = viewModel.categoryScrollPosition,
            selectedCategory = selectedCategory,
            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
            onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
            categories = getAllMoviesCategories()
        )
    }

}