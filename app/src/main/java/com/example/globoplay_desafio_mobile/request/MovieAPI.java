package com.example.globoplay_desafio_mobile.request;

import com.example.globoplay_desafio_mobile.models.account.UserModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.response.account.AuthenticationResponse;
import com.example.globoplay_desafio_mobile.response.account.LoginResponse;
import com.example.globoplay_desafio_mobile.response.account.SessionResponse;
import com.example.globoplay_desafio_mobile.response.account.UserResponse;
import com.example.globoplay_desafio_mobile.response.movies.CastResponse;
import com.example.globoplay_desafio_mobile.response.movies.GenderResponse;
import com.example.globoplay_desafio_mobile.response.movies.MovieResponse;
import com.example.globoplay_desafio_mobile.response.movies.VideoResponse;
import com.example.globoplay_desafio_mobile.response.watchlist.WatchListResponse;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MovieAPI {

    //Login

    //Create Token
    @GET("/3/authentication/token/new")
    Call<AuthenticationResponse> createRequestToken(@Query("api_key") String api_key);

    //Validate login and password
    @FormUrlEncoded
    @POST("/3/authentication/token/validate_with_login")
    Call<AuthenticationResponse> validateWithLogin(@Field("username") String username,@Field("password") String password, @Field("request_token") String request_token,@Query("api_key") String api_key);

    //Create Session
    @FormUrlEncoded
    @POST("/3/authentication/session/new")
    Call<SessionResponse> createSession(@Field("request_token") String request_token, @Query("api_key") String api_key);

    //Account ID
    @GET("/3/account")
    Call<LoginResponse> getAccountId(@Query("session_id") String session_id, @Query("api_key") String api_key);

    //Get details user
    @GET("/3/account")
    Call<UserResponse> getDetails(@Query("session_id") String session_id, @Query("api_key") String api_key);



    //Watchlist
    //get fav movies
    @GET("/3/account/{account_id}/watchlist/movies")
    Call<MovieResponse> getWatchList(@Path("account_id") String account_id, @Query("session_id") String session_id,@Query("sort_by") String sort_by,@Query("api_key") String api_key,@Query("page") int page);

    //Rm and Add movie fav
    @POST("/3/account/{account_id}/watchlist")
    Call<WatchListResponse> addRmMovieFav(@Body MediaRequest media, @Path("account_id") String account_id, @Query("session_id") String session_id, @Query("api_key") String api_key);



    //Movies
    //Get movies by genre
    @GET("3/discover/movie")
    Observable<MovieResponse> getMoviesByGenres(@Query("api_key") String api_key, @Query("page") int page, @Query("with_genres") int gender);

    @GET("3/discover/movie")
    Call<MovieResponse> getMoviesPagination(@Query("api_key") String api_key, @Query("page") int page, @Query("with_genres") int gender);

    //Get genres
    @GET("3/genre/movie/list")
    Call<GenderResponse> getGenders(@Query("api_key") String api_key);

    //Get cast
    @GET("3/movie/{movie_id}/credits")
    Call<CastResponse> getCast(@Path("movie_id") int movie_id, @Query("api_key") String api_key);

    //get similar movies
    @GET("3/movie/{movie_id}/similar")
    Call<MovieResponse> getSimilarMovies(@Path("movie_id") int movie_id, @Query("api_key") String api_key,@Query("page") int page);

    //get url video
    @GET("3/movie/{movie_id}/videos")
    Call<VideoResponse> getUrlVideoMovie(@Path("movie_id") int movie_id, @Query("api_key") String api_key);
}
