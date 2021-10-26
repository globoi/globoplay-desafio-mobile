package com.com.ifood.details

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabsAdapter(private val mapFragments: MutableMap<String, Fragment>, fm: FragmentManager) :
    FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return mapFragments[mapFragments.keys.elementAt(position)]!!
    }

    override fun getCount(): Int {
        return mapFragments.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return mapFragments.keys.elementAt(position)
    }

    fun addFragment(title: String, fragment: Fragment) {
        mapFragments[title] = fragment
        notifyDataSetChanged()
    }
}