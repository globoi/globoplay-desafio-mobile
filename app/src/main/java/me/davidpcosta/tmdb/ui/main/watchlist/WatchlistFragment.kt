package me.davidpcosta.tmdb.ui.main.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.adapters.MovieAdapter
import me.davidpcosta.tmdb.toast


class WatchlistFragment : Fragment() {

    private lateinit var watchlistViewModel: WatchlistViewModel
    private lateinit var watchlistGrid: GridView
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_watchlist, container, false)

        watchlistViewModel = ViewModelProvider(this, WatchlistViewModelFactory()).get(WatchlistViewModel::class.java)
        movieAdapter = MovieAdapter(requireActivity().applicationContext)

        watchlistGrid = view.findViewById<GridView>(R.id.watchlist).apply {
            adapter = movieAdapter
            onItemClickListener =  AdapterView.OnItemClickListener { parent, view, position, id ->
                requireActivity().toast(watchlistViewModel.movies.value!![position].title)
            }
        }

        watchlistViewModel.fetchWatchlist()
        watchlistViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAdapter.movies = it
            movieAdapter.notifyDataSetChanged()
        })

        return view
    }
}
