package com.simonassi.globoplay.ui.highlights

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.simonassi.globoplay.R
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.fragment.app.viewModels
import androidx.navigation.navArgs
import com.simonassi.globoplay.data.Movie
import com.simonassi.globoplay.data.Tv
import com.simonassi.globoplay.utilities.contants.ItemType
import com.simonassi.globoplay.viewmodels.HomeViewModel

class HighlightsActivity : AppCompatActivity() {

    val args: HighlightsActivityArgs by navArgs()
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var movie: Movie
    private lateinit var tv: Tv

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_highlights)
        supportActionBar?.hide()

        if(args.type == ItemType.MOVIE){
            movie = homeViewModel.getMovieById(args.itemId)!!
        }else{
            tv = homeViewModel.getTvById(args.itemId)!!
        }

        setupListeners()
    }

    private fun setupListeners() {

    }

}