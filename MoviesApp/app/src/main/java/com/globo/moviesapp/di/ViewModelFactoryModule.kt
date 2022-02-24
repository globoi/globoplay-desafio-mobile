package com.globo.moviesapp.di

import androidx.lifecycle.ViewModelProvider
import com.globo.moviesapp.shared.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory( providerFactory : ViewModelProviderFactory) : ViewModelProvider.Factory
}