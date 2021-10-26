package com.com.ifood.main.adapter

import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.com.ifood.main.view.MovieListMainView
import com.com.ifood.repository.MoviesCacheRepository
import com.com.ifood.repository.model.MoviesTitleCategory
import com.com.ifood.main.viewmodel.MovieCategoryErrorRequest
import com.com.ifood.main.viewmodel.MovieCategoryViewModel

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