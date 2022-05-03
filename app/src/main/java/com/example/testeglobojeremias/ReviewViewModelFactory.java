package com.example.testeglobojeremias;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ReviewViewModelFactory implements ViewModelProvider.Factory {

    private final Application mApplication;
    private final long movieID;

    public ReviewViewModelFactory(Application mApplication, long movieID) {
        this.mApplication = mApplication;
        this.movieID = movieID;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ReviewViewModel(mApplication, movieID);
    }
}
