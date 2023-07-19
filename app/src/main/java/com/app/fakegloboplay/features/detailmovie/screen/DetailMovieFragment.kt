package com.app.fakegloboplay.features.detailmovie.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.app.fakegloboplay.BuildConfig
import com.app.fakegloboplay.R
import com.app.fakegloboplay.databinding.FragmentDatailMovieBinding
import com.app.fakegloboplay.databinding.FragmentDetailDatasheetBinding
import com.app.fakegloboplay.features.commons.customview.ImageLoaderView
import com.app.fakegloboplay.network.response.Movie
import com.app.fakegloboplay.network.response.MyFavorite
import com.app.fakegloboplay.features.detailmovie.screen.adapters.DetailViewPageAdapter
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieFragment : Fragment() {

    private val viewModel: DetailWatchTooViewModel by viewModel()

    private var movie: Movie? = null

    private lateinit var detailViewPageAdapter: DetailViewPageAdapter

    private var _binding: FragmentDatailMovieBinding? = null
    private val binding: FragmentDatailMovieBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movie = arguments?.getParcelable("movie")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDatailMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setDataView(view)
        configViewPage()
        with(binding) {
            btnAddFavorite.setOnClickListener {
                viewModel.postMyFavorite(
                    MyFavorite(
                        mediaType = movie?.mediaType ?: "tv",
                        mediaId = movie?.id!!,
                        favorite = true
                    )
                )
            }
            bntNavBack.setOnClickListener {
                navPopBackStack()
            }
        }

    }

    private fun navPopBackStack() {
        this.findNavController().popBackStack()
    }

    private fun setDataView(view: View) {
        movie?.let {
            with(binding) {
                txtTitleOrName.text = it.name ?: it.title
                txtOverview.text = it.overview
                imgCapaMovie.loadImage(BuildConfig.URL_IMG + it.posterPath)
                imgBgCapa.loadImage(BuildConfig.URL_IMG + it.backdropPath)
                imgBgCapa.setScaleType(ImageView.ScaleType.CENTER_CROP)
            }
        }
    }

    private fun configViewPage() {
        println(movie?.id!!)
        detailViewPageAdapter = DetailViewPageAdapter(this, movie?.id!!, movie?.mediaType)
        detailViewPageAdapter.addFragment(DetailWatchTooFragment())
        detailViewPageAdapter.addFragment(DetailDatasheetFragment())
        with(binding) {
            vpDetailPage.adapter = detailViewPageAdapter
            TabLayoutMediator(tabDetailLayout, vpDetailPage) { tab, position ->
                when (position) {
                    TAB_ASSISTA_TABEM -> tab.text =
                        resources.getString(R.string.str_tab_assista_tambem)

                    TAB_DETALHES -> tab.text = resources.getString(R.string.str_tab_detalhes)
                }
            }.attach()
        }
    }

    companion object {
        private val TAB_ASSISTA_TABEM = 0
        private val TAB_DETALHES = 1
    }
}