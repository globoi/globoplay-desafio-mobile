package com.ftoniolo.core.usecase

import androidx.paging.PagingConfig
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.testing.MainCoroutineRule
import com.ftoniolo.testing.model.WatchTooFactory
import com.ftoniolo.testing.pagingsource.PagingSourceFactory
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetWatchTooUseCaseImplTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: FilmsRepository

    private lateinit var getWatchTooUseCase: GetWatchTooUseCase

    private val movie = WatchTooFactory().create(WatchTooFactory.Movie.Batman)

    private val fakePagingSource = PagingSourceFactory().create(listOf(movie))

    @Before
    fun setup(){
        getWatchTooUseCase = GetWatchTooUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runTest {
            whenever(repository.getWatchToo(1L))
                .thenReturn(
                    fakePagingSource
                )
        val result = getWatchTooUseCase
            .invoke(GetWatchTooUseCase.GetWatchTooParams(1L, PagingConfig(20)))

            assertNotNull(result.first())
    }
}
