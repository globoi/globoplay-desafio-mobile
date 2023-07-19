package com.app.fakegloboplay.features.detailmovie.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.app.fakegloboplay.R
import com.app.fakegloboplay.databinding.FragmentDetailDatasheetBinding
import com.app.fakegloboplay.databinding.FragmentWatchTooBinding
import com.app.fakegloboplay.network.response.Movie
import com.app.fakegloboplay.features.commons.ListMovieViewState
import com.app.fakegloboplay.features.detailmovie.screen.adapters.DetailWatchTooAdapter
import com.app.fakegloboplay.features.mylist.screen.adapter.OnItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailWatchTooFragment : DetailFragmentViewPage() {

    private val viewModel: DetailWatchTooViewModel by viewModel()

    private val detailWatchTooAdapter = DetailWatchTooAdapter()

    private var _binding: FragmentWatchTooBinding? = null
    private val binding: FragmentWatchTooBinding get() = _binding!!

    override fun setParamMediaType(value: String?) {
        mediaType = value
    }

    override fun setParamSeriesId(value: Int) {
        seriesId = value
    }

    override fun showLoading() {
        with(binding) {
            loadingWatchToo.isVisible = true
            errorContentWatchToo.isVisible = false
            rvWatchToo.isVisible = false
        }
    }

    override fun hideLoading() {
        with(binding) {
            loadingWatchToo.isVisible = false
            rvWatchToo.isVisible = true
        }
    }

    override fun showMessageError() {
        with(binding) {
            loadingWatchToo.isVisible = false
            errorContentWatchToo.isVisible = true
            rvWatchToo.isVisible = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentWatchTooBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()

        with(binding) {
            rvWatchToo.apply {
                layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
                adapter = detailWatchTooAdapter
            }

            btnRetryWatchToo.setOnClickListener {
                retryGetRecommendations()
            }
        }

        with(viewModel) {
            detailWatchTooState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ListMovieViewState.Success -> {
                        hideLoading()
                        detailWatchTooAdapter.submitList(state.listMovie)
                    }

                    ListMovieViewState.Empty -> showMessageError()
                    ListMovieViewState.Error -> showMessageError()
                }
            }
            getRecommendations(seriesId, 1, mediaType)
        }

        detailWatchTooAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: Movie, position: Int) {
                navDetailMovie(item)
            }
        })


    }

    private fun navDetailMovie(item: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movie", item)
        this.findNavController().navigate(R.id.detailMovieFragment, bundle)
    }

    private fun retryGetRecommendations() {
        showLoading()
        viewModel.getRecommendations(seriesId, 1, mediaType)
    }
}