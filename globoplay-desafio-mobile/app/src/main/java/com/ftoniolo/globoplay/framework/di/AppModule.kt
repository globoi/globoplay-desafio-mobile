package com.ftoniolo.globoplay.framework.di

import com.ftoniolo.globoplay.framework.imageLoader.GlideImageLoader
import com.ftoniolo.globoplay.framework.imageLoader.ImageLoader
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent

@Module
@InstallIn(FragmentComponent::class)
interface AppModule {

    @Binds
    fun bindImageLoader(imageLoader: GlideImageLoader) : ImageLoader
}