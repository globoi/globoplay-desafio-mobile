package br.com.nerdrapido.themoviedbapp.ui.components.verticamovielist

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListOnScrollListener

/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class VerticalMovieListOnScrollListener(
    pageSize: Int,
    lastPage: Int,
    layoutManager: LinearLayoutManager,
    onNextPageNeeded: OnNextPageNeeded
) : MovieListOnScrollListener(pageSize, lastPage, layoutManager, onNextPageNeeded) {

}