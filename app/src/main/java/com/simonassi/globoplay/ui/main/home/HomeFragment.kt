package com.simonassi.globoplay.ui.main.home

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import com.simonassi.globoplay.databinding.FragmentHomeBinding
import com.simonassi.globoplay.databinding.NetworkErrorLayoutBinding
import com.simonassi.globoplay.utilities.Utils
import com.simonassi.globoplay.viewmodels.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), LifecycleObserver {

    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentHomeBinding
    val scope = MainScope() // could also use an other scope such as viewModelScope if available
    var job: Job? = null
    private val TRANSITION_TIME_MILISECONDS = 4000L


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        if(context?.let { Utils.isNetworkAvailable(it) } != true){
            val errorBinding = NetworkErrorLayoutBinding.inflate(inflater, container, false)
            return errorBinding.root
        }

        binding = FragmentHomeBinding.inflate(inflater, container, false)
        context ?: return binding.root

        val movieAdapter = MovieAdapter()
        binding.movieList.adapter = movieAdapter

        val tvAdapter = TvAdapter()
        binding.tvList.adapter = tvAdapter

        val carouselAdapter = CarouselAdapter()
        binding.carouselRecyclerView.adapter = carouselAdapter

        subscribeUi(movieAdapter, tvAdapter, carouselAdapter)
        setTabBarAnimation()

        homeViewModel.getMovies()
        homeViewModel.getTvs()
        homeViewModel.getTrending()

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onDestroyView() {
        stopAutoScroll()
        super.onDestroyView()
    }

    private fun setupAutoScroll(){
        val recyclerView = binding.carouselRecyclerView
        stopAutoScroll()
        job = scope.launch {
            var position = 0
            while(true) {
                delay(TRANSITION_TIME_MILISECONDS)
                position = if(position < recyclerView.adapter!!.itemCount -1){
                    position+1
                }else{ 0 }
                recyclerView.adapter?.let { recyclerView.smoothScrollToPosition(position) }
            }
        }
    }

    private fun stopAutoScroll(){
        job?.cancel()
        job = null
    }

    private fun setTabBarAnimation(){
        val mainScrollView = binding.homeScrollView
        val tabBarLayout = binding.tabBarLayout
        mainScrollView.viewTreeObserver.addOnScrollChangedListener {
            val scrollY: Int = mainScrollView.scrollY // For ScrollView
            tabBarLayout.background.alpha = getAlpha(scrollY, 600)
        }
    }

    private fun getAlpha(position: Int, viewHeight: Int): Int {
        if ((position > viewHeight) || (viewHeight <= 0)) {
            return 255
        }
        return (position * 255) / viewHeight
    }

    private fun subscribeUi(movieAdapter: MovieAdapter, tvAdapter: TvAdapter, carouselAdapter: CarouselAdapter) {
        homeViewModel.moviesLiveData.observe(viewLifecycleOwner, Observer { movies ->
            movieAdapter.submitList(movies)
        })

        homeViewModel.tvsLiveData.observe(viewLifecycleOwner, Observer { tvs ->
            tvAdapter.submitList(tvs)
        })

        homeViewModel.trendingLiveData.observe(viewLifecycleOwner, Observer { movies ->
            carouselAdapter.submitList(movies)
            setupAutoScroll()
        })
    }
}