package com.ftoniolo.globoplay.presentation.home

import androidx.paging.PagingData
import com.ftoniolo.core.usecase.GetPopularFilmsUseCase
import com.ftoniolo.testing.MainCoroutineRule
import com.ftoniolo.testing.model.FilmsFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getPopularFilmsUseCaseMock: GetPopularFilmsUseCase

    private lateinit var homeViewModel: HomeViewModel

    private val filmsFactory = FilmsFactory()

    private val pagingDataFilms = PagingData.from(
        listOf(
            filmsFactory.create(FilmsFactory.Movie.Sonic),
            filmsFactory.create(FilmsFactory.Movie.Batman),
        )
    )

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(getPopularFilmsUseCaseMock)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `should validate the paging data object values when calling filmsPagingData`() {
        runBlockingTest {
            whenever(
                getPopularFilmsUseCaseMock.invoke(
                    any()
                )
            ).thenReturn(
                flowOf(
                    pagingDataFilms
                )
            )

            val result = homeViewModel.filmsPagingData()

            assertEquals(1, result.count())
        }
    }

    @ExperimentalCoroutinesApi
    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`(){
        runBlockingTest {
            whenever(getPopularFilmsUseCaseMock.invoke(any()))
                .thenThrow(RuntimeException())

            homeViewModel.filmsPagingData()
        }
    }
}