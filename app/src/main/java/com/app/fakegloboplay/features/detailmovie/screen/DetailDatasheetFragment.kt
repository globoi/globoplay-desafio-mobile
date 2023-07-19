package com.app.fakegloboplay.features.detailmovie.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import com.app.fakegloboplay.R
import com.app.fakegloboplay.databinding.FragmentDetailDatasheetBinding
import com.app.fakegloboplay.network.response.Cast
import com.app.fakegloboplay.network.response.DetailsMove
import com.app.fakegloboplay.network.response.Genres
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailDatasheetFragment : DetailFragmentViewPage() {

    private val viewModel: DetailDatasheetViewModel by viewModel()

    private var _binding: FragmentDetailDatasheetBinding? = null
    private val binding: FragmentDetailDatasheetBinding get() = _binding!!

    override fun setParamMediaType(value: String?) {
        mediaType = value
    }

    override fun setParamSeriesId(value: Int) {
        seriesId = value
    }

    override fun showLoading() {
        with(binding) {
            errorContentDatasheet.isVisible = false
            loadingDatasheet.isVisible = true
            contentDatasheet.isVisible = false
        }
    }

    override fun hideLoading() {
        with(binding) {
            loadingDatasheet.isVisible = false
            contentDatasheet.isVisible = true
        }
    }

    override fun showMessageError() {
        with(binding) {
            loadingDatasheet.isVisible = false
            errorContentDatasheet.isVisible = true
            contentDatasheet.isVisible = false
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailDatasheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        showLoading()
        with(viewModel) {
            detailsTv.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is DetailDatasheetStateView.Success -> setDataView(state.detailsMove)
                    DetailDatasheetStateView.Error -> showMessageError()

                }
            }
            getDetailsTv(seriesId, mediaType)
        }

        binding.btnRetryDatasheet.setOnClickListener {
            retryGetDetailsTv()
        }
    }

    private fun retryGetDetailsTv() {
        showLoading()
        viewModel.getDetailsTv(seriesId, mediaType)
    }

    private fun formatString(str: Int, value: String?): String =
        String.format(
            resources.getString(str),
            value
        )

    private fun getItemListGenres(array: ArrayList<Genres>): String {
        val list = arrayListOf<String?>()
        array.forEach {
            list.add(it.name)
        }
        return list.toString()
            .replace("[", "")
            .replace("]", "")
    }

    private fun getItemListCast(array: ArrayList<Cast>?): String {
        val list = arrayListOf<String?>()
        array?.forEach {
            list.add(it.name)
        }
        return list.toString().replace("[", "").replace("]", "")
    }

    private fun formatDateBr(value: String?): String {
        val d = value?.split("-")
        return "${d?.get(2)}/${d?.get(1)}/${d?.get(0)}"
    }

    private fun setDataView(movie: DetailsMove) {
        with(binding) {
            txtOriginalName.text =
                formatString(R.string.str_original_name, movie.originalName ?: movie.originalTitle)
            txtGenres.text =
                formatString(R.string.str_genre, getItemListGenres(movie.genres))
            txtEpisodeCount.text =
                formatString(R.string.str_episode,
                    if (movie.numberOfEpisodes == null) {
                        resources.getString(R.string.str_not_found)
                    } else {
                        movie.numberOfEpisodes.toString()
                    })
            txtFirstAirDate.text =
                formatString(
                    R.string.str_air_date,
                    movie.firstAirDate?.substring(0, 4) ?: movie.releaseDate?.substring(0, 4)
                )

            if (movie.originCountry.size > 0) {
                txtOriginCountry.text =
                    formatString(R.string.str_origin_country, movie.originCountry[0])
            } else {
                txtOriginCountry.text =
                    formatString(
                        R.string.str_origin_country,
                        resources.getString(R.string.str_not_found)
                    )
            }
            movie.credits?.crew?.let {
                if (it.isNotEmpty()) {
                    txtDirecting.text =
                        formatString(R.string.str_directing, it[0].name)
                } else {
                    txtDirecting.text =
                        formatString(
                            R.string.str_directing,
                            resources.getString(R.string.str_not_found)
                        )
                }
            }
            txtCast.text = formatString(R.string.str_cast, getItemListCast(movie.credits?.cast))
            txtLastAirDate.text =
                formatString(
                    R.string.str_last_air_date,
                    if (movie.lastAirDate.isNullOrEmpty()) {
                        resources.getString(R.string.str_not_found)
                    } else {
                        formatDateBr(movie.lastAirDate)
                    }
                )
        }
        hideLoading()
    }

}