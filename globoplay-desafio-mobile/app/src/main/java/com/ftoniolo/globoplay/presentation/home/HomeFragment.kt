package com.ftoniolo.globoplay.presentation.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ftoniolo.core.domain.model.FilmPoster
import com.ftoniolo.core.domain.model.FilmsFromGenre
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding get() = _binding!!

    private val homeVerticalAdapter = HomeVerticalAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentHomeBinding.inflate(
        inflater,
        container,
        false
    ).apply {
        _binding = this
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initHomeVerticalAdapter()

        val listPosterMock = listOf(
            FilmPoster("https://image.tmdb.org/t/p/w500/soD18uucENplI81kUErvMzdb5Lm.jpg"),
            FilmPoster("https://image.tmdb.org/t/p/w500/soD18uucENplI81kUErvMzdb5Lm.jpg"),
            FilmPoster("https://image.tmdb.org/t/p/w500/soD18uucENplI81kUErvMzdb5Lm.jpg"),
            FilmPoster("https://image.tmdb.org/t/p/w500/soD18uucENplI81kUErvMzdb5Lm.jpg"),
            FilmPoster("https://image.tmdb.org/t/p/w500/soD18uucENplI81kUErvMzdb5Lm.jpg"),
            FilmPoster("https://image.tmdb.org/t/p/w500/soD18uucENplI81kUErvMzdb5Lm.jpg"),
            FilmPoster("https://image.tmdb.org/t/p/w500/soD18uucENplI81kUErvMzdb5Lm.jpg"),
            FilmPoster("https://image.tmdb.org/t/p/w500/soD18uucENplI81kUErvMzdb5Lm.jpg"),
            )

        homeVerticalAdapter.submitList(
            listOf(
                FilmsFromGenre("Ação", listPosterMock),
                FilmsFromGenre("Aventura", listPosterMock),
                FilmsFromGenre("Suspense", listPosterMock),
                FilmsFromGenre("Drama", listPosterMock),
            )
        )
    }

    private fun initHomeVerticalAdapter() {
        with(binding.rvVertical) {
            setHasFixedSize(true)
            adapter = homeVerticalAdapter
        }
    }
}