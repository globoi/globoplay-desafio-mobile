package com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.FragmentRecommendationListBinding
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.adapter.CarouselMoviesAdapter

/**
 * Este Fragment exibirá uma lista de filmes em 3 colunas
 * será usado no "Assista Também" presente no MovieDetailsActivity
 */
class RecommendationFragment : Fragment() {

    private lateinit var adapter: CarouselMoviesAdapter
    private lateinit var binding: FragmentRecommendationListBinding
    private lateinit var recommendationListener: GetRecommendation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        adapter = CarouselMoviesAdapter {

        }
        binding = FragmentRecommendationListBinding.inflate(inflater,container,false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.rvRecommendationList.layoutManager = layoutManager
    }

    fun setRecommendationListener(listener: GetRecommendation) {
        recommendationListener = listener
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecommendationFragment()
    }

    fun onGetMovieList(recommendationList: List<Movie>) {
        adapter.setList(recommendationList)
    }
}