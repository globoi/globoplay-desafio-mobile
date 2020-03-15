package br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MovieDetailPageAdapter(
    private val fragments: List<MovieDetailFragment>,
    fragmentManager: FragmentManager,
    behavior: Int
) :
    FragmentPagerAdapter(fragmentManager, behavior) {

    override fun getItem(position: Int): MovieDetailFragment {
        return fragments[position]
    }

    override fun getCount(): Int {
        return fragments.count()
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragments[position].title
    }
}