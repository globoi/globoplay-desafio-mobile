package br.com.andersonmatte.service;

import br.com.andersonmatte.entity.Welcome;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITheMovieDB {

    String BASE_URL = "https://api.themoviedb.org/3/";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    //https://api.themoviedb.org/3/movie/popular?api_key=d4ac950397b93ecc6119e1ea932b0dfa&language=pt

    @GET("movie/popular")
    Call<Welcome> getPopularFilmes(@Query("api_key") String api_key,
                                   @Query("language") String language);


}
