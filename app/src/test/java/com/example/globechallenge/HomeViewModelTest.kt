package com.example.globechallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.globechallenge.data.repository.home.HomeRepositoryImplementation
import com.example.globechallenge.ui.home.viewmodels.HomeViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    private lateinit var homeViewModel: HomeViewModel

    @Mock
    private lateinit var repositoryImplementation: HomeRepositoryImplementation

    @Before
    fun setup() {
        homeViewModel = HomeViewModel(repositoryImplementation)
    }

    @Test
    fun `prove that function getMovieByGenre is suspend`() {
        homeViewModel.getMovieByGenre()
        Assert.assertEquals(
            null,
            homeViewModel.viewFlipperLiveData.value
        )
    }
}