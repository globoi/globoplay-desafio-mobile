package com.ftoniolo.globoplay.presentation.details

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ftoniolo.globoplay.presentation.details.moviedetails.MovieDetailsFragment
import com.ftoniolo.globoplay.presentation.details.watchtoo.WatchTooFragment

class TabViewPagerAdapter(
    parentFragment: Fragment,
    private val tabs: List<Int>,
    tabViewPagerArgs: TabViewPagerArgs,
) : FragmentStateAdapter(parentFragment) {
    private val fragments =
        arrayOf(MovieDetailsFragment(tabViewPagerArgs),
            WatchTooFragment(tabViewPagerArgs.id))

    override fun getItemCount() = tabs.size

    override fun createFragment(position: Int) = fragments[position]
}