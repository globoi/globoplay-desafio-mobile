package com.maslima.globo_play_recrutamento.presentation.ui.movie_list

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.MovieListEvent.*
import com.maslima.globo_play_recrutamento.repository.MovieRepository
import kotlinx.coroutines.launch

const val PAGE_SIZE = 20

const val STATE_KEY_PAGE = "movie.state.page.key"
const val STATE_KEY_QUERY = "movie.state.query.key"
const val STATE_KEY_LIST_POSITION = "movie.state.query.list_position"
const val STATE_KEY_SELECTED_CATEGORY = "movie.state.query.selected_category"
const val TAG = "MovieList"

class MovieListViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepository,
    @Assisted private val savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val categoryId = mutableStateOf(0)
    val selectedCategory: MutableState<MovieCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Float = 0f
    val loading = mutableStateOf(false)
    val page = mutableStateOf(1)
    var movieListScrollPosition = 0

    init {
        savedStateHandle.get<Int>(STATE_KEY_PAGE)?.let { p ->
            setPage(p)
        }
        savedStateHandle.get<String>(STATE_KEY_QUERY)?.let { q ->
            setQuery(q)
        }
        savedStateHandle.get<Int>(STATE_KEY_LIST_POSITION)?.let { p ->
            setListScrollPosition(p)
        }
        savedStateHandle.get<MovieCategory>(STATE_KEY_SELECTED_CATEGORY)?.let { c ->
            setSelectedCategory(c)
        }

        if (movieListScrollPosition != 0) {
            onTriggerEvent(RestoreStateEvent)
        } else {
            onTriggerEvent(NewSearchEvent)
        }
    }

    fun onTriggerEvent(event: MovieListEvent) {
        viewModelScope.launch {
            try {
                when (event) {
                    is NewSearchEvent -> {
                        listMovies()
                    }
                    is NextPageEvent -> {
                        nextPage()
                    }
                    is RestoreStateEvent -> {
                        restoreState()
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "launchJob: Exception: ${e}, ${e.cause}")
            }
        }
    }

    private suspend fun restoreState() {
        loading.value = true
        val results: MutableList<Movie> = mutableListOf()
        for (p in 1..page.value) {
            val result = repository.listMovies(page = p, query = query.value)
            results.addAll(result)
            if (p == page.value) {
                movies.value = results
                loading.value = false
            }
        }
    }

    private suspend fun listMovies() {
        loading.value = true
        resetSearchState()
        val result =
            repository.listMovies(page = 1, query = query.value, categoryId = categoryId.value)
        movies.value = result
        loading.value = false
    }

    private suspend fun nextPage() {
        if ((movieListScrollPosition + 1) >= (page.value * PAGE_SIZE)) {
            loading.value = true
            incrementPage()
            if (page.value > 1) {
                val result = repository.listMovies(
                    page = page.value,
                    query = query.value,
                    categoryId = categoryId.value
                )
                appendMovies(result)
            }
            loading.value = false
        }
    }

    private fun appendMovies(movies: List<Movie>) {
        val current = ArrayList(this.movies.value)
        current.addAll(movies)
        this.movies.value = current
    }

    private fun incrementPage() {
        setPage(page.value + 1)
    }

    fun onChangeMovieScrollPosition(position: Int) {
        setListScrollPosition(position)
    }

    fun onQueryChanged(query: String) {
        setQuery(query)
    }

    private fun onCategoryIdChanged(newCategory: MovieCategory?) {
        this.categoryId.value = getIdByCategory(newCategory)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getMovieCategory(category)
        setSelectedCategory(newCategory)
        onCategoryIdChanged(newCategory)
    }

    fun onChangeCategoryScrollPosition(position: Float) {
        categoryScrollPosition = position
    }

    private fun setListScrollPosition(position: Int) {
        movieListScrollPosition = position
        savedStateHandle.set(STATE_KEY_LIST_POSITION, position)
    }

    private fun setPage(page: Int) {
        this.page.value = page
        savedStateHandle.set(STATE_KEY_PAGE, page)
    }

    private fun setSelectedCategory(category: MovieCategory?) {
        selectedCategory.value = category
        savedStateHandle.set(STATE_KEY_SELECTED_CATEGORY, category)
    }

    private fun resetSearchState() {
        movies.value = listOf()
        setPage(1)
        setListScrollPosition(0)
    }

    private fun setQuery(query: String) {
        this.query.value = query
        savedStateHandle.set(STATE_KEY_QUERY, query)
    }
}