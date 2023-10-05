package com.mazer.globoplayapp.presentation.adapter.pagination

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationScrollListener(private val layoutManager: LinearLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var loading = true
    private var currentPage = 1

    abstract fun loadMoreItems(currentPage: Int)

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        val visibleItemCount = layoutManager.childCount
        val totalItemCount = layoutManager.itemCount
        val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

        if (loading && (visibleItemCount + firstVisibleItemPosition) >= totalItemCount) {
            loading = false
            currentPage++
            loadMoreItems(currentPage)
        }
    }

    fun setLoaded() {
        loading = true
    }
}