package com.com.globo.main.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.com.globo.R
import com.com.globo.repository.model.MoviesTitleCategory
import com.com.globo.main.viewmodel.MovieCategoryAnswerSuccess
import com.com.globo.main.viewmodel.MovieCategoryInteracts
import com.com.globo.main.viewmodel.MovieCategoryViewModel

class MovieListMainView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val textViewCategoryTitle by lazy { findViewById<TextView>(R.id.textview_category_title) }
    private val recyclerViewMovies by lazy { findViewById<RecyclerView>(R.id.recyclerview) }

    init {
        View.inflate(context, R.layout.view_movie_list_main, this)
        recyclerViewMovies.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    internal fun reloadData(
        movie: MoviesTitleCategory,
        viewModel: MovieCategoryViewModel
    ) {
        requestData(movie, viewModel)
        textViewCategoryTitle.setText(movie.message)
    }

    private fun requestData(movie: MoviesTitleCategory, viewModel: MovieCategoryViewModel) {
        viewModel.interact(
            MovieCategoryInteracts.SearchMovieByCategory(
                moviesTitleCategory = movie,
                page = 1
            )
        )

        viewModel.answers.observe(context as LifecycleOwner, Observer {
            when (it) {
                is MovieCategoryAnswerSuccess.AddMoviesToView -> it.movies.listMovies?.let { movies ->
                    recyclerViewMovies.adapter =
                        MovieMainAdapter(movies)
                }
            }
        })
    }
}