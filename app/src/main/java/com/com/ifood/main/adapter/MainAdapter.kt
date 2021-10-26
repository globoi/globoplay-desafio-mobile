package com.com.ifood.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.com.ifood.main.view.MovieListMainView
import com.com.ifood.repository.MoviesCacheRepository
import com.com.ifood.R
import com.com.ifood.repository.model.MoviesTitleCategory
import com.com.ifood.main.viewmodel.MovieCategoryErrorRequest

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