package com.example.globoplay.views

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globoplay.R
import com.example.globoplay.databinding.FragmentAssistatambemBinding
import com.example.globoplay.domain.PopularMovie
import com.example.globoplay.domain.PopularTVSeries
import com.example.globoplay.viewmodel.MovieViewModel
import com.example.globoplay.viewmodel.TVSeriesViewModel
import com.example.globoplay.views.adapter.ClickItemMovieDetails
import com.example.globoplay.views.adapter.ClickItemSerieDetails
import com.example.globoplay.views.adapter.MovieAdapter
import com.example.globoplay.views.adapter.SerieAdapter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class AssistaTambem : Fragment(), ClickItemMovieDetails, ClickItemSerieDetails {
    private lateinit var binding:FragmentAssistatambemBinding
    private lateinit var adapterMovie:MovieAdapter
    private lateinit var adapterSerie: SerieAdapter
    private lateinit var recyclerViewSeries:RecyclerView
    private lateinit var recyclerViewMovies: RecyclerView
    private val movieViewModel: MovieViewModel by viewModel()
    private val seriesViewModel: TVSeriesViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = FragmentAssistatambemBinding.inflate(layoutInflater)
        movieViewModel.popularMovies.observe(viewLifecycleOwner) { popularMovies ->
            val layoutManager = LinearLayoutManager(context, LinearLayout.HORIZONTAL,false)
            recyclerViewMovies = binding.recyclerViewAssistaTambemSerie
            recyclerViewMovies.layoutManager = layoutManager
            recyclerViewMovies.setHasFixedSize(true)
            adapterMovie = MovieAdapter(popularMovies.shuffled().take(4),this)
            recyclerViewMovies.adapter = adapterMovie
        }


        seriesViewModel.popularSeries.observe(viewLifecycleOwner){ popularSeries ->
            val layoutManager = LinearLayoutManager(context,LinearLayout.HORIZONTAL,false)
            recyclerViewSeries = binding.recyclerViewAssistaTambem
            recyclerViewSeries.layoutManager = layoutManager
            recyclerViewSeries.setHasFixedSize(true)
            adapterSerie = SerieAdapter(popularSeries.shuffled().take(4),this)
            recyclerViewSeries.adapter = adapterSerie
        }

        callPopularMovies()
        callPopularSeries()
        return binding.root
    }


    @OptIn(DelicateCoroutinesApi::class)
    private fun callPopularMovies() {
        GlobalScope.launch {
            movieViewModel.getPopularMovies()
        }
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun callPopularSeries() {
        GlobalScope.launch {
            seriesViewModel.getPopularSeries()
        }
    }

    override fun onItemCLickListener(movie: PopularMovie) {
        val movieDetails = Intent(context,MediaDetail::class.java)
        movieDetails.putExtra("title",movie.title)
        movieDetails.putExtra("description",movie.description)
        movieDetails.putExtra("poster", movie.posterPath)
        movieDetails.putExtra("voteAverage",movie.voteAverage.toString())
        movieDetails.putExtra("releaseDate",movie.releaseDate.toString())
        startActivity(movieDetails)
    }

    override fun onItemCLickListener(serie: PopularTVSeries) {
        val serieDetails = Intent(context,MediaDetail::class.java)
        serieDetails.putExtra("title",serie.name)
        serieDetails.putExtra("description",serie.overview)
        serieDetails.putExtra("poster", serie.posterPath)
        serieDetails.putExtra("voteAverage",serie.voteAverage.toString())
        serieDetails.putExtra("releaseDate",serie.releaseDate)
        startActivity(serieDetails)
    }

}