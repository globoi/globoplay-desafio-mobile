package com.mazer.globoplayapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mazer.globoplayapp.presentation.ui.details.tabs.details.DetailsFragment
import com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation.RecommendationFragment

class PagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> RecommendationFragment()
            1 -> DetailsFragment()
            else -> throw IllegalArgumentException("Invalid tab position")
        }
    }


}