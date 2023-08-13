package br.com.details_movie.di

import br.com.common.util.Mapper
import br.com.details_movie.data.remote.dto.MovieDetailsDto
import br.com.details_movie.data.repository.MovieRepositoryImpl
import br.com.details_movie.data.repository.datasource.MovieLocalDataSource
import br.com.details_movie.data.repository.datasource.MovieRemoteDataSource
import br.com.details_movie.data.repository.datasourceimpl.MovieLocalDataSourceImpl
import br.com.details_movie.data.repository.datasourceimpl.MovieRemoteDataSourceImpl
import br.com.details_movie.domain.mappers.MovieRemoteToEntityMapper
import br.com.details_movie.domain.mappers.MovieToDomainMapper
import br.com.details_movie.domain.model.MovieDetails
import br.com.details_movie.domain.repository.MovieRepository
import br.com.local.model.movie_details.MovieDetailsEntity
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
interface MovieModule {

    @ViewModelScoped
    @Binds
    fun bindMovieRepository(repository: MovieRepositoryImpl): MovieRepository

    @ViewModelScoped
    @Binds
    fun bindRemoteDataSource(impl: MovieRemoteDataSourceImpl): MovieRemoteDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalDataSource(impl: MovieLocalDataSourceImpl): MovieLocalDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalMapper(impl: MovieToDomainMapper): Mapper<MovieDetailsEntity, MovieDetails>

    @ViewModelScoped
    @Binds
    fun bindRemoteToLocalMapper(impl: MovieRemoteToEntityMapper): Mapper<MovieDetailsDto, MovieDetailsEntity>
}