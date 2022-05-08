package com.ftoniolo.globoplay.presentation.home

import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter

class HomeLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<HomeLoadMoreStateViewHolder>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ) = HomeLoadMoreStateViewHolder.create(parent, retry)

    override fun onBindViewHolder(holder: HomeLoadMoreStateViewHolder, loadState: LoadState) = holder
        .bind(loadState)
}