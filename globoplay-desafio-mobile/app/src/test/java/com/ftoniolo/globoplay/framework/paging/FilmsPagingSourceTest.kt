package com.ftoniolo.globoplay.framework.paging

import androidx.paging.PagingSource
import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.factory.response.FilmsDataWrapperResponseFactory
import com.ftoniolo.testing.MainCoroutineRule
import com.ftoniolo.testing.model.FilmsFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import java.lang.RuntimeException

@RunWith(MockitoJUnitRunner::class)
class FilmsPagingSourceTest {

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var filmsPagingSource: FilmsPagingSource

    @Mock
    lateinit var remoteDataSource: FilmsRemoteDataSource

    private val filmsDataWrapperResponseFactory = FilmsDataWrapperResponseFactory()

    private val filmsFactory = FilmsFactory()

    @Before
    fun setup() {
        filmsPagingSource = FilmsPagingSource(remoteDataSource)
    }

    @Test
    fun `should return a success load result when load is called`() = runBlocking {

        whenever(remoteDataSource.fetchFilms(any()))
            .thenReturn(filmsDataWrapperResponseFactory.create())

        val result = filmsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 2,
                false
            )
        )

        val expected = listOf(
            filmsFactory.create(FilmsFactory.Movie.HomemAranha),
            filmsFactory.create(FilmsFactory.Movie.DrEstranho)
        )

        assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = null
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runBlocking {

        val exception = RuntimeException()
        whenever(remoteDataSource.fetchFilms(any())).thenThrow(exception)


        val result = filmsPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )

        assertEquals(
            PagingSource.LoadResult.Error<Int, Film>(exception),
            result
        )
    }
}