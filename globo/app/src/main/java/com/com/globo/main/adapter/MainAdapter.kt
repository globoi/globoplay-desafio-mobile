package com.com.globo.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.com.globo.main.view.MovieListMainView
import com.com.globo.repository.MoviesCacheRepository
import com.com.globo.R
import com.com.globo.repository.model.MoviesTitleCategory
import com.com.globo.main.viewmodel.MovieCategoryErrorRequest

class MainAdapter(
    private val listMoviesTitleCategory: MutableList<MoviesTitleCategory>,
    private val errorsListener: MutableLiveData<MovieCategoryErrorRequest>
) :
    RecyclerView.Adapter<MainViewHolder>() {

    private val moviesCacheRepository =
        MoviesCacheRepository.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(
                    R.layout.item_movie_list_main, parent, false
                ) as MovieListMainView,
            errorsListener,
            moviesCacheRepository
        )
    }

    override fun getItemCount() = listMoviesTitleCategory.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bind(listMoviesTitleCategory[position])
    }
}