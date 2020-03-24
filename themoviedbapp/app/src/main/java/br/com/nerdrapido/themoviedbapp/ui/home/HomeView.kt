package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationView

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
interface HomeView : NavigationView {

    suspend fun loadedDiscoverPage(movieListResultObjectList: List<MovieListResultObject>)

    suspend fun loadedActionPage(movieListResultObjectList: List<MovieListResultObject>)

    suspend fun loadedComedyPage(movieListResultObjectList: List<MovieListResultObject>)

    suspend fun loadedScienceFictionPage(movieListResultObjectList: List<MovieListResultObject>)
}