package com.ftoniolo.globoplay.framework.paging

import androidx.paging.PagingSource
import com.ftoniolo.core.data.repository.FilmsRemoteDataSource
import com.ftoniolo.core.domain.model.WatchToo
import com.ftoniolo.factory.response.WatchTooPagingFactory
import com.ftoniolo.testing.MainCoroutineRule
import com.ftoniolo.testing.model.WatchTooFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class WatchTooPagingSourceTest {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var watchTooPagingSource: WatchTooPagingSource

    @Mock
    lateinit var remoteDataSource: FilmsRemoteDataSource

    private val filmPagingFactory = WatchTooPagingFactory()

    private val watchTooFactory = WatchTooFactory()

    @Before
    fun setup() {
        watchTooPagingSource = WatchTooPagingSource(remoteDataSource, any())
    }

    @Test
    fun `should return a success load result when load is called`() = runTest {

        whenever(remoteDataSource.fetchWatchToo(1L, any()))
            .thenReturn(filmPagingFactory.create())

        val result = watchTooPagingSource.load(
            PagingSource.LoadParams.Refresh(
                null,
                loadSize = 2,
                false
            )
        )

        val expected = listOf(
            watchTooFactory.create(WatchTooFactory.Movie.HomemAranha),
            watchTooFactory.create(WatchTooFactory.Movie.DrEstranho)
        )

        Assert.assertEquals(
            PagingSource.LoadResult.Page(
                data = expected,
                prevKey = null,
                nextKey = null
            ),
            result
        )
    }

    @Test
    fun `should return a error load result when load is called`() = runTest {

        val exception = RuntimeException()
        whenever(remoteDataSource.fetchWatchToo(1L, any())).thenThrow(exception)


        val result = watchTooPagingSource.load(
            PagingSource.LoadParams.Refresh(
                key = null,
                loadSize = 2,
                placeholdersEnabled = false
            )
        )
        Assert.assertEquals(
            PagingSource.LoadResult.Error<Int, WatchToo>(exception),
            result
        )
    }
}