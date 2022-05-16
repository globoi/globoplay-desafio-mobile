package com.simonassi.globoplay.ui.highlights

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.simonassi.globoplay.ui.highlights.details.DetailsFragment
import com.simonassi.globoplay.ui.highlights.morecontent.MoreContentFragment

class TabAdapter (activity: AppCompatActivity) : FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> MoreContentFragment.newInstance()
            1 -> DetailsFragment.newInstance()
            else -> MoreContentFragment.newInstance()
        }
    }

}