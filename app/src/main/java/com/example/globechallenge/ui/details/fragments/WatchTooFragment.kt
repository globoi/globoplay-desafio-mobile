package com.example.globechallenge.ui.details.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.globechallenge.data.model.features.home.MovieToGenre
import com.example.globechallenge.data.model.features.home.Movie
import com.example.globechallenge.databinding.FragmentWatchTooBinding
import com.example.globechallenge.ui.details.activities.MovieDetailsActivity
import com.example.globechallenge.ui.details.adapters.WatchTooAdapter

class WatchTooFragment : Fragment() {

    private lateinit var binding: FragmentWatchTooBinding
    private lateinit var recyclerViewWatchToo: RecyclerView
    private lateinit var adapterWatchToo: WatchTooAdapter

    fun setMovieToGenre(movieToGenre: ArrayList<MovieToGenre>?) {
       val moviesList = mutableListOf<Movie>()
       movieToGenre?.forEach{ movieToGenreItem ->
            moviesList.addAll(movieToGenreItem.getMovies() as MutableList<Movie>)
       }
       adapterWatchToo = WatchTooAdapter(moviesList) {
           val intent = MovieDetailsActivity.getIntentMovieDetail(requireContext())
           intent.putExtra(MovieDetailsActivity.EXTRA_ID, it.id)
           intent.putParcelableArrayListExtra(MovieDetailsActivity.EXTRA_MOVIE_TO_GENRE, movieToGenre)
           ContextCompat.startActivity(requireContext(), intent, null)
       }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWatchTooBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
    }

    private fun initialize() {
        viewBind()
        setupRecyclerWatchToo()
    }

    private fun setupRecyclerWatchToo() {
        recyclerViewWatchToo.run {
            layoutManager = GridLayoutManager(context, GRID_LAYOUT_SPAN_COUNT, RecyclerView.VERTICAL, false)
            adapter = adapterWatchToo
        }
    }

    private fun viewBind() {
        recyclerViewWatchToo = binding.rvWatchToo
    }

    companion object {
        const val TITLE_MY_FAVORITE = "Assista Também"
        const val GRID_LAYOUT_SPAN_COUNT = 3
        fun newInstance(param1: String, param2: String) =
            WatchTooFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}