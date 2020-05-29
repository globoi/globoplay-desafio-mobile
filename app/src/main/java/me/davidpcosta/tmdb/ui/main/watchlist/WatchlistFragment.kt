package me.davidpcosta.tmdb.ui.main.watchlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.data.model.Movie


class WatchlistFragment : Fragment() {

    private lateinit var watchlistViewModel: WatchlistViewModel

    private var movies: List<Movie> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        watchlistViewModel = ViewModelProvider(this, WatchlistViewModelFactory()).get(WatchlistViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_watchlist, container, false)
        val watchlistGrid: GridView = view.findViewById(R.id.watchlist)

        val movieAdapter = MovieAdapter(activity!!.applicationContext)
        watchlistGrid.adapter = movieAdapter

        watchlistViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAdapter.movies = it
            movieAdapter.notifyDataSetChanged()
        })

        watchlistViewModel.fetchWatchlist()

        return view
    }
}
