package com.example.globechallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.globechallenge.data.model.models.home.MovieToGenre
import com.example.globechallenge.data.repository.home.HomeRepositoryImplementation
import com.example.globechallenge.ui.home.viewmodels.HomeViewModel
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class HomeViewModelWithTestCoroutineRuleTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var repositoryImplementation: HomeRepositoryImplementation

    @Mock
    private lateinit var movieByGenreStateObserver: Observer<List<MovieToGenre>>

    @Mock
    private lateinit var viewFlipperStateObserver: Observer<Pair<Int, Int?>>

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        homeViewModel = HomeViewModel(repositoryImplementation)
            .apply {
                movieByGenreMutableLiveData.observeForever(movieByGenreStateObserver)
                viewFlipperLiveData.observeForever(viewFlipperStateObserver)
            }
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should result data when getMovieByGenre returns success`() =
        testCoroutineRule.runBlockingTest {
            //Given
            val result = arrayListOf<MovieToGenre>()
            whenever(repositoryImplementation.getMovieByGenre()).thenReturn(result)

            //When
            homeViewModel.getMovieByGenre()

            //Then
            verify(movieByGenreStateObserver).onChanged(arrayListOf())
        }

    @ExperimentalCoroutinesApi
    @Test
    fun `should Pair with 1 and null when getMovieByGenre returns success`() {
        testCoroutineRule.runBlockingTest {
            //Given
            val result = arrayListOf<MovieToGenre>()
            whenever(repositoryImplementation.getMovieByGenre()).thenReturn(result)

            //When
            homeViewModel.getMovieByGenre()

            //Then
            verify(viewFlipperStateObserver).onChanged(Pair(1,null))
        }
    }
}