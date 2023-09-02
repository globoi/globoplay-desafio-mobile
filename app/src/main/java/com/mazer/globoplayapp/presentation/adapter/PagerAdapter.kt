package com.mazer.globoplayapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.ui.details.tabs.details.DetailsFragment
import com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation.RecommendationFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {


    private var movieId: Int = 0

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                RecommendationFragment.newInstance(this.movieId)
            }
            1 -> DetailsFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }
    // Função para atualizar o objeto no RecommendationFragment
    fun setMovieId(movieId: Int) {
        this.movieId = movieId
    }


}