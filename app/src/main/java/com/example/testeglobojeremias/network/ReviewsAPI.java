package com.example.testeglobojeremias.network;

import com.example.testeglobojeremias.models.ReviewResult;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ReviewsAPI {
    @GET("movie/{movieID}/reviews")
    Call<ReviewResult> getReviewResults(@Path("movieID") long id,
                                        @Query("api_key") String apiKey,
                                        @Query("language") String lang,
                                        @Query("page") int page);
}
