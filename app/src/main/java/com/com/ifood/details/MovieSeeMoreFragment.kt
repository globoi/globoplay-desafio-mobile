package com.com.ifood.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.com.ifood.R
import com.com.ifood.details.MovieDetailsActivity.Companion.EXTRA_LIST_MOVIE
import com.com.ifood.repository.model.Movie


class MovieSeeMoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_see_more, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().intent.getParcelableArrayListExtra<Movie>(EXTRA_LIST_MOVIE)?.let {
            val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
            recyclerView.adapter = MovieSeeMoreAdapter(it)
        }
    }
}
