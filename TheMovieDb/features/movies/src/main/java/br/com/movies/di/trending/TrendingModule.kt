package br.com.movies.di.trending

import br.com.common.util.Mapper
import br.com.local.model.trending.TrendingMovieEntity
import br.com.common.data.dto.MoviesDto
import br.com.movies.data.repository.trending.TrendingMoviesRepositoryImpl
import br.com.movies.data.repository.datasource.trending.TrendingLocalDataSource
import br.com.movies.data.repository.datasource.trending.TrendingRemoteDataSource
import br.com.movies.data.repository.datasource_impl.trending.TrendingLocalDataSourceImpl
import br.com.movies.data.repository.datasource_impl.trending.TrendingRemoteDataSourceImpl
import br.com.movies.domain.mappers.trending.TrendingMoviesDtoToEntityMapper
import br.com.movies.domain.mappers.trending.TrendingMoviesEntityToDomain
import br.com.common.domain.model.Movie
import br.com.movies.domain.repository.trending.TrendingMoviesRepository
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
    fun bindTrendingRepository(
        repository: TrendingMoviesRepositoryImpl):
            TrendingMoviesRepository

    @ViewModelScoped
    @Binds
    fun bindRemoteDataSource(
        impl: TrendingRemoteDataSourceImpl)
    : TrendingRemoteDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalDataSource(
        impl: TrendingLocalDataSourceImpl): TrendingLocalDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalMapper(impl: TrendingMoviesEntityToDomain)
    : Mapper<TrendingMovieEntity, Movie>

    @ViewModelScoped
    @Binds
    fun bindRemoteToLocalMapper(
        impl: TrendingMoviesDtoToEntityMapper,
    ): Mapper<MoviesDto, TrendingMovieEntity>

}