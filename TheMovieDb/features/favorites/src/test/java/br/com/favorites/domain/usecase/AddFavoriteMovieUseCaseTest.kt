package br.com.favorites.domain.usecase

import br.com.favorites.domain.model.AddOrRemoveFavorite
import br.com.favorites.domain.model.ResultAddFavorite
import br.com.favorites.domain.repository.FavoritesMoviesRepository
import br.com.network.Resource
import com.nhaarman.mockitokotlin2.whenever
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

import org.mockito.Mockito.*

@RunWith(MockitoJUnitRunner::class)
class AddFavoriteMovieUseCaseTest {

    @Mock
    private lateinit var repository: FavoritesMoviesRepository

    private lateinit var useCase: AddFavoriteMovieUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        useCase = AddFavoriteMovieUseCase(repository)
    }

    fun `test adding favorite movie success`() = runBlocking {
    }

}