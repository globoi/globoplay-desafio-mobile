package me.davidpcosta.tmdb.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.adapters.GenreAdapter

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var genresRecyclerView: RecyclerView
    private lateinit var genreAdapter: GenreAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main_fragment_home, container, false)

        homeViewModel = ViewModelProvider(this, HomeViewModelFactory()).get(HomeViewModel::class.java)
        viewManager = LinearLayoutManager(requireActivity().applicationContext)
        genreAdapter = GenreAdapter(requireActivity().applicationContext, homeViewModel, viewLifecycleOwner)

        initComponents()
        fetchGenres()

        return view
    }

    private fun initComponents() {
        view?.let {view ->
            genresRecyclerView = view.findViewById<RecyclerView>(R.id.genres_list).apply {
                layoutManager = viewManager
                adapter = genreAdapter
            }
        }
    }

    private fun fetchGenres() {
        homeViewModel.fetchGenres()
        homeViewModel.genres.observe(viewLifecycleOwner, Observer { genres ->
            genreAdapter.genres = genres
            genreAdapter.notifyDataSetChanged()
        })
    }
}
