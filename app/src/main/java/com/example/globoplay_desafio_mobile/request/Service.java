package com.example.globoplay_desafio_mobile.request;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Service {

    public static final String BASE_URL = "https://api.themoviedb.org";
    public static final String API_KEY = "API_KEY";

    public static Retrofit retrofit = null;
    public static MovieAPI movieAPI = null;

    public static MovieAPI getClient() {

        if (movieAPI != null)
            return movieAPI;

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        movieAPI = retrofit.create(MovieAPI.class);


        return movieAPI;
    }

}
