package me.davidpcosta.tmdb.ui.main.watchlist

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.ui.highlight.HighlightActivity


class WatchlistFragment : Fragment() {

    private lateinit var watchlistViewModel: WatchlistViewModel
    private lateinit var watchlistGrid: GridView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var sessionId: String
    private var accountId: Long = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_main_fragment_watchlist, container, false)

        sharedPreferences = requireActivity().getSharedPreferences(getString(R.string.const_shared_preference), Context.MODE_PRIVATE)
        watchlistViewModel = ViewModelProvider(this, WatchlistViewModelFactory(requireActivity().applicationContext)).get(WatchlistViewModel::class.java)
        movieAdapter = MovieAdapter(requireActivity().applicationContext)
        sessionId = sharedPreferences.getString(getString(R.string.const_key_session_id), "")!!
        accountId = sharedPreferences.getLong(getString(R.string.const_key_account_id), 0)

        // Grid view
        watchlistGrid = view.findViewById<GridView>(R.id.watchlist).apply {
            adapter = movieAdapter
            onItemClickListener =  AdapterView.OnItemClickListener { _, _, position, _ ->
                goToMovie(this.adapter.getItem(position) as Movie)
            }
        }

        observeWatchlist()
        return view
    }

    override fun onResume() {
        super.onResume()
        watchlistViewModel.updateFromLocalWatchlist()
    }

    private fun observeWatchlist() {
        watchlistViewModel.fetchWatchlist(accountId, sessionId)
        watchlistViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAdapter.movies = it.sortedBy { movie -> movie.title }
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun goToMovie(movie: Movie) {
        val intent = Intent(requireActivity(), HighlightActivity::class.java)
        intent.putExtra(getString(R.string.const_key_movie), movie)
        requireActivity().startActivity(intent)
    }
}
