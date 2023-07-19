package com.app.fakegloboplay.features.mylist.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
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
import com.app.fakegloboplay.databinding.FragmentMyListBinding
import com.app.fakegloboplay.features.commons.BaseFragment
import com.app.fakegloboplay.network.response.Movie
import com.app.fakegloboplay.features.commons.ListMovieViewState
import com.app.fakegloboplay.features.mylist.screen.adapter.MyFavoriteAdapter
import com.app.fakegloboplay.features.mylist.screen.adapter.OnItemClickListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class MyListFragment : BaseFragment() {

    private val viewModel: MyListViewModel by viewModel()

    private val myFavoriteAdapter = MyFavoriteAdapter()

    private var _binding: FragmentMyListBinding? = null
    private val binding: FragmentMyListBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMyListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        showLoading()

        binding.rvMyFavorite.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = myFavoriteAdapter
        }

        myFavoriteAdapter.setOnItemClickListener(object : OnItemClickListener {
            override fun onItemClick(item: Movie, position: Int) {
                navDetailMovie(item)
            }
        })

        with(viewModel) {
            myFavoriteState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is ListMovieViewState.Success -> {
                        hideLoading()
                        myFavoriteAdapter.submitList(state.listMovie)
                    }

                    ListMovieViewState.Empty -> navErrorView()
                    ListMovieViewState.Error -> navErrorView()
                }
            }
            getMyFavorite()
        }
    }

    override fun showLoading() {
        binding.myListFavoriteProgressBar.isVisible = true
    }

    override fun hideLoading() {
        binding.myListFavoriteProgressBar.isVisible = false
    }

    private fun navDetailMovie(item: Movie) {
        val bundle = Bundle()
        bundle.putParcelable("movie", item)
        this.findNavController().navigate(R.id.detailMovieFragment, bundle)
    }

    private fun navErrorView() {
        this.findNavController().navigate(R.id.errorFragment)
    }
}