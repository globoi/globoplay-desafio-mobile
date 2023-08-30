package com.mazer.globoplayapp.presentation.ui.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mazer.globoplayapp.R
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel : MainActivityViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.loadPopularMovies()
    }
}