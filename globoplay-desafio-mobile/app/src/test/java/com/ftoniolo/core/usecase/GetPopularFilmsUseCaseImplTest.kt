package com.ftoniolo.core.usecase

import androidx.paging.PagingConfig
import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.testing.MainCoroutineRule
import com.ftoniolo.testing.model.FilmsFactory
import com.ftoniolo.testing.pagingsource.PagingSourceFactory
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetPopularFilmsUseCaseImplTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Mock
    lateinit var repository: FilmsRepository

    private lateinit var getPopularFilmsUseCase: GetPopularFilmsUseCase

    private val movie = FilmsFactory().create(FilmsFactory.Movie.Batman)

    private val fakePagingSource = PagingSourceFactory().create(listOf(movie))

    @Before
    fun setup(){
        getPopularFilmsUseCase = GetPopularFilmsUseCaseImpl(repository)
    }

    @Test
    fun `should validate flow paging data creation when invoke from use case is called`() =
        runBlocking {
            whenever(repository.getPopularFilms())
                .thenReturn(
                    fakePagingSource
                )
        val result = getPopularFilmsUseCase
            .invoke(GetPopularFilmsUseCase.GetFilmsParams(PagingConfig(20)))

            verify(repository).getPopularFilms()

            assertNotNull(result.first())
    }
}