package com.mazer.globoplayapp.presentation.ui.main.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mazer.globoplayapp.databinding.FragmentHomeBinding
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.adapter.CarouselMoviesAdapter
import com.mazer.globoplayapp.presentation.ui.details.MovieDetailsActivity
import com.mazer.globoplayapp.utils.AppConstants
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel : HomeFragmentViewModel by viewModel()
    private lateinit var adapterPopularMovies: CarouselMoviesAdapter
    private lateinit var adapterTopRatedMovies: CarouselMoviesAdapter
    private lateinit var adapterupcomingMovies: CarouselMoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        registerObservers()
    }

    private fun setupView() {
        adapterPopularMovies = CarouselMoviesAdapter {
            goToMovieDetailsScreen(it)
        }
        adapterTopRatedMovies = CarouselMoviesAdapter {
            goToMovieDetailsScreen(it)
        }
        adapterupcomingMovies = CarouselMoviesAdapter {
            goToMovieDetailsScreen(it)
        }

        setupPopularMoviesCarousel()
        setupTopRatedMoviesCarousel()
        setupUpcomingMoviesCarousel()
    }

    private fun goToMovieDetailsScreen(movie: Movie){
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(AppConstants.MOVIE_EXTRA, movie)
        startActivity(intent)
    }

    private fun setupUpcomingMoviesCarousel() {
        binding.carouselViewPopular.setAdapter(adapterPopularMovies)
    }

    private fun setupTopRatedMoviesCarousel() {
        binding.carouselViewTopRated.setAdapter(adapterTopRatedMovies)
    }

    private fun setupPopularMoviesCarousel() {
        binding.carouselViewUpcoming.setAdapter(adapterupcomingMovies)
    }

    private fun registerObservers() {
        viewModel.moviePopularList.observe(viewLifecycleOwner) {
            adapterPopularMovies.setList(it)
        }
        viewModel.movieTopRatedList.observe(viewLifecycleOwner) {
            adapterTopRatedMovies.setList(it)
        }
        viewModel.movieUpcomingList.observe(viewLifecycleOwner) {
            adapterupcomingMovies.setList(it)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}