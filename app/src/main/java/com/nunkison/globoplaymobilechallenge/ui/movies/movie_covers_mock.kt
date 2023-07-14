package com.nunkison.globoplaymobilechallenge.ui.movies

val movieCoversMock = arrayListOf(
    MovieCover(
        id = "1",
        name = "Warrior",
        cover = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/ip0JTVXV4atOdx18ixlYPwESBQR.jpg"
    ),
    MovieCover(
        id = "2",
        name = "The Blacklist",
        cover = "https://www.themoviedb.org/t/p/w600_and_h900_bestv2/7WCNaek6zGlhum99TA63QmVPhox.jpg"
    ),
    MovieCover(
        id = "3",
        name = "The Rookie",
        cover = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/jbMefmo6EjcvpOULP50nfMpGP70.jpg"
    ),
    MovieCover(
        id = "4",
        name = "Woman in a Veil",
        cover = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/5ERr09UrnVm0hdXBeefNVtQMxI.jpg"
    ),
    MovieCover(
        id = "5",
        name = "Superman & Lois",
        cover = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/qJ6ndkbqgqS1n7ETi0YHhcjASym.jpg"
    ),
    MovieCover(
        id = "5",
        name = "Fast X",
        cover = "https://www.themoviedb.org/t/p/w300_and_h450_bestv2/fiVW06jE7z9YnO4trhaMEdclSiC.jpg"
    ),
)

val moviesScreenData = arrayListOf(
    MoviesGroup(
        category = "Novelas",
        movieCovers = movieCoversMock.shuffled()
    ),
    MoviesGroup(
        category = "Filmes",
        movieCovers = movieCoversMock.shuffled()
    ),
    MoviesGroup(
        category = "Series",
        movieCovers = movieCoversMock.shuffled()
    ),
    MoviesGroup(
        category = "Infantil",
        movieCovers = movieCoversMock.shuffled()
    ),
)
