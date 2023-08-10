package br.com.favorites.di

import br.com.common.util.Mapper
import br.com.local.model.favorite.FavoritiesMovieEntity
import br.com.common.data.dto.MoviesDto
import br.com.favorites.data.repository.datasource.FavoritiesLocalDataSource
import br.com.favorites.data.repository.datasource.FavoritiesRemoteDataSource
import br.com.favorites.data.repository.datasourceimpl.FavoritiesLocalDataSourceImpl
import br.com.favorites.data.repository.datasourceimpl.FavoritiesRemoteDataSourceImpl
import br.com.favorites.data.repository.FavoritesMoviesRepositoryImpl
import br.com.favorites.domain.mappers.FavoritesMoviesEntityToDomain
import br.com.favorites.domain.mappers.FavoritiesMoviesDtoToEntityMapper
import br.com.common.domain.model.Movie
import br.com.favorites.domain.repository.FavoritesMoviesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
interface FavoritesModule {

    @ViewModelScoped
    @Binds
    fun bindFavoritesRepository(
        repositoryImpl: br.com.favorites.data.repository.FavoritesMoviesRepositoryImpl
    ) : FavoritesMoviesRepository

    @ViewModelScoped
    @Binds
    fun bindFavoritesRemoteDataSource(
        impl: br.com.favorites.data.repository.datasourceimpl.FavoritiesRemoteDataSourceImpl
    ) : br.com.favorites.data.repository.datasource.FavoritiesRemoteDataSource

    @ViewModelScoped
    @Binds
    fun bindFavoritesLocalDataSource(
        impl: br.com.favorites.data.repository.datasourceimpl.FavoritiesLocalDataSourceImpl
    ) : br.com.favorites.data.repository.datasource.FavoritiesLocalDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalMapper(impl: br.com.favorites.domain.mappers.FavoritesMoviesEntityToDomain)
    : Mapper<FavoritiesMovieEntity, Movie>

    @ViewModelScoped
    @Binds
    fun bindRemoteToLOcalMapper(
        impl: br.com.favorites.domain.mappers.FavoritiesMoviesDtoToEntityMapper
    ) : Mapper<MoviesDto, FavoritiesMovieEntity>
}