package com.example.globoplay_desafio_mobile.request;

public class MediaRequest {

    private String media_type;
    private int media_id;
    private  boolean watchlist;

    public MediaRequest(String media_type, int media_id, boolean watchlist) {
        this.media_type = media_type;
        this.media_id = media_id;
        this.watchlist = watchlist;
    }

    //Getters
    public String getMedia_type() {
        return media_type;
    }

    public int getMedia_id() {
        return media_id;
    }

    public boolean isWatchlist() {
        return watchlist;
    }

    //Setters
    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public void setMedia_id(int media_id) {
        this.media_id = media_id;
    }

    public void setWatchlist(boolean watchlist) {
        this.watchlist = watchlist;
    }
}
