package com.example.globoplay_desafio_mobile.models.movies;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MovieModel implements Parcelable{

    private int id;
    private String original_title;
    private String poster_path;
    private String overview;
    private String release_date;
    private String original_language;
    private List<Integer> genre_ids;
    private List<CastModel> cast;
    private String genre_names;
    private String video_id;
    private List<MovieModel> similar_movies;

    //Constructor
    public MovieModel(int id, String original_title, String poster_path, String overview, String release_date, String original_language, List<Integer> genre_ids, String video_id) {
        this.id = id;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.release_date = release_date;
        this.original_language = original_language;
        this.genre_ids = genre_ids;
        this.video_id = video_id;
    }

    protected MovieModel(Parcel in) {
        id = in.readInt();
        original_title = in.readString();
        poster_path = in.readString();
        overview = in.readString();
        release_date = in.readString();
        original_language = in.readString();
        genre_names = in.readString();
        video_id = in.readString();
    }

    public static final Creator<MovieModel> CREATOR = new Creator<MovieModel>() {
        @Override
        public MovieModel createFromParcel(Parcel in) {
            return new MovieModel(in);
        }

        @Override
        public MovieModel[] newArray(int size) {
            return new MovieModel[size];
        }
    };

    //Getters
    public int getId() {
        return id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public List<Integer> getGenre_ids() {
        return genre_ids;
    }

    public String getGenre_names() {
        return genre_names.substring(0, genre_names.length()-1);
    }

    public List<CastModel> getCast() {
        return cast;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public String getVideo() {
        return video_id;
    }

    public List<MovieModel> getSimilar_movies() {
        return similar_movies;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public void setGenre_ids(List<Integer> genre_ids) {
        this.genre_ids = genre_ids;
    }

    public void setGenre_names(String genre_names) {
        if(this.genre_names!=null)
            this.genre_names += genre_names + ",";
        else
            this.genre_names = genre_names + ",";
    }

    public void setCast(List<CastModel> cast) {
        this.cast = cast;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public void setVideo(String video_id) {
        this.video_id = video_id;
    }

    public void setSimilar_movies(List<MovieModel> similar_movies) {
        this.similar_movies = similar_movies;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(original_title);
        dest.writeString(poster_path);
        dest.writeString(overview);
        dest.writeString(release_date);
        dest.writeString(original_language);
        dest.writeString(genre_names);
        dest.writeString((video_id));
    }
}
