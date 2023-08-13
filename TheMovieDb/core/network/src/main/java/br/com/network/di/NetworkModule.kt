package br.com.network.di

import android.content.Context
import android.util.Config.DEBUG
import br.com.network.ApiKeyInterceptor
import br.com.network.BuildConfig
import br.com.network.BuildConfig.BASE_URL
import br.com.network.ResultCallAdapterFactory
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

private const val CONTENT_TYPE = "application/json"
private const val QUERY_NAME_API_KEY = "Authorization"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    @Singleton
    @Provides
    fun provideInterceptor(): Interceptor = Interceptor { chain ->
        val originalRequest = chain.request()
        val url = originalRequest.newBuilder()
            .addHeader(
                QUERY_NAME_API_KEY, BuildConfig.ACCESS_TOKEN_AUTH
            )
            .build()
//        val url = originalRequest.url.newBuilder().addQueryParameter(
//            QUERY_NAME_API_KEY,
//            BuildConfig.ACCESS_TOKEN_AUTH,
//        ).build()
        chain.proceed(url)
    }

    @Singleton
    @Provides
    fun providesHttpClient( interceptor: Interceptor) : OkHttpClient {

        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }


        return OkHttpClient
            .Builder()
            .addInterceptor(interceptor)
            .addInterceptor(httpLoggingInterceptor)
            .readTimeout(15,TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .build()
    }


    @Provides
    fun provideGson(): Gson {
        return GsonBuilder()
            .create()
    }

    @Singleton
    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory = ResultCallAdapterFactory()

    @Singleton
    @Provides
    fun providesRetrofit(okHttpClient: OkHttpClient,
                         gson: Gson,
                         callAdapterFactory: CallAdapter.Factory
    ) : Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(callAdapterFactory)
            .build()
    }

}