package com.mazer.globoplayapp.presentation.ui.main.mylist

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.FragmentMyListBinding
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.adapter.CarouselMoviesAdapter
import com.mazer.globoplayapp.presentation.adapter.decorator.RecommendationListDecoration
import com.mazer.globoplayapp.presentation.ui.details.MovieDetailsActivity
import com.mazer.globoplayapp.utils.AppConstants
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment: Fragment() {

    private lateinit var binding: FragmentMyListBinding
    private val viewModel : MyListViewModel by viewModel()
    private lateinit var adapter: CarouselMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = CarouselMoviesAdapter {
            goToMovieDetailsScreen(it)
        }
        binding = FragmentMyListBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        registerObservers()
    }

    private fun registerObservers() {
        viewModel.myFavoritesList.observe(viewLifecycleOwner, Observer {
            setupView(it)
        })
    }

    private fun setupView(movieList: List<Movie>?) {
        adapter.setList(movieList)
    }

    private fun goToMovieDetailsScreen(movie: Movie){
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(AppConstants.MOVIE_EXTRA, movie)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        val verticalSpaceHeight = resources.getDimensionPixelSize(R.dimen.recommend_space_height)
        val verticalSpaceItemDecoration = RecommendationListDecoration(verticalSpaceHeight)
        binding.rvMyList.layoutManager = layoutManager
        binding.rvMyList.adapter = adapter
        binding.rvMyList.addItemDecoration(verticalSpaceItemDecoration)
    }

}