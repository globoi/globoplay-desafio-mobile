package com.simonassi.globoplay.utilities

import com.simonassi.globoplay.data.movie.Movie
import com.simonassi.globoplay.data.tv.Tv

class Generator {

    companion object{
        fun generateInitialMovies(): MutableList<Movie> {
            val list: MutableList<Movie> = mutableListOf()        // or, use `arrayListOf`
            for (i: Int in 0..5) {
                list.add(Movie(-1,"", "", "", "", listOf(), "", ""))
            }
            return list
        }

        fun generateInitialTv(): MutableList<Tv> {
            val list: MutableList<Tv> = mutableListOf()        // or, use `arrayListOf`
            for (i: Int in 0..5) {
                list.add(Tv(-1,"", "", "", listOf(), "", ""))
            }
            return list
        }
    }

}