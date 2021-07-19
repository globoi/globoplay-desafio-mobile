package com.com.globo.main.adapter

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.com.globo.main.view.MovieListMainView
import com.com.globo.repository.MoviesCacheRepository
import com.com.globo.repository.model.MoviesTitleCategory
import com.com.globo.main.viewmodel.MovieCategoryErrorRequest
import com.com.globo.main.viewmodel.MovieCategoryViewModel

class MainViewHolder(
    itemView: MovieListMainView,
    errorsListener: MutableLiveData<MovieCategoryErrorRequest>,
    moviesCacheRepository: MoviesCacheRepository
) : RecyclerView.ViewHolder(itemView) {
    private val movieCategoryViewModel = MovieCategoryViewModel(errorsListener, moviesCacheRepository)

    fun bind(moviesTitleCategory: MoviesTitleCategory) {
        (itemView as MovieListMainView).reloadData(moviesTitleCategory, movieCategoryViewModel)
    }
}