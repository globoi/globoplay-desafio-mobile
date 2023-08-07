package br.com.movies.di

import br.com.common.util.Mapper
import br.com.local.model.TrendingMovieEntity
import br.com.movies.data.remote.dto.TrendingMoviesDto
import br.com.movies.data.repository.TrendingMoviesRepositoryImpl
import br.com.movies.data.repository.datasource.LocalDataSource
import br.com.movies.data.repository.datasource.RemoteDataSource
import br.com.movies.data.repository.datasource_impl.LocalDataSourceImpl
import br.com.movies.data.repository.datasource_impl.RemoteDataSourceImpl
import br.com.movies.domain.mappers.TrendingMoviesDtoToEntityMapper
import br.com.movies.domain.mappers.TrendingMoviesEntityToDomain
import br.com.movies.domain.model.TrendingMovies
import br.com.movies.domain.repository.TrendingMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface TrendingModule {

    @ViewModelScoped
    @Binds
    fun bindTrendingRepository(repository: TrendingMoviesRepositoryImpl): TrendingMoviesRepository

    @ViewModelScoped
    @Binds
    fun bindRemoteDataSource(impl: RemoteDataSourceImpl): RemoteDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalDataSource(impl: LocalDataSourceImpl): LocalDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalMapper(impl: TrendingMoviesEntityToDomain): Mapper<TrendingMovieEntity, TrendingMovies>

    @ViewModelScoped
    @Binds
    fun bindRemoteToLocalMapper(
        impl: TrendingMoviesDtoToEntityMapper,
    ): Mapper<TrendingMoviesDto, TrendingMovieEntity>

}