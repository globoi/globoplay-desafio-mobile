package com.ftoniolo.globoplay.presentation.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.ftoniolo.core.domain.model.HomeData
import com.ftoniolo.core.usecase.GetFilmsByCategoryUseCase
import com.ftoniolo.core.usecase.base.ResultStatus
import com.ftoniolo.globoplay.R
import com.ftoniolo.testing.MainCoroutineRule
import com.ftoniolo.testing.model.FilmFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.isA
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    private lateinit var getFilmsByCategoryUseCase: GetFilmsByCategoryUseCase

    @Mock
    private lateinit var uiStateObserver: Observer<HomeUiActionStateLiveData.UiState>

    private val films = listOf(FilmFactory().create(FilmFactory.FakeFilm.FakeFilm1))

    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        homeViewModel = HomeViewModel(
            getFilmsByCategoryUseCase,
            mainCoroutineRule.testDispatcherProvider
        ).apply {
            categories.state.observeForever(uiStateObserver)

        }
    }

    @Test
    fun `should notify uiState with Success from UiState when get films categories returns success`() =
        runTest {
            // Arrange
            whenever(getFilmsByCategoryUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            HomeData(
                                films, films,
                                films, films,
                                films, films,
                                films, films,
                            )
                        )
                    )
                )

            // Act
            homeViewModel.categories.load()

            // Assert
            verify(uiStateObserver).onChanged(isA<HomeUiActionStateLiveData.UiState.Success>())

            val uiStateSuccess = homeViewModel.categories.state.value as HomeUiActionStateLiveData.UiState.Success
            val categoriesParentList = uiStateSuccess.homeParentList

            assertEquals(8, categoriesParentList.size)
            assertEquals(
                R.string.popular_film,
                categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Success from UiState when get film categories returns only film`() =
        runTest {
            // Arrange
            whenever(getFilmsByCategoryUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Success(
                            HomeData(
                                films, listOf(),
                                listOf(), listOf(),
                                listOf(), listOf(),
                                listOf(), listOf(),
                            )
                        )
                    )
                )

            // Act
            homeViewModel.categories.load()

            // Assert
            verify(uiStateObserver).onChanged(isA<HomeUiActionStateLiveData.UiState.Success>())

            val uiStateSuccess = homeViewModel.categories.state.value as HomeUiActionStateLiveData.UiState.Success
            val categoriesParentList = uiStateSuccess.homeParentList

            assertEquals(1, categoriesParentList.size)
            assertEquals(
                R.string.popular_film,
                categoriesParentList[0].categoryStringResId
            )
        }

    @Test
    fun `should notify uiState with Error from UiState when get character categories returns an exception`() =
        runTest {
            // Arrange
            whenever(getFilmsByCategoryUseCase.invoke(any()))
                .thenReturn(
                    flowOf(
                        ResultStatus.Error(Throwable())
                    )
                )

            // Act
            homeViewModel.categories.load()

            // Assert
            verify(uiStateObserver).onChanged(isA<HomeUiActionStateLiveData.UiState.Error>())
        }
}