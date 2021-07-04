package com.maslima.globo_play_recrutamento.presentation.ui.movie_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.Dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.maslima.globo_play_recrutamento.presentation.components.CircularIndeterminateProgressBar
import com.maslima.globo_play_recrutamento.presentation.components.MovieCard
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
                        CircularIndeterminateProgressBar(isDisplayed = loading, verticalBias = 0.3f)
                        LazyVerticalGrid(cells = GridCells.Fixed(3)) {
                            itemsIndexed(items = movies) { index, movie ->
                                viewModel.onChangeMovieScrollPosition(index)
                                if ((index + 1) >= (page * PAGE_SIZE) && !loading) {
                                    viewModel.onTriggerEvent(NextPageEvent)
                                }
                                val url = "https://image.tmdb.org/t/p/w154".plus(movie.posterPath)
                                MovieCard(movieUrlImage = url, onClickCard = {})
                            }
                        }
                    },
                    bottomBar = {
                        BottomBar(selectedItem, result)
                    }
                )
            }
        }
    }

    @Composable
    private fun BottomBar(
        selectedItem: MutableState<String>,
        result: MutableState<String>
    ) {
        BottomAppBar(
            content = {
                BottomNavigation() {
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Home) },
                        selected = selectedItem.value == "home",
                        onClick = {
                            result.value = "home icon clicked"
                            selectedItem.value = "Início"
                        },
                        alwaysShowLabels = true
                    )
                    BottomNavigationItem(
                        icon = { Icon(Icons.Filled.Favorite) },
                        selected = selectedItem.value == "favorite",
                        onClick = {
                            result.value = "favorite icon clicked"
                            selectedItem.value = "Minha Lista"
                        },
                        alwaysShowLabels = true
                    )
                }
            }
        )
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