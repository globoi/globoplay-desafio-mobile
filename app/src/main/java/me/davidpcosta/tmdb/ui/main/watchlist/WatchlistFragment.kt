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
import me.davidpcosta.tmdb.adapters.MovieAdapter
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.toast
import me.davidpcosta.tmdb.ui.highlight.HighlightActivity


class WatchlistFragment : Fragment() {

    private lateinit var watchlistViewModel: WatchlistViewModel
    private lateinit var watchlistGrid: GridView
    private lateinit var movieAdapter: MovieAdapter
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_main_fragment_watchlist, container, false)
        sharedPreferences = requireActivity().getSharedPreferences("user_login", Context.MODE_PRIVATE)
        val sessionId = sharedPreferences.getString("session_id", "")
        val accountId = sharedPreferences.getLong("account_id", 0)

        watchlistViewModel = ViewModelProvider(this, WatchlistViewModelFactory()).get(WatchlistViewModel::class.java)
        movieAdapter = MovieAdapter(requireActivity().applicationContext)

        watchlistGrid = view.findViewById<GridView>(R.id.watchlist).apply {
            adapter = movieAdapter
            onItemClickListener =  AdapterView.OnItemClickListener { parent, view, position, id ->
                val movie = watchlistViewModel.movies.value!![position]
                requireActivity().toast(movie.title)
                goToMovie(movie)
            }
        }

        watchlistViewModel.fetchWatchlist(accountId, sessionId)
        watchlistViewModel.movies.observe(viewLifecycleOwner, Observer {
            movieAdapter.movies = it
            movieAdapter.notifyDataSetChanged()
        })

        return view
    }

    private fun goToMovie(movie: Movie) {
        val intent = Intent(requireActivity(), HighlightActivity::class.java)
        intent.putExtra("movie", movie)
        requireActivity().startActivity(intent)
    }
}
