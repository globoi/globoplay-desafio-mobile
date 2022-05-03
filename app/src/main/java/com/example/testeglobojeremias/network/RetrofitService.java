package com.example.testeglobojeremias.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {

    private static final String BASE_URL = "https://api.themoviedb.org/3/";

    private static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    static <S> S createService(Class<S> serviceClass){
        return retrofit.create(serviceClass);
    }
}
