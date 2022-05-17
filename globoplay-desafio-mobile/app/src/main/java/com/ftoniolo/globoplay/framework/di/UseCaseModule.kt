package com.ftoniolo.globoplay.framework.di

import com.ftoniolo.core.usecase.AddFavoriteUseCase
import com.ftoniolo.core.usecase.AddFavoritesUseCaseImpl
import com.ftoniolo.core.usecase.CheckFavoriteUseCase
import com.ftoniolo.core.usecase.CheckFavoriteUseCaseImpl
import com.ftoniolo.core.usecase.GetFavoritesUseCase
import com.ftoniolo.core.usecase.GetFavoritesUseCaseImpl
import com.ftoniolo.core.usecase.GetFilmsByCategoryUseCase
import com.ftoniolo.core.usecase.GetFilmsByCategoryUseCaseImpl
import com.ftoniolo.core.usecase.GetPopularFilmsUseCase
import com.ftoniolo.core.usecase.GetPopularFilmsUseCaseImpl
import com.ftoniolo.core.usecase.GetTrailerByIdUseCase
import com.ftoniolo.core.usecase.GetTrailerByIdUseCaseImpl
import com.ftoniolo.core.usecase.GetWatchTooUseCase
import com.ftoniolo.core.usecase.GetWatchTooUseCaseImpl
import com.ftoniolo.core.usecase.RemoveFavoriteUseCase
import com.ftoniolo.core.usecase.RemoveFavoriteUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface UseCaseModule {
    @Binds
    fun bindPopularFilmsUseCase(useCase: GetPopularFilmsUseCaseImpl): GetPopularFilmsUseCase

    @Binds
    fun bindMovieByCategoryUseCase(useCase: GetFilmsByCategoryUseCaseImpl): GetFilmsByCategoryUseCase

    @Binds
    fun bindGetWatchTooUseCase(useCase: GetWatchTooUseCaseImpl): GetWatchTooUseCase

    @Binds
    fun bindCheckFavoriteUseCase(useCase: CheckFavoriteUseCaseImpl): CheckFavoriteUseCase

    @Binds
    fun bindAddFavoriteUseCase(useCase: AddFavoritesUseCaseImpl): AddFavoriteUseCase

    @Binds
    fun bindRemoveFavoriteUseCase(useCase: RemoveFavoriteUseCaseImpl): RemoveFavoriteUseCase

    @Binds
    fun bindGetFavoriteUseCase(useCase: GetFavoritesUseCaseImpl): GetFavoritesUseCase

    @Binds
    fun bindGetTrailerByIdUseCase(useCase: GetTrailerByIdUseCaseImpl): GetTrailerByIdUseCase
}