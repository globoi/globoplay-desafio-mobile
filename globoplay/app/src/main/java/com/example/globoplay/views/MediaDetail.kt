package com.example.globoplay.views

import android.content.res.Resources
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.globoplay.R
import com.example.globoplay.databinding.ActivityMovieDetailBinding
import com.example.globoplay.viewmodel.MovieViewModel
import com.example.globoplay.views.adapter.MovieAdapter
import com.example.globoplay.views.adapter.PagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import org.koin.androidx.viewmodel.ext.android.viewModel

class MediaDetail() : AppCompatActivity() {

    private lateinit var binding:ActivityMovieDetailBinding
    private lateinit var tabLayout: TabLayout
    private lateinit var resume:String
    private lateinit var viewPager:ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getData()
        tabLayout = binding.tabLayout
        viewPager = binding.viewPager
        viewPager.adapter = PagerAdapter(this,resume)


        TabLayoutMediator(tabLayout,viewPager){ tab, index ->
            tab.text = when(index){
                0 -> {"Assista Também"}
                1-> {"Detalhes"}
                else->{throw Resources.NotFoundException("Position failed")}
            }

        }.attach()

    }

    private fun getData(){
        val nome = intent.getStringExtra("title")
        val description = intent.getStringExtra("description").toString()
        val poster = intent.getStringExtra("poster")
        val releaseDate = intent.getStringExtra("releaseDate")
        val voteAverage = intent.getStringExtra("voteAverage")

        resume = "Nome: $nome\nData de Lançamento: $releaseDate\nNota IMDB: $voteAverage\n"

        Picasso.get().load("https://image.tmdb.org/t/p/original$poster")
           .transform(BlurTransformation(this,40))
           .into(binding.mediaBackground)

        Picasso.get().load("https://image.tmdb.org/t/p/w500$poster")
          .into(binding.mediaPoster)

        binding.mediaTitle.text = nome.toString()
        binding.mediaDescription.text = description
    }
}


