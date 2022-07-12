package com.nroncari.movieplay.presentation.viewmodel

import androidx.annotation.VisibleForTesting
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nroncari.movieplay.domain.usecase.*
import com.nroncari.movieplay.presentation.model.MovieDataVideoPresentation
import com.nroncari.movieplay.presentation.model.MovieDetailPresentation
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

@ExperimentalCoroutinesApi
class MovieDetailViewModelTest {

    @get:Rule
    var instantExecutionRule = InstantTaskExecutorRule()

    private val movieDetailUseCase: GetMovieDetailUseCase =
        Mockito.mock(GetMovieDetailUseCase::class.java)
    private val getMovieDataVideoUseCase: GetMovieDataVideoUseCase =
        Mockito.mock(GetMovieDataVideoUseCase::class.java)
    private val getMovieDatabaseById: GetMovieDatabaseById =
        Mockito.mock(GetMovieDatabaseById::class.java)
    private val recommendationsUseCase: GetMovieRecommendationsUseCase =
        Mockito.mock(GetMovieRecommendationsUseCase::class.java)
    private val insertMovieDBUseCase: InsertMovieDatabaseUseCase =
        Mockito.mock(InsertMovieDatabaseUseCase::class.java)
    private val removeMovieDBUseCase: RemoveMovieDatabaseUseCase =
        Mockito.mock(RemoveMovieDatabaseUseCase::class.java)

    private val viewModel by lazy {
        MovieDetailViewModel(
            movieDetailUseCase,
            getMovieDataVideoUseCase,
            getMovieDatabaseById,
            recommendationsUseCase,
            insertMovieDBUseCase,
            removeMovieDBUseCase
        )
    }

    private val moviePresentation =
        MovieDetailPresentation(
            42,
            "originalTitle",
            "overview",
            "posterPath",
            "releaseDate",
            "title",
            "backdropPath",
            4.2.toFloat()
        )

    private val movieDataVideoPresentation = MovieDataVideoPresentation("//", "YouTube")

    @Test
    fun `When get movie detail from viewModel should return success`() = runBlockingTest {
        // Given
        whenever(movieDetailUseCase(42)).thenReturn(moviePresentation)

        // When
        viewModel.getMovieDetail(42)

        val result = viewModel.movie.getOrAwaitValue()

        verify(movieDetailUseCase).invoke(42)
        assertEquals(42, result.id)
        assertTrue(result is MovieDetailPresentation)
    }

    @Test
    fun `When get movie data video from viewModel should return success`() = runBlockingTest {
        // Given
        whenever(getMovieDataVideoUseCase(42)).thenReturn(movieDataVideoPresentation)

        // When
        viewModel.getMovieDataVideo(42)

        val result = viewModel.dataMovieVideo.getOrAwaitValue()

        verify(getMovieDataVideoUseCase).invoke(42)
        assertEquals("YouTube", result!!.site)
        assertTrue(result is MovieDataVideoPresentation)
    }
}

@VisibleForTesting(otherwise = VisibleForTesting.NONE)
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS,
    afterObserver: () -> Unit = {}
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(t: T) {
            data = t
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }
    this.observeForever(observer)

    try {
        afterObserver.invoke()

        if (!latch.await(time, timeUnit)) {
            throw TimeoutException("LiveData value was never set.")
        }

    } finally {
        this.removeObserver(observer)
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}