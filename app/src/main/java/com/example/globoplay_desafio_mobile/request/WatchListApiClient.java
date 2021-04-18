package com.example.globoplay_desafio_mobile.request;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import com.example.globoplay_desafio_mobile.models.movies.GenderModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.response.movies.GenderResponse;
import com.example.globoplay_desafio_mobile.response.movies.MovieResponse;
import com.example.globoplay_desafio_mobile.response.watchlist.WatchListResponse;
import com.example.globoplay_desafio_mobile.utils.Variables;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WatchListApiClient {

    private WatchListApiClient instance;
    private MutableLiveData<List<MovieModel>> movies;
    private MutableLiveData<Integer> responseAddRm;

    public WatchListApiClient getInstance(){
        if(instance == null)
            instance = new WatchListApiClient();

        return instance;
    }

    //Constructor
    public WatchListApiClient(){
        movies = new MutableLiveData<>();
        responseAddRm = new MutableLiveData<>();
    }

    //Getters
    public MutableLiveData<List<MovieModel>> getMovies() {
        return movies;
    }

    public MutableLiveData<Integer> getResponse() {
        return responseAddRm;
    }

    //Setters
    public void setNullresponseAddRm() {
        this.responseAddRm.postValue(null);
    }

    //get favorite movies
    public void getWatchList(String accountId, String sessionId){

        Call<MovieResponse> call = Service.getClient().getWatchList(accountId,sessionId,"created_at.asc",Service.API_KEY,1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                if(response.isSuccessful()) {
                    if (response.body() != null) {
                        List<MovieModel> movieModels = response.body().getMovies();

                        if(Variables.genreList == null) {  //When starting the application, the user can click on the MyList tab before the list of movies on the Home tab is loaded
                            getGenres(movieModels);
                        }
                        else {

                            for (MovieModel movie : movieModels) {   //Add genres names

                                for (int id_gender : movie.getGenre_ids()) {

                                    for (GenderModel gender : Variables.genreList) {
                                        if (id_gender == gender.getId()) {
                                            movie.setGenre_names(gender.getName());
                                            break;
                                        }
                                    }
                                }
                            }

                            movies.postValue(movieModels);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }

    //get genres
    //When starting the application, the user can click on the MyList tab before the list of movies on the Home tab is loaded
    private void getGenres(List<MovieModel> movieModels) {
        Call<GenderResponse> call = Service.getClient().getGenders(Service.API_KEY);

        call.enqueue(new Callback<GenderResponse>() {
            @Override
            public void onResponse(Call<GenderResponse> call, Response<GenderResponse> response) {

                if(response.isSuccessful())
                {
                    if(response.body() != null) {
                        List<GenderModel> list = response.body().getGenders();
                        Variables.genreList.addAll(list);

                        for (MovieModel movie : movieModels) {   //Add genres names

                            for (int id_gender : movie.getGenre_ids()) {

                                for (GenderModel gender : Variables.genreList) {
                                    if (id_gender == gender.getId()) {
                                        movie.setGenre_names(gender.getName());
                                        break;
                                    }
                                }
                            }
                        }

                        movies.postValue(movieModels);
                    }
                }
            }

            @Override
            public void onFailure(Call<GenderResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }

    //add or rm the movie from favorites
    public void addRmMovieFav(String accountId, String sessionId,MediaRequest mediaRequest){
        Call<WatchListResponse> call = Service.getClient().addRmMovieFav(mediaRequest,accountId,sessionId,Service.API_KEY);

        call.enqueue(new Callback<WatchListResponse>() {
            @Override
            public void onResponse(Call<WatchListResponse> call, Response<WatchListResponse> response) {
                if(response.isSuccessful())
                {
                    if(response.body() != null)
                    {
                        if(response.body().getStatus_code() == 1 || response.body().getStatus_code() == 13) //Sucess
                            responseAddRm.postValue(response.body().getStatus_code());
                    }
                }
            }

            @Override
            public void onFailure(Call<WatchListResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }
}
