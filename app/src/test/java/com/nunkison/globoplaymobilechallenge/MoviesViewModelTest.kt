package com.nunkison.globoplaymobilechallenge

import com.nunkison.globoplaymobilechallenge.project.structure.MoviesRepository
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel
import com.nunkison.globoplaymobilechallenge.ui.movies.MoviesViewModelImpl
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MoviesGroup
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test

class MoviesViewModelTest {

    private val moviesRepositoryMock: MoviesRepository = mockk(relaxed = true)
    private val moviesGroup: MoviesGroup = mockk(relaxed = true)

    @Test
    fun `when the first call to loadMovies occurs, it should call getDiscoverMovies from the Repository`() =
        runBlocking {
            coEvery { moviesRepositoryMock.getDiscoverMovies() } returns arrayListOf(moviesGroup)
            val moviesViewModel: MoviesViewModel = MoviesViewModelImpl(moviesRepositoryMock)
            moviesViewModel.loadMovies()
            coVerify { moviesRepositoryMock.getDiscoverMovies() }
        }
}
