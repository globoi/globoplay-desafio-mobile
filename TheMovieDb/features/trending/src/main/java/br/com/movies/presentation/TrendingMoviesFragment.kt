package br.com.movies.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import br.com.movies.R
import br.com.ui_kit.R as uiKit

import br.com.movies.databinding.FragmentTrendingMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 * Use the [TrendingMoviesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@AndroidEntryPoint
class TrendingMoviesFragment : Fragment() {

    private lateinit var binding: FragmentTrendingMoviesBinding
    private val viewModel: TrendingMoviesViewModel by viewModels ()

    @Inject
    lateinit var adapter: TrendingMoviesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentTrendingMoviesBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        subscribeObservers()
        initToolbar()
    }

    private fun subscribeObservers() {
        collectLatestLifecycleFlow(viewModel.uiState) { launchesUiState ->
            if (launchesUiState is TrendingUiState.Success) {
                adapter.submitData(launchesUiState.pagingData)
            }
        }
    }

    private fun setupRecyclerView() {
        adapter.onItemClicked = ::openMovieDetail

        binding.layoutRecyclerviewTrending.recyclerviewTrendingMovies.addItemDecoration(
            SpacingItemDecoration(resources.getDimensionPixelSize(br.com.ui_kit.R.dimen.space_medium), true)
        )
        binding.layoutRecyclerviewTrending.recyclerviewTrendingMovies.adapter = adapter
    }

    private fun initToolbar() {


        with(binding.fragmentTrendingToolbar.toolbar) {
            inflateMenu(uiKit.menu.toolbar_main_menu)
            title = getString(uiKit.string.txt_globoplay)


        }
    }



    private fun openMovieDetail(movieId: Int) {
//        Log.d("")
       // findNavController().navigateToMovie(movieId)
    }
}