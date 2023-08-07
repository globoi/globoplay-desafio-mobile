package com.reisdeveloper.domain.usecase

import app.cash.turbine.test
import com.reisdeveloper.domain.Error
import com.reisdeveloper.domain.FakeMovieRepository
import com.reisdeveloper.domain.MainCoroutineRule
import com.reisdeveloper.domain.ResultWrapper
import com.reisdeveloper.domain.usecases.FavoriteMovieUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class FavoriteMovieUseCaseTest {

    private lateinit var repository: FakeMovieRepository

    private lateinit var getUserByNameUseCase: FavoriteMovieUseCase

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setup() {
        repository = FakeMovieRepository()
        getUserByNameUseCase = FavoriteMovieUseCase(repository)
    }


    @Test
    fun favorite_movie_success() = runBlocking {
        repository.setReturnError(false)

        val result = getUserByNameUseCase.invoke(repository.favoriteMock)

        result.test {
            assertEquals(ResultWrapper.Loading, awaitItem())
            assertEquals(
                ResultWrapper.Success(Unit),
                awaitItem()
            )
            assertEquals(ResultWrapper.DismissLoading, awaitItem())
            awaitComplete()
        }
    }

    @Test
    fun favorite_movie_error() = runBlocking {
        repository.setReturnError(true)

        val result = getUserByNameUseCase.invoke(repository.favoriteMock)

        result.test {
            try {
                assertEquals(ResultWrapper.Loading, awaitItem())
                assertEquals(
                    ResultWrapper.Failure(Error.UnknownException(Throwable("Test exception1"))),
                    awaitItem()
                )
                cancelAndConsumeRemainingEvents()
                awaitComplete()

            } catch (e: Throwable) {
                assertEquals(ResultWrapper.DismissLoading, awaitItem())
                awaitComplete()
            }
        }
    }
}