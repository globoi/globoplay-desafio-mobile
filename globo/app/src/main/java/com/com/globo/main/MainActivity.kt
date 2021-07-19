package com.com.globo.main

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.com.globo.R
import com.com.globo.helper.TransitionHelper
import com.com.globo.main.adapter.MainAdapter
import com.com.globo.main.adapter.MovieMyListAdapter
import com.com.globo.main.viewmodel.MovieCategoryErrorRequest
import com.com.globo.main.viewmodel.MovieMyListAnswers
import com.com.globo.main.viewmodel.MovieMyListInteract
import com.com.globo.main.viewmodel.MovieMyListViewModel
import com.com.globo.repository.MoviesTitleCategoryLocalRepository
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private val movieMyListViewModel: MovieMyListViewModel = MovieMyListViewModel()
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.recyclerview_movies) }
    private val bottomNavigationView by lazy { findViewById<BottomNavigationView>(R.id.bottom_navigation) }
    private val textViewTitle by lazy { findViewById<TextView>(R.id.textview_globoplay) }
    private val textViewError by lazy { findViewById<TextView>(R.id.textview_messages_error) }
    private var shouldShowErrorOrEmptyOnMyList: Boolean = false

    private val recyclerViewHomeConfiguration by lazy {
        RecyclerViewConfiguration(
            MainAdapter(
                MoviesTitleCategoryLocalRepository().getListMoviesTitleCategory(),
                getErrorsListener()
            ), LinearLayoutManager(this)
        )
    }

    private val recyclerViewListConfiguration by lazy {
        RecyclerViewConfiguration(
            MovieMyListAdapter(mutableListOf()),
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        TransitionHelper.enableTransition(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.adapter = recyclerViewHomeConfiguration.adapter
        recyclerView.layoutManager = recyclerViewHomeConfiguration.layoutManager

        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.page_1 -> {
                    textViewTitle.gravity = Gravity.CENTER
                    textViewTitle.setText(R.string.app_name)
                    recyclerView.visibility = View.VISIBLE
                    configureList(recyclerViewHomeConfiguration)
                    textViewError.visibility = View.GONE
                    true
                }
                R.id.page_2 -> {
                    textViewTitle.gravity = Gravity.START
                    textViewTitle.setText(R.string.my_list)

                    if (shouldShowErrorOrEmptyOnMyList) {
                        textViewError.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    } else {
                        configureList(recyclerViewListConfiguration)
                    }
                    true
                }
                else -> false
            }
        }

        movieMyListViewModel.answers.observe(this, Observer {
            when (it) {
                is MovieMyListAnswers.NotFoundMyListMovies,
                MovieMyListAnswers.ErrorMyListMovie -> configureMessageErrorMyList(R.string.not_found_list)

                is MovieMyListAnswers.MyListMovies -> {
                    shouldShowErrorOrEmptyOnMyList = false
                    recyclerViewListConfiguration.adapter.changeList(
                        it.listMovies
                    )
                }
            }
        })
    }

    private fun configureMessageErrorMyList(@StringRes message: Int) {
        shouldShowErrorOrEmptyOnMyList = true
        textViewError.setText(message)
    }

    private fun getErrorsListener(): MutableLiveData<MovieCategoryErrorRequest> {
        val errors = MutableLiveData<MovieCategoryErrorRequest>()
        errors.observe(this, Observer {
            when (it) {
                is MovieCategoryErrorRequest.ShowMessageErrorNetwork -> showMessageErrorNetwork()
            }
        })
        return errors
    }

    private fun configureList(configuration: RecyclerViewConfiguration<*>) {
        if (configuration.adapter != recyclerView.adapter ||
            recyclerView.layoutManager != configuration.layoutManager
        ) {
            recyclerView.layoutManager = configuration.layoutManager
            recyclerView.adapter = configuration.adapter
        }
    }

    private fun showMessageErrorNetwork() {
        recyclerView.visibility = View.GONE
        bottomNavigationView.visibility = View.GONE
        textViewError.visibility = View.VISIBLE

        textViewError.setText(R.string.text_error_network)
    }

    override fun onResume() {
        super.onResume()
        movieMyListViewModel.interact(MovieMyListInteract.SearchMyListMovie)
    }

    internal data class RecyclerViewConfiguration<T : RecyclerView.Adapter<*>>(
        val adapter: T,
        val layoutManager: RecyclerView.LayoutManager
    )
}
