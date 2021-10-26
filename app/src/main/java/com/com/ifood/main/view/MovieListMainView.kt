package com.com.ifood.main.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.com.ifood.R
import com.com.ifood.main.viewmodel.MovieCategoryAnswerSuccess
import com.com.ifood.main.viewmodel.MovieCategoryInteracts
import com.com.ifood.main.viewmodel.MovieCategoryViewModel
import com.com.ifood.repository.model.MoviesTitleCategory

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
        recyclerViewMovies.adapter = MovieMainAdapter()
        requestData(movie, viewModel)
        textViewCategoryTitle.setText(movie.message)
        recyclerViewMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {

            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val visibleItemCount: Int = recyclerView.layoutManager!!.childCount
                val totalItemCount: Int = recyclerView.layoutManager!!.itemCount

                val firstVisibleItemPosition: Int = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount - 7
                    && firstVisibleItemPosition >= 0) {
                    viewModel.interact(MovieCategoryInteracts.SearchMoreMovie)
                }

            }
        })
    }

    private fun requestData(movie: MoviesTitleCategory, viewModel: MovieCategoryViewModel) {
        viewModel.interact(
            MovieCategoryInteracts.SearchMovieByCategory(
                moviesTitleCategory = movie
            )
        )

        viewModel.answers.observe(context as LifecycleOwner, Observer {
            when (it) {
                is MovieCategoryAnswerSuccess.AddMoviesToView -> it.movies.listMovies?.let { movies ->
                    (recyclerViewMovies.adapter as MovieMainAdapter).addMovie(movies)
                }
            }
        })
    }
}