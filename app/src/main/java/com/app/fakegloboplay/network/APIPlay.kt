package com.app.fakegloboplay.network

import com.app.fakegloboplay.BuildConfig
import com.app.fakegloboplay.network.response.AccountResponse
import com.app.fakegloboplay.network.response.BaseResponse
import com.app.fakegloboplay.network.response.Credits
import com.app.fakegloboplay.network.response.DetailsMove
import com.app.fakegloboplay.network.response.MyFavorite
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface APIPlay {

    @GET("3/account/"+ BuildConfig.accountId)
    suspend fun getAccount():Response<AccountResponse>

    @GET("3/discover/tv?first_air_date_year=2018&include_adult=false&language=pt-BR&sort_by=vote_average.asc&with_companies=globo%20TV&with_genres=18%2C10766&with_origin_country=BR&with_original_language=pt")
    suspend fun getTvPopular(@Query("page") page:Int): Response<BaseResponse>

    @GET("3/movie/popular?language=pt-BR")
    suspend fun getMoviePopular(@Query("page") page:Int): Response<BaseResponse>

    @GET("3/account/${BuildConfig.accountId}/favorite/tv?language=pt-BR")
    suspend fun getMyFavoriteTv():Response<BaseResponse>

    @GET("3/account/${BuildConfig.accountId}/favorite/movies?language=pt-BR")
    suspend fun getMyFavoriteMovie():Response<BaseResponse>

    @POST("3/account/${BuildConfig.accountId}/favorite")
    @Headers("Content-Type: application/json")
    suspend fun postMyFavorite(@Body myFavorite: MyFavorite):Response<Any>

    @GET("3/tv/{series_id}/recommendations?language=pt-BR")
    suspend fun getTvRecommendations(@Path("series_id") seriesId: Int, @Query("page") page:Int): Response<BaseResponse>

    @GET("3/movie/{series_id}/recommendations?language=pt-BR")
    suspend fun getMovieRecommendations(@Path("series_id") seriesId: Int, @Query("page") page:Int): Response<BaseResponse>

    @GET("3/tv/{series_id}")
    suspend fun getDetailsTv(@Path("series_id") seriesId:Int):Response<DetailsMove>

    @GET("3/movie/{movie_id}")
    suspend fun getDetailsMovie(@Path("movie_id") movieId: Int): Response<DetailsMove>

    @GET("3/tv/{series_id}/credits")
    suspend fun getCreditsTv(@Path("series_id") seriesId:Int):Response<Credits>

    @GET("3/movie/{series_id}/credits")
    suspend fun getCreditsMovie(@Path("series_id") seriesId:Int):Response<Credits>
}