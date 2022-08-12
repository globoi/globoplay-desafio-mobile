package com.example.globoplay.views.inicio

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globoplay.R
import com.example.globoplay.databinding.FragmentHomeBinding
import com.example.globoplay.domain.PopularMovie
import com.example.globoplay.domain.PopularTVSeries
import com.example.globoplay.views.adapter.MovieAdapter
import com.example.globoplay.viewmodel.MovieViewModel
import com.example.globoplay.viewmodel.TVSeriesViewModel
import com.example.globoplay.views.MediaDetail
import com.example.globoplay.views.adapter.ClickItemMovieDetails
import com.example.globoplay.views.adapter.ClickItemSerieDetails
import com.example.globoplay.views.adapter.SerieAdapter
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class InicioFragment : Fragment(R.layout.fragment_home),ClickItemMovieDetails,ClickItemSerieDetails {
    private lateinit var adapterMovie:MovieAdapter
    private lateinit var adapterSerie:SerieAdapter
    private lateinit var recyclerViewMovies:RecyclerView
    private lateinit var recyclerViewSeries:RecyclerView
    private var _binding: FragmentHomeBinding? = null
    private val movieViewModel: MovieViewModel by viewModel()
    private val seriesViewModel: TVSeriesViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentHomeBinding.inflate(layoutInflater)

        movieViewModel.popularMovies.observe(viewLifecycleOwner) { popularMovies ->
            val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            recyclerViewMovies = view.findViewById(R.id.recyclerView_Cinema)
            recyclerViewMovies.layoutManager = layoutManager
            recyclerViewMovies.setHasFixedSize(true)
            adapterMovie = MovieAdapter(popularMovies,this)
            recyclerViewMovies.adapter = adapterMovie
        }

        seriesViewModel.popularSeries.observe(viewLifecycleOwner){ popularSeries ->
            val layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL,false)
            recyclerViewSeries = view.findViewById(R.id.recyclerView_Series)
            recyclerViewSeries.layoutManager = layoutManager
            recyclerViewSeries.setHasFixedSize(true)
            adapterSerie = SerieAdapter(popularSeries,this)
            recyclerViewSeries.adapter = adapterSerie
        }

        seriesViewModel.popularSeries.observe(viewLifecycleOwner){ popularSeries ->
            val layoutManager = LinearLayoutManager(context,RecyclerView.HORIZONTAL,false)
            recyclerViewSeries = view.findViewById(R.id.recyclerView_Novelas)
            recyclerViewSeries.layoutManager = layoutManager
            recyclerViewSeries.setHasFixedSize(true)
            adapterSerie = SerieAdapter(popularSeries,this)
            recyclerViewSeries.adapter = adapterSerie
        }

        callPopularSeries()
        callPopularMovies()

        super.onViewCreated(view, savedInstanceState)
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemCLickListener(movie: PopularMovie) {
        val movieDetails = Intent(context,MediaDetail::class.java)
        movieDetails.putExtra("title",movie.title)
        Log.i("movie",movie.title)
        movieDetails.putExtra("description",movie.description)
        movieDetails.putExtra("poster", movie.posterPath)
        movieDetails.putExtra("voteAverage",movie.voteAverage.toString())
        movieDetails.putExtra("releaseDate",movie.releaseDate.toString())
        startActivity(movieDetails)
    }

    override fun onItemCLickListener(serie: PopularTVSeries) {
        val serieDetails = Intent(context,MediaDetail::class.java)
        Log.i("serie",serie.name)
        serieDetails.putExtra("title",serie.name)
        serieDetails.putExtra("description",serie.overview)
        serieDetails.putExtra("poster", serie.posterPath)
        serieDetails.putExtra("voteAverage",serie.voteAverage.toString())
        serieDetails.putExtra("releaseDate",serie.releaseDate)
        startActivity(serieDetails)
    }

}