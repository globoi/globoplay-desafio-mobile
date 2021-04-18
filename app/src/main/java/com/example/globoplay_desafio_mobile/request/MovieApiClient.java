package com.example.globoplay_desafio_mobile.request;

import android.annotation.SuppressLint;
import android.os.Build;
import android.util.Log;
import androidx.lifecycle.MutableLiveData;

import com.example.globoplay_desafio_mobile.interfaces.OnScrollListener;
import com.example.globoplay_desafio_mobile.models.movies.CastModel;
import com.example.globoplay_desafio_mobile.models.movies.GenderModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.models.movies.VideoModel;
import com.example.globoplay_desafio_mobile.response.movies.CastResponse;
import com.example.globoplay_desafio_mobile.response.movies.GenderResponse;
import com.example.globoplay_desafio_mobile.response.movies.MovieResponse;
import com.example.globoplay_desafio_mobile.response.movies.VideoResponse;
import com.example.globoplay_desafio_mobile.utils.Variables;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieApiClient {

    private static MovieApiClient instance;
    //movies by genres
    private MutableLiveData<List<GenderModel>> genres;
    //movies
    private List<MovieModel> movieByGenreList = new ArrayList<>();
    //movies cast
    private MutableLiveData<List<CastModel>> movieCast;
    //similar movies
    private MutableLiveData<List<MovieModel>> similarMovies;
    //url videos by movie
    private MutableLiveData<List<VideoModel>> urlVideosMovie;
    //all movies
    private MutableLiveData<List<MovieModel>> listAllMovies;

    private int total_pages = 0;

    public static MovieApiClient getInstance() {
        if(instance==null){
            instance = new MovieApiClient();
        }
        return instance;
    }

    //Constructor
    private MovieApiClient(){
        genres = new MutableLiveData<>();
        movieCast = new MutableLiveData<>();
        similarMovies = new MutableLiveData<>();
        urlVideosMovie = new  MutableLiveData<>();
        listAllMovies = new MutableLiveData<>();
    }


    //Setters
    public void setNullmovieCast() {
        this.movieCast.postValue(null);
    }

    public void setNullurlVideosMovie() {
        this.urlVideosMovie.postValue(null);
    }

    public void setNullsimilarMovies() {
        this.similarMovies.postValue(null);
    }

    public void setNullMoviesPagination(){
        this.listAllMovies.postValue(null);
    }


    //Getters
    public MutableLiveData<List<GenderModel>> getGenres() {
        return genres;
    }

    public MutableLiveData<List<CastModel>> getMovieCast() {
        return movieCast;
    }

    public MutableLiveData<List<MovieModel>> getSimilarMovies() {
        return similarMovies;
    }

    public MutableLiveData<List<VideoModel>> getUrlVideosMovie() {
        return urlVideosMovie;
    }

    public MutableLiveData<List<MovieModel>> getAllMovies() {
        return listAllMovies;
    }

    public int getTotal_pages(){
        return total_pages;
    }

    //Get genres
    public void getGenresMovies(){

        //Check that the genreList has already been filled.
        //When starting the application, the user can click on the MyList tab before the list of movies on the Home tab is loaded
        if(Variables.genreList !=null) {
            if (Variables.genreList.size() != 0) {
                genres.postValue(Variables.genreList); //update
                getMoviesByGenres(Variables.genreList);
                return;
            }
        }

        Call<GenderResponse> call = Service.getClient().getGenders(Service.API_KEY);

        call.enqueue(new Callback<GenderResponse>() {
            @Override
            public void onResponse(Call<GenderResponse> call, Response<GenderResponse> response) {

                if(response.isSuccessful())
                {
                    if(response.body() != null) {
                        List<GenderModel> list = response.body().getGenders();
                        genres.postValue(list); //update
                        getMoviesByGenres(list);
                    }
                }
            }

            @Override
            public void onFailure(Call<GenderResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }

    //Get movies by genres
    //add movies to the array<MovieModel>
    @SuppressLint("CheckResult")
    public void getMoviesByGenres(List<GenderModel> genderModels){
        ArrayList<Observable<MovieResponse>> observables = new ArrayList<>();
        for(int i=0; i<genderModels.size(); i++){
            observables.add(Service.getClient().getMoviesByGenres(Service.API_KEY, 1, genderModels.get(i).getId()));
        }

        movieByGenreList = new ArrayList<>();

        //get movies by gender
        Observable.merge(observables).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<MovieResponse>() {
                    @Override
                    public void onNext(@NonNull MovieResponse movieResponse) {
                        if(movieResponse!=null)
                            movieByGenreList.addAll(movieResponse.getMovies()); //add movies
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        prepareMergedDataAndSend(genderModels);
                    }
                });
    }

    //add movies to genrer
    public void  prepareMergedDataAndSend(List<GenderModel> finalGenderModels){

        //Add movies in the genre

        Variables.genreList = finalGenderModels;

        HashSet<Object> seen=new HashSet<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            movieByGenreList.removeIf(e->!seen.add(e.getId())); // remove repeated movies
        }

        for (MovieModel movie : movieByGenreList) {   //Add movies to the array genders (GenderModel)

            for (int id_gender : movie.getGenre_ids()) {

                    for (GenderModel gender : finalGenderModels)
                    {
                        if(id_gender == gender.getId())
                        {
                            movie.setGenre_names(gender.getName());
                            gender.setMovie(movie);
                            break;
                        }
                    }
            }
        }

        genres.postValue(finalGenderModels);

        //        HashMap<Integer, HashSet<MovieModel>> mapped = new HashMap<>();
//
//        for (MovieModel movie : movieByGenderList){
//            for (int id : movie.getGenre_ids()) {
//                HashSet<MovieModel> movieSet = new HashSet<>();
//                if (mapped.containsKey(id)){
//                    movieSet = mapped.get(id);
//                }
//                movieSet.add(movie);
//                mapped.put(id, movieSet);
//            }
//        }
//
//        for (GenderModel gender: finalGenderModels){
//            if (mapped.containsKey(gender.getId())){
//                HashSet<MovieModel> movieModels = mapped.get(gender.getId());
//
//                List<MovieModel> movies = new ArrayList();
//                movies.addAll(movieModels);
//
//                gender.setMovies(movies);
//
//
//            }
//        }
//
//        genders.postValue(finalGenderModels);

    }

    //get cast
    @SuppressLint("CheckResult")
    public void getCastID(int id_movie){

        Call<CastResponse> call = Service.getClient().getCast(id_movie,Service.API_KEY);

        call.enqueue(new Callback<CastResponse>() {
            @Override
            public void onResponse(Call<CastResponse> call, Response<CastResponse> response) {

                if(response.isSuccessful())
                {
                    if(response.body() != null) {
                        List<CastModel> list = response.body().getCast();
                        movieCast.postValue(list); //update
                    }
                }
            }

            @Override
            public void onFailure(Call<CastResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });


    }

    //get similar movies
    public void getSimilarMoviesID(int id_movie){

        Call<MovieResponse> call = Service.getClient().getSimilarMovies(id_movie,Service.API_KEY,1);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                if(response.isSuccessful())
                {
                    if(response.body() != null) {
                        List<MovieModel> list = response.body().getMovies();

                        for (MovieModel movie : list) {   //Add genres names

                            for (int id_gender : movie.getGenre_ids()) {

                                for (GenderModel gender : Variables.genreList)
                                {
                                    if(id_gender == gender.getId())
                                    {
                                        movie.setGenre_names(gender.getName());
                                        break;
                                    }
                                }
                            }
                        }

                        similarMovies.postValue(list);  //update
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }

    //get video url
    @SuppressLint("CheckResult")
    public void getUrlVideoMoviesID(int id_movie){

        Call<VideoResponse> call = Service.getClient().getUrlVideoMovie(id_movie,Service.API_KEY);

        call.enqueue(new Callback<VideoResponse>() {
            @Override
            public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {

                if(response.isSuccessful())
                {
                    if(response.body() != null) {
                        List<VideoModel> list = response.body().getVideos();
                        urlVideosMovie.postValue(list); //update
                    }
                }
            }

            @Override
            public void onFailure(Call<VideoResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });

    }

    //get movies with pagination
    public void getMoviesPagination(int genre, int page){

        Call<MovieResponse> call = Service.getClient().getMoviesPagination(Service.API_KEY,page,genre);

        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                if(response.isSuccessful())
                {
                    if(response.body() != null) {
                        List<MovieModel> list = response.body().getMovies();

                        total_pages = response.body().getTotal_pages();

                        if(Variables.genreList == null) {  //When starting the application, the user can click on the MyList tab before the list of movies on the Home tab is loaded
                            getGenres(list);
                        }
                        else {
                            for (MovieModel movie : list) {   //Add genres names

                                for (int id_gender : movie.getGenre_ids()) {

                                    for (GenderModel gender : Variables.genreList) {
                                        if (id_gender == gender.getId()) {
                                            movie.setGenre_names(gender.getName());
                                            break;
                                        }
                                    }
                                }
                            }

                            listAllMovies.postValue(list); //update
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

                        listAllMovies.postValue(movieModels);
                    }
                }
            }

            @Override
            public void onFailure(Call<GenderResponse> call, Throwable t) {
                Log.v("Tag","Error fetching data " + t.getMessage());
            }
        });
    }

}
