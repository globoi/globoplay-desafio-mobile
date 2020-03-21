package br.com.nerdrapido.themoviedbapp.ui.components.abstracts

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
abstract class MovieListOnScrollListener(
    private val pageSize: Int,
    private val lastPage: Int,
    private val layoutManager: LinearLayoutManager,
    private val onNextPageNeeded: OnNextPageNeeded
) : RecyclerView.OnScrollListener()  {

    private var page = 1

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount: Int = layoutManager.childCount
        val totalItemCount: Int = layoutManager.itemCount
        val firstVisibleItemPosition: Int = layoutManager.findFirstVisibleItemPosition()
        if (page != lastPage) {
            if (visibleItemCount + firstVisibleItemPosition >= totalItemCount - 5
                && firstVisibleItemPosition >= 0 && totalItemCount >= pageSize
            ) {
                page++
                onNextPageNeeded.loadPage(page)
            }
        }
    }

    /**
     * CallBack for when the page is scrolled till the end
     */
    interface OnNextPageNeeded {
        fun loadPage(page: Int)
    }

}