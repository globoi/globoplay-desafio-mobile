package com.app.fakegloboplay.features.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.fakegloboplay.R
import com.app.fakegloboplay.databinding.FragmentMoviesBinding
import com.app.fakegloboplay.features.commons.BaseFragment
import com.app.fakegloboplay.features.commons.ListMoviePageViewState
import com.app.fakegloboplay.features.home.adapters.PopularAdapter
import com.app.fakegloboplay.features.mylist.screen.adapter.OnItemClickListener
import com.app.fakegloboplay.network.response.Movie
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeMoviesFragment : BaseFragment() {

    private val homeViewModel: HomeViewModel by viewModel()
    private val popularAdapter = PopularAdapter()
    private var popularData: List<Movie> = listOf()
    private var currentPage: Int = 1
    private var nextPage: Int = 0

    private var _binding: FragmentMoviesBinding? = null
    private val binding: FragmentMoviesBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {
            showLoading()
            rvTvPopulary.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = popularAdapter
                addOnScrollListener(object : RecyclerView.OnScrollListener() {
                    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                        super.onScrolled(recyclerView, dx, dy)
                        if (!recyclerView.canScrollHorizontally(1)) {
                            homeViewModel.getMoviesPopular(nextPage)
                        }
                    }
                })
            }
        }

        popularAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: Movie, position: Int) {
                navDetailMovie(item)
            }
        })

        with(homeViewModel) {
            listPopularState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ListMoviePageViewState.Success -> {
                        popularData = popularData + state.listMovie.results
                        popularAdapter.submitList(popularData)
                        hideLoading()
                        currentPage = state.listMovie.page
                        nextPage = state.listMovie.nextPage()
                    }

                    ListMoviePageViewState.Error -> navError()
                    ListMoviePageViewState.Empty -> {}
                }
            }
            getMoviesPopular(currentPage)
        }
    }

    override fun showLoading() {
        binding.listProgressBar.isVisible = true
    }

    override fun hideLoading() {
        binding.listProgressBar.isVisible = false
    }

    private fun navError() {
        hideLoading()
        this.findNavController().navigate(R.id.errorFragment)
    }

    private fun navDetailMovie(item: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movie", item)
        this.findNavController()
            .navigate(R.id.detailMovieFragment, bundle)
    }
}