package com.nroncari.movieplay.presentation.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nroncari.movieplay.presentation.ui.fragment.DetailsMovieFragment
import com.nroncari.movieplay.presentation.ui.fragment.ListRecommendationsFragment

class TabsAdapter(fm: FragmentActivity) :
    FragmentStateAdapter(fm) {

    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> ListRecommendationsFragment()
            1 -> DetailsMovieFragment()
            else -> ListRecommendationsFragment()
        }
    }
}