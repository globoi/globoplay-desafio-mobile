package com.mazer.globoplayapp.presentation.ui.details.tabs.recommendation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mazer.globoplayapp.R

/**
 * Este Fragment exibirá uma lista de filmes em 3 colunas
 * será usado no "Assista Também" presente no MovieDetailsActivity
 */
class RecommendationFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommendation_list, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecommendationFragment()
    }
}