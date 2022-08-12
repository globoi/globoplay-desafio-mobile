package com.example.globoplay.di

import com.example.globoplay.data.remote.MoviesAPI
import com.example.globoplay.data.remote.SeriesAPI
import com.example.globoplay.database.AppDatabase
import com.example.globoplay.database.repository.MediaRepository
import com.example.globoplay.viewmodel.ListaViewModel
import com.example.globoplay.viewmodel.MovieViewModel
import com.example.globoplay.viewmodel.TVSeriesViewModel
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class QualquerCoisa(var string: String = "qualquer coisa"){

}

val retrofitModule = module {

    factory {
        QualquerCoisa("Thalles")
    }

    single {
        Retrofit.Builder()
            .client(
                OkHttpClient.Builder().addInterceptor(
                    HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
                        .setLevel(HttpLoggingInterceptor.Level.HEADERS)
                ).build()
            )
            .baseUrl(MoviesAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
    }
}

val apiMovieModule = module {
    single(createdAtStart = false) {
        get<Retrofit>().create(MoviesAPI::class.java)
    }
}

val apiSeriesModule = module {
    single(createdAtStart = false) {
        get<Retrofit>().create(SeriesAPI::class.java)
    }
}

val viewModelMovieModule = module {
    viewModel {
        MovieViewModel(get())
    }
}

val viewModelSerieModule = module {
    viewModel {
        TVSeriesViewModel(get())
    }
}


val daoModule = module {
    single { AppDatabase.getInstance(androidContext()).myListDao() }
}


val repositoryModule = module {
    single { MediaRepository(get()) }
}

val viewModelListaModule = module {
    viewModel {
        ListaViewModel(get())
    }
}

