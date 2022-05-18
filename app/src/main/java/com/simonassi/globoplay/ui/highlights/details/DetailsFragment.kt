package com.simonassi.globoplay.ui.highlights.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.simonassi.globoplay.data.Genre
import com.simonassi.globoplay.databinding.FragmentDetailsBinding
import com.simonassi.globoplay.viewmodels.HighlightsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val highlighthsViewModel: HighlightsViewModel by activityViewModels()
    lateinit var binding: FragmentDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(inflater, container, false)
        context ?: return binding.root

        subscribeUi()
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun subscribeUi() {
        highlighthsViewModel.movieSearchLiveData.observe(viewLifecycleOwner, Observer { movie ->
            updateDetails(Details(movie.title, movie.currentGenres, movie.releaseDate.split("-")[0], ""))
        })

        highlighthsViewModel.tvSearchLiveData.observe(viewLifecycleOwner, Observer { tv ->
            updateDetails(Details(tv.title, tv.currentGenres, tv.releaseDate.split("-")[0], ""))
        })
    }

    fun updateDetails(details: Details){
        binding.originalTitleValue.text = details.title
        var genderText = ""
        details.genre.forEach { gender ->
            genderText += if(details.genre.last() == gender){
                gender.name + "."
            }else{
                gender.name + ", "
            }
        }
        binding.genderValue.text = genderText
        binding.productionYearValue.text = details.productionYear
        binding.directionValue.text = details.direction
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }

     class Details(
         val title: String,
         val genre: List<Genre>,
         val productionYear: String,
         val direction: String,
    )
}