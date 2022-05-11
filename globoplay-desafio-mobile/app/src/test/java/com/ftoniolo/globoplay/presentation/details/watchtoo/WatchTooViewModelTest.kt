package com.ftoniolo.globoplay.presentation.details.watchtoo

import androidx.paging.PagingData
import com.ftoniolo.core.usecase.GetWatchTooUseCase
import com.ftoniolo.testing.MainCoroutineRule
import com.ftoniolo.testing.model.WatchTooFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.count
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
class WatchTooViewModelTest{

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var getWatchTooUseCaseMock: GetWatchTooUseCase

    private lateinit var watchTooViewModel: WatchTooViewModel

    private val watchTooFactory = WatchTooFactory()

    private val pagingDataFilms = PagingData.from(
        listOf(
            watchTooFactory.create(WatchTooFactory.Movie.Sonic),
            watchTooFactory.create(WatchTooFactory.Movie.Batman),
        )
    )

    @Before
    fun setUp() {
        watchTooViewModel = WatchTooViewModel(getWatchTooUseCaseMock, mainCoroutineRule.testDispatcherProvider)
    }

    @Test
    fun `should validate the paging data object values when calling watchTooPagingData`() {
        runTest {
            whenever(
                getWatchTooUseCaseMock.invoke(
                    any()
                )
            ).thenReturn(
                flowOf(
                    pagingDataFilms
                )
            )

            val result = watchTooViewModel.watchTooPagingData(1L)

            assertEquals(1, result.count())
        }
    }

    @Test(expected = RuntimeException::class)
    fun `should throw an exception when the calling to the use case returns an exception`(){
        runTest {
            whenever(getWatchTooUseCaseMock.invoke(any()))
                .thenThrow(RuntimeException())

            watchTooViewModel.watchTooPagingData(1L)
        }
    }
}