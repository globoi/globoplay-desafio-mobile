package com.ftoniolo.globoplay.presentation.details

import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.presentation.details.moviedetails.MovieDetailsFilmsViewArgs
import com.ftoniolo.globoplay.presentation.details.moviedetails.MovieDetailsFragment
import com.ftoniolo.globoplay.presentation.details.watchtoo.WatchTooFragment

class TabViewPagerAdapter(
    fragmentActivity: FragmentActivity,
    movieDetailsArgs: MovieDetailsFilmsViewArgs
): FragmentStateAdapter(fragmentActivity) {
    val tabs = arrayOf(R.string.details, R.string.watch_too)
    private val fragments = arrayOf(MovieDetailsFragment(movieDetailsArgs), WatchTooFragment())

    override fun getItemCount() = tabs.size

    override fun createFragment(position: Int) = fragments[position]
}