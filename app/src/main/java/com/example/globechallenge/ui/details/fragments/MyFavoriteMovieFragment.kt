package com.example.globechallenge.ui.details.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.globechallenge.R

class MyFavoriteMovieFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_my_favorite_movie, container, false)
    }

    companion object {
        const val TITLE_MY_FAVORITE = "Assista Também"
        fun newInstance(param1: String, param2: String) =
            MyFavoriteMovieFragment().apply {
                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
                }
            }
    }
}