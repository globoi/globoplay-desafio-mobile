package com.example.testeglobojeremias;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.testeglobojeremias.models.VideosResult;
import com.example.testeglobojeremias.network.VideosRepository;

public class VideosViewModel extends AndroidViewModel {

    private final VideosRepository videosRepository;

    public VideosViewModel(@NonNull Application application, long movieID) {
        super(application);
        videosRepository = new VideosRepository(movieID);
    }

    public void getVideos(long movieID){
        videosRepository.getVideos(movieID);
    }

    public MutableLiveData<VideosResult> getVideosLiveData(){
        return videosRepository.getVideoMutableLiveData();
    }

    public MutableLiveData<Boolean> progressMutableLiveData(){
        return videosRepository.getProgressMutableLiveData();
    }

    public MutableLiveData<Boolean> failureMutableLiveData(){
        return videosRepository.getFailureMutableLiveData();
    }
}
