package com.app.fakegloboplay.features.detailmovie.screen.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.app.fakegloboplay.features.detailmovie.screen.DetailFragmentViewPage

class DetailViewPageAdapter(
    fragment: Fragment,
    private val seriesId: Int,
    private val mediaType: String?
) : FragmentStateAdapter(fragment) {

    private var fragments: MutableList<DetailFragmentViewPage> = arrayListOf()

    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment =
        fragments[position].apply {
            setParamSeriesId(seriesId)
            setParamMediaType(mediaType)
        }

    fun addFragment(itemFragment: DetailFragmentViewPage) {
        fragments.add(itemFragment)
        notifyDataSetChanged()
    }
}