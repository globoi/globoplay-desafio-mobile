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
import br.com.favorites.data.remote.dto.AddOrRemoveFavoriteDto
import br.com.favorites.domain.mappers.FavoriteAddOrRemoveToDtoMapper
import br.com.favorites.domain.mappers.FavoritesMovieToEntityMapper
import br.com.favorites.domain.model.AddOrRemoveFavorite
import br.com.favorites.domain.repository.FavoritesMoviesRepository
import br.com.local.model.movie_details.MovieDetailsEntity
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
        repositoryImpl: FavoritesMoviesRepositoryImpl
    ) : FavoritesMoviesRepository

    @ViewModelScoped
    @Binds
    fun bindFavoritesRemoteDataSource(
        impl: FavoritiesRemoteDataSourceImpl
    ) : br.com.favorites.data.repository.datasource.FavoritiesRemoteDataSource

    @ViewModelScoped
    @Binds
    fun bindFavoritesLocalDataSource(
        impl: FavoritiesLocalDataSourceImpl
    ) : FavoritiesLocalDataSource

    @ViewModelScoped
    @Binds
    fun bindLocalMapper(impl: FavoritesMoviesEntityToDomain)
    : Mapper<FavoritiesMovieEntity, Movie>

    @ViewModelScoped
    @Binds
    fun bindRemoteToLOcalMapper(
        impl: FavoritiesMoviesDtoToEntityMapper
    ) : Mapper<MoviesDto, FavoritiesMovieEntity>

    @ViewModelScoped
    @Binds
    fun bindRemoteToEntityMapper(
        impl: FavoritesMovieToEntityMapper
    ) : Mapper<MovieDetailsEntity, FavoritiesMovieEntity>

    @ViewModelScoped
    @Binds
    fun bindRemoteToDtoMapper(
        impl: FavoriteAddOrRemoveToDtoMapper
    ) : Mapper<AddOrRemoveFavorite, AddOrRemoveFavoriteDto>
}