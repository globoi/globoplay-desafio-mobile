package com.reisdeveloper.domain.usecases

import com.reisdeveloper.data.dataModel.MovieList
import com.reisdeveloper.data.repository.MovieRepository
import com.reisdeveloper.domain.MovieListType

class GetMovieListUseCase(
    private val userRepository: MovieRepository
) : AbstractUseCase<MovieListType, MovieList>() {

    override suspend fun execute(param: MovieListType): MovieList {
        return when(param) {
            MovieListType.NOW_PLAYING -> userRepository.getNowPlaying("pt-BR", page = 1)
            MovieListType.POPULAR -> userRepository.getPopularMovies("pt-BR", page = 1)
            MovieListType.TOP_RATED -> userRepository.getTopRatedMovies("pt-BR", page = 1)
            MovieListType.UPCOMING -> userRepository.getUpcomingMovies("pt-BR", page = 1)
        }
    }
}