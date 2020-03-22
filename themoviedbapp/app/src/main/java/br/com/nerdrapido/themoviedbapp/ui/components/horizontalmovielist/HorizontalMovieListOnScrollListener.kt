package br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist

import androidx.recyclerview.widget.LinearLayoutManager
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListOnScrollListener

/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class HorizontalMovieListOnScrollListener(
    pageSize: Int,
    lastPage: Int,
    layoutManager: LinearLayoutManager,
    onNextPageNeeded: OnNextPageNeeded
) : MovieListOnScrollListener(pageSize, lastPage, layoutManager, onNextPageNeeded)