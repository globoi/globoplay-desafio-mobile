package com.gmribas.globoplaydesafiomobile.feature.home.domain.usecase

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.MainCoroutineRule
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.presentation.UiState
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverMoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DiscoverMoviesUseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var useCase: DiscoverMoviesUseCase

    private val repository: DiscoverMoviesRepository = mockk()

    @Before
    fun setUp() {
        useCase = DiscoverMoviesUseCase(repository)
    }

    @Test
    fun `When use case returns success`() {
        runBlocking {
            val movie: Movie = mockk()
            val data = PagingData.from(listOf(movie))
            val moviesFound: Flow<PagingData<Movie>> = flow { emit(data) }

            // Given
            coEvery { repository.discoverMovies() } returns moviesFound

            // When
            val response = useCase.process(DiscoverMoviesUseCase.Request(this)).collect()

            // Then
            assertEquals(data, response)
        }
    }

//    @Test(expected = Exception::class)
//    fun `When use case returns IllegalArgumentException expected`() {
//        runBlocking {
//            // Given
//            val responseData = IllegalArgumentException()
//            coEvery { repository.getUsers() } throws responseData
//
//            // When
//            useCase.process(GetUsersUseCase.Request).first()
//
//            // Then
//            coVerify(exactly = 1) { repository.getUsers() }
//        }
//    }
}