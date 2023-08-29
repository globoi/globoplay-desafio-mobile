package com.mazer.globoplayapp.presentation.ui.details.tabs.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mazer.globoplayapp.R

/**
 * Fragmento que conterá a view da aba "Detalhes" dentro da MovieDetailsActivity
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    companion object {
        @JvmStatic
        fun newInstance() = DetailsFragment()
    }
}