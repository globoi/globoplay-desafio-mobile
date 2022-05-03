package com.example.testeglobojeremias.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VideosResult {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("results")
    @Expose
    private List<Video> results = null;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Video> getResults() {
        return results;
    }

    public void setResults(List<Video> results) {
        this.results = results;
    }

}
