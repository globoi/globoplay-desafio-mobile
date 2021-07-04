package com.maslima.globo_play_recrutamento.presentation.ui.movie_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maslima.globo_play_recrutamento.domain.model.Movie
import com.maslima.globo_play_recrutamento.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieListViewModel
@ViewModelInject
constructor(
    private val repository: MovieRepository
) : ViewModel() {

    val movies: MutableState<List<Movie>> = mutableStateOf(listOf())
    val query = mutableStateOf("")
    val categoryId = mutableStateOf(0)
    val selectedCategory: MutableState<MovieCategory?> = mutableStateOf(null)
    var categoryScrollPosition: Float = 0f
    val loading = mutableStateOf(false)

    init {
        listMovies()
    }

    fun listMovies() {
        viewModelScope.launch {
            loading.value = true
            resetSearchState()
            val result =
                repository.listMovies(page = 1, query = query.value, categoryId = categoryId.value)
            movies.value = result
            loading.value = false
        }
    }

    fun onQueryChanged(query: String) {
        this.query.value = query
    }

    private fun onCategoryIdChanged(newCategory: MovieCategory?) {
        this.categoryId.value = getIdByCategory(newCategory)
    }

    fun onSelectedCategoryChanged(category: String) {
        val newCategory = getMovieCategory(category)
        selectedCategory.value = newCategory
        onCategoryIdChanged(newCategory)
        onQueryChanged(category)
    }

    fun onChangeCategoryScrollPosition(position: Float) {
        categoryScrollPosition = position
    }

    private fun resetSearchState() {
        movies.value = listOf()
        if (selectedCategory.value?.value != query.value)
            clearSelectedCategory()
    }

    private fun clearSelectedCategory() {
        selectedCategory.value = null
    }
}