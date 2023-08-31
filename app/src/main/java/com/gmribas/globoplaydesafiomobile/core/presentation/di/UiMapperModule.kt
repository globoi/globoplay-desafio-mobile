package com.gmribas.globoplaydesafiomobile.core.presentation.di

import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.FindMediaByIdUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MovieDetailsUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.MoviePagedUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.RemoveMediaUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.SaveMovieUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.SaveTvShowUIMapper
import com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.TvShowDetailsUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.MovieUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.TopRatedTvShowUIMapper
import com.gmribas.globoplaydesafiomobile.feature.home.presentation.mapper.TvShowUIMapper
import com.gmribas.globoplaydesafiomobile.feature.mylist.presentation.mapper.MediaListUIMapper
import org.koin.dsl.module

val uiMapperModule = module {

    single { MovieUIMapper() }

    single { TvShowUIMapper() }

    single { TopRatedTvShowUIMapper() }

    single { MovieDetailsUIMapper() }

    single { MoviePagedUIMapper() }

    single { TvShowDetailsUIMapper() }

    single { com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper.TvShowUIMapper() }

    single { MediaListUIMapper() }

    single { RemoveMediaUIMapper() }

    single { SaveMovieUIMapper() }

    single { SaveTvShowUIMapper() }

    single { FindMediaByIdUIMapper() }
}