package br.com.details_movie.domain.usecase

import br.com.details_movie.domain.model.Movie
import br.com.details_movie.domain.repository.MovieRepository
import br.com.network.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetMovieUseCase @Inject constructor(private val repository: MovieRepository) {

    suspend operator fun invoke(movieId: Int): Flow<Resource<Movie>> = repository.getMovie(movieId)
}
