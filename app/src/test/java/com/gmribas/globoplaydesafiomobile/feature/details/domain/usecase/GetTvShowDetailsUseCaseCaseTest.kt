package com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase

import androidx.paging.PagingData
import com.gmribas.globoplaydesafiomobile.MainCoroutineRule
import com.gmribas.globoplaydesafiomobile.core.domain.model.Movie
import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.repository.GetTvShowDetailsRepository
import com.gmribas.globoplaydesafiomobile.feature.home.domain.repository.DiscoverMoviesRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GetTvShowDetailsUseCaseCaseTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @JvmField
    @Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var useCase: GetTvShowDetailsUseCase

    private val repository: GetTvShowDetailsRepository = mockk()

    @Before
    fun setUp() {
        useCase = GetTvShowDetailsUseCase(repository)
    }

    @Test
    fun `When use case returns success`() {
        runTest {
            val id = 1
            val tvShowDetails: TvShowDetails = mockk()
            val flow = flow { emit(tvShowDetails) }

            // Given
            coEvery { repository.getTvShowDetails(id) } returns flow

            // When
            val response = useCase.process(GetTvShowDetailsUseCase.Request(id)).first()

            // Then
            assertEquals(tvShowDetails, response.details)
        }
    }

    @Test(expected = Exception::class)
    fun `When use case returns IllegalArgumentException expected`() {
        runTest {
            val id = 1
            // Given
            val responseData = IllegalArgumentException()
            coEvery { repository.getTvShowDetails(id) } throws responseData

            // When
            useCase.process(GetTvShowDetailsUseCase.Request(id)).first()

            // Then
            coVerify(exactly = 1) { repository.getTvShowDetails(id) }
        }
    }
}