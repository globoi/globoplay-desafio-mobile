package br.com.nerdrapido.themoviedbapp.domain.usecase

import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieRequest
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.repository.movies.MoviesRepository

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class GetMovieUseCase(private val moviesRepository: MoviesRepository) {

    suspend fun getMovieById(id: Int): MovieResponse {
        val movieRequest = MovieRequest(id, "pt-BR")
        return moviesRepository.getMovie(movieRequest)
    }

}