package com.mazer.globoplayapp.presentation.ui.details

import android.os.Bundle
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.ActivityMovieDetailsBinding
import com.mazer.globoplayapp.presentation.adapter.PagerAdapter


class MovieDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView(){
        setupTabLayout()
    }

    private fun setupTabLayout(){
        val pagerAdapter = PagerAdapter(this)
        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            when (pagerAdapter.getItemId(position)) {
                0L -> {
                    tab.text = getString(R.string.tab_movie_recommendation)
                }
                1L -> {
                    tab.text = getString(R.string.tab_movie_details)
                }

            }

            if (position == binding.viewPager.currentItem){
                tab.customView?.findViewById<TextView>(binding.tabLayout.id)?.setTextAppearance(R.style.TabLayoutTextActive)
            }else{
                tab.customView?.findViewById<TextView>(binding.tabLayout.id)?.setTextAppearance(R.style.TabLayoutTextInactive)
            }
        }.attach()
    }
}