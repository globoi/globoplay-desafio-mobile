package com.example.globoplay_desafio_mobile.response.movies;

import com.example.globoplay_desafio_mobile.models.movies.VideoModel;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class VideoResponse {

    @SerializedName("results")
    private List<VideoModel> videos;

    // Getter
    public List<VideoModel> getVideos() {
        return videos;
    }
}
