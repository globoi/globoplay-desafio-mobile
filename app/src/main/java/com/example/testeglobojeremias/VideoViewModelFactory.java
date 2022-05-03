package com.example.testeglobojeremias;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class VideoViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;
    private final long movieID;

    public VideoViewModelFactory(Application application, long movieID) {
        this.application = application;
        this.movieID = movieID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new VideosViewModel(application, movieID);
    }
}
