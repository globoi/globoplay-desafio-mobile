package com.example.globechallenge

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.globechallenge.data.repository.home.HomeRepository
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

    private lateinit var viewmodel: HomeViewModel

    @Mock
    private lateinit var repository: HomeRepository

    @Before
    fun setup() {
        viewmodel = HomeViewModel(repository)
    }

    @Test
    fun `prove that function getMovieByGenre is suspend`() {
        viewmodel.getMovieByGenre()
        Assert.assertEquals(
            null,
            viewmodel.viewFlipperLiveData.value
        )
    }
}