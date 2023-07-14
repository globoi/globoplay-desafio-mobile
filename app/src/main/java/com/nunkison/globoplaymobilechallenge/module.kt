package com.nunkison.globoplaymobilechallenge

import com.nunkison.globoplaymobilechallenge.ui.movies.MoviesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel

import org.koin.dsl.module

val androidModule = module {
    viewModel { MoviesViewModel() }
}