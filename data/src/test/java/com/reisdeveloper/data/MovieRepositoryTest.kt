package com.reisdeveloper.data

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class MovieRepositoryTest {

    private lateinit var repository: FakeMovieRepository

    @Before
    fun setup() {
        repository = FakeMovieRepository()
    }


    @Test
    fun get_now_playing_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getNowPlaying("pt-BR", 1)

        assertEquals(repository.movieListMock, result)
    }

    @Test
    fun get_now_playing_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getNowPlaying("pt-BR", 1)

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_popular_movies_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getPopularMovies("pt-BR", 1)

        assertEquals(repository.movieListMock, result)
    }

    @Test
    fun get_popular_movies_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getPopularMovies("pt-BR", 1)

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_top_rated_movies_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getTopRatedMovies("pt-BR", 1)

        assertEquals(repository.movieListMock, result)
    }

    @Test
    fun get_top_rated_movies_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getTopRatedMovies("pt-BR", 1)

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_upcoming_movies_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getUpcomingMovies("pt-BR", 1)

        assertEquals(repository.movieListMock, result)
    }

    @Test
    fun get_upcoming_movies_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getUpcomingMovies("pt-BR", 1)

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_my_lists_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getMyLists()

        assertEquals(repository.movieListMock, result)
    }

    @Test
    fun get_my_lists_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getMyLists()

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_favorite_movies_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getFavoriteMovies()

        assertEquals(repository.movieListMock, result)
    }

    @Test
    fun get_favorite_movies_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getFavoriteMovies()

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_similar_movies_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getSimilarMovies("3646135", "pt-BR")

        assertEquals(repository.movieListMock, result)
    }

    @Test
    fun get_similar_movies_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getSimilarMovies("3646135", "pt-BR")

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_movie_details_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getMovieDetails("pt-BR", "16546541")

        assertEquals(repository.movieDetails, result)
    }

    @Test
    fun get_movie_details_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getMovieDetails("pt-BR", "16546541")

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun favorite_movie_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.favoriteMovie(repository.favoriteMock)

        assertEquals(Unit, result)
    }

    @Test
    fun favorite_movie_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.favoriteMovie(repository.favoriteMock)

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

    @Test
    fun get_movie_videos_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.getMovieVideos("654654")

        assertEquals(repository.movieVideos, result)
    }

    @Test
    fun get_movie_videos_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.getMovieVideos("545465")

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }
    @Test
    fun search_movies_success() = runBlocking {
        repository.setReturnError(false)

        val result = repository.searchMovies("search")

        assertEquals(repository.movieListMock, result)
    }

    @Test
    fun search_movies_error(): Unit = runBlocking {
        repository.setReturnError(true)

        try {
            repository.searchMovies("search")

        } catch (e: Throwable) {
            assertThat(e.message, IsEqual("Test exception"))
        }
    }

}