package com.mazer.globoplayapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mazer.globoplayapp.domain.entities.Movie
import com.mazer.globoplayapp.presentation.ui.details.tabs.details.DetailsFragment
import com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation.GetRecommendation
import com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation.RecommendationFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    fun getRecommendationList(){

    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> {
                val recommendationFragment = RecommendationFragment()
                recommendationFragment.setRecommendationListener(object : GetRecommendation{
                    override fun onGetMovieList(recommendationList: List<Movie>) {
                        recommendationFragment.onGetMovieList(recommendationList)
                    }

                })
                recommendationFragment
            }
            1 -> DetailsFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }


}