package com.globo.moviesapp.ui.panel.activity

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.globo.moviesapp.R
import com.globo.moviesapp.shared.FragmentTabHelper
import com.globo.moviesapp.ui.genre.fragment.GenreMoviesFragment
import com.globo.moviesapp.ui.genre.viewmodel.GenreViewModel
import com.globo.moviesapp.ui.movie.fragment.MovieFavoriteFragment
import com.globo.moviesapp.ui.movie.viewmodel.MovieViewModel
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_panel.*
import javax.inject.Inject

class PanelActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val genreViewModel: GenreViewModel by viewModels {
        viewModelFactory
    }

    private val movieViewModel: MovieViewModel by viewModels {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_panel)
        setupTab()
        setSupportActionBar(panelToolbar)

        genreViewModel.apiKey = getString(R.string.api_key_movie_db)
        genreViewModel.languageLocale = getString(R.string.locale_movie_db)

        movieViewModel.apiKey = getString(R.string.api_key_movie_db)
        movieViewModel.languageLocale = getString(R.string.locale_movie_db)
    }

    private fun setupTab(){
        val icons = arrayListOf(
            R.drawable.ic_baseline_home_white_24,
            R.drawable.ic_baseline_star_white_24,
        )

        val titleTab = arrayListOf(
            getString(R.string.title_tab_home),
            getString(R.string.title_tab_my_list)
        )

        val fragments = arrayListOf<Fragment>(
            GenreMoviesFragment.newInstance(),
            MovieFavoriteFragment.newInstance()
        )

        val tabAdapter = FragmentTabHelper(this, fragments)
        panelViewPager.isUserInputEnabled = false
        panelViewPager.adapter = tabAdapter

        panelTabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {
                changeTitleToolbar(tab!!.position)
            }
            override fun onTabUnselected(tab: TabLayout.Tab?) {}

            override fun onTabReselected(tab: TabLayout.Tab?) {}

        })
        TabLayoutMediator(panelTabLayout, panelViewPager) { tab, position ->
            tab.text = titleTab[position]
            tab.icon = ContextCompat.getDrawable(applicationContext, icons[position])
        }.attach()
    }

    private fun changeTitleToolbar(position: Int){
        tvTitleGloboplay.textAlignment = if(position == 1) View.TEXT_ALIGNMENT_TEXT_START
        else View.TEXT_ALIGNMENT_CENTER
        tvTitleGloboplay.text = if(position == 1) getString(R.string.title_tab_my_list)
        else getString(R.string.app_name)
    }
}