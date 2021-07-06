package com.maslima.globo_play_recrutamento.presentation.components

import androidx.compose.foundation.ScrollableRow
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import com.maslima.globo_play_recrutamento.presentation.ui.movie_list.MovieCategory

@Composable
fun SearchAppBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Float,
    categories: List<MovieCategory>,
    selectedCategory: MovieCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangeCategoryScrollPosition: (Float) -> Unit
) {
    Surface(modifier = Modifier.fillMaxWidth(), elevation = Dp(8f), color = Color.White) {
        Column {
            TopAppBar(
                title = {
                    Text(
                        text = "globoplay",
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                },
                elevation = Dp(7f)
            )
            SearchBar(query, onQueryChange, onExecuteSearch)
            Spacer(modifier = Modifier.padding(Dp(4f)))
            FilterCategory(
                selectedCategory,
                scrollPosition,
                categories,
                onSelectedCategoryChanged,
                onChangeCategoryScrollPosition,
                onExecuteSearch
            )
        }
    }
}

@Composable
private fun SearchBar(query: String, onQueryChange: (String) -> Unit, onExecuteSearch: () -> Unit) {
    Row(modifier = Modifier.fillMaxWidth()) {
        TextField(
            value = query,
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(Dp(8f)),
            onValueChange = {
                onQueryChange(it)
            },
            label = {
                Text(text = "Search")
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search
            ),
            leadingIcon = {
                Icon(Icons.Filled.Search)
            },
            onImeActionPerformed = { action, soft ->
                if (action == ImeAction.Search) {
                    onExecuteSearch()
                    soft?.hideSoftwareKeyboard()
                }
            },
            textStyle = TextStyle(color = MaterialTheme.colors.onSurface),
            backgroundColor = MaterialTheme.colors.surface
        )
    }
}

@Composable
private fun FilterCategory(
    selectedCategory: MovieCategory?,
    scrollPosition: Float,
    categories: List<MovieCategory>,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangeCategoryScrollPosition: (Float) -> Unit,
    onExecuteSearch: () -> Unit,
) {
    val scrollState = rememberScrollState()
    ScrollableRow(
        modifier = Modifier.padding(start = Dp(8f), bottom = Dp(8f)),
        scrollState = scrollState
    ) {
        scrollState.scrollTo(scrollPosition)
        for (category in categories) {
            MovieCategoryChip(
                category = category.value,
                isSelected = selectedCategory == category,
                onExecuteSearch = { onExecuteSearch() },
                onSelectedCategoryChanged = {
                    onSelectedCategoryChanged(it)
                    onChangeCategoryScrollPosition(scrollState.value)
                }
            )
        }
    }
}
