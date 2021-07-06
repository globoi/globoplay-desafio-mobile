package com.maslima.globo_play_recrutamento.presentation.ui.favorite_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.material.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.maslima.globo_play_recrutamento.presentation.components.BottomBar
import com.maslima.globo_play_recrutamento.presentation.components.GeneralToolbar
import com.maslima.globo_play_recrutamento.presentation.components.MovieListComponent
import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.MovieListEvent
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class FavoriteListFragment : Fragment() {
    val viewModel: FavoriteListViewModel by viewModels()

    @ExperimentalFoundationApi
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {

            var movies = viewModel.movies
            val loading = viewModel.loading.value

            CoroutineScope(Dispatchers.IO).launch {
                viewModel.onTriggerEvent(MovieListEvent.NewSearchEvent, { items -> movies = items })
                withContext(Dispatchers.Main) {
                    setContent {
                        val selectedItem = remember { mutableStateOf("home") }
                        val result = remember { mutableStateOf("") }

                        Scaffold(
                            topBar = {
                                GeneralToolbar()
                            },
                            bodyContent = {
                                MovieListComponent(
                                    movies,
                                    0,
                                    loading,
                                    onChangeMovieScrollPosition = {},
                                    onTriggerEvent = {
                                        viewModel.onTriggerEvent(
                                            MovieListEvent.NextPageEvent,
                                            {})
                                    },
                                    navController = findNavController(),
                                    isFromFavorite = true
                                )
                            },
                            bottomBar = { BottomBar(selectedItem, result, findNavController()) }
                        )
                    }
                }
            }
        }
    }
}