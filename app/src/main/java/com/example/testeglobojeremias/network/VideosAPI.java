package com.example.testeglobojeremias.network;

import com.example.testeglobojeremias.models.VideosResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface VideosAPI {
    @GET("movie/{movieID}/videos")
    Call<VideosResult> getVideoResults(@Path("movieID") long id,
                                       @Query("api_key") String apiKey,
                                       @Query("language") String lang);
}
