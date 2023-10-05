package com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.FragmentRecommendationListBinding
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.adapter.CarouselMoviesAdapter
import com.mazer.globoplayapp.presentation.adapter.decorator.RecommendationListDecoration
import com.mazer.globoplayapp.presentation.ui.details.MovieDetailsActivity
import com.mazer.globoplayapp.utils.AppConstants
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Este Fragment exibirá uma lista de filmes em 3 colunas
 * será usado no "Assista Também" presente no MovieDetailsActivity
 */
class RecommendationFragment : Fragment() {

    private lateinit var adapter: CarouselMoviesAdapter
    private lateinit var binding: FragmentRecommendationListBinding
    private val viewModel : RecommendationViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            viewModel.setExtras(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = CarouselMoviesAdapter {
            goToMovieDetailsScreen(it)
        }
        binding = FragmentRecommendationListBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        registerObservers()
    }

    private fun registerObservers() {
        viewModel.recommendationList.observe(viewLifecycleOwner, Observer {
            adapter.setList(it)
        } )
    }

    private fun goToMovieDetailsScreen(movie: Movie){
        activity?.finish()
        val intent = Intent(activity, MovieDetailsActivity::class.java)
        intent.putExtra(AppConstants.MOVIE_EXTRA, movie)
        startActivity(intent)
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        val verticalSpaceHeight = resources.getDimensionPixelSize(R.dimen.recommend_space_height)
        val verticalSpaceItemDecoration = RecommendationListDecoration(verticalSpaceHeight)
        binding.rvRecommendationList.layoutManager = layoutManager
        binding.rvRecommendationList.adapter = adapter
        binding.rvRecommendationList.addItemDecoration(verticalSpaceItemDecoration)
    }

    companion object {
        @JvmStatic
        fun newInstance(movieId: Int) =
            RecommendationFragment().apply {
                arguments = Bundle().apply {
                    putInt(AppConstants.RECOMENDATION_EXTRA, movieId)
                }
            }
    }

}