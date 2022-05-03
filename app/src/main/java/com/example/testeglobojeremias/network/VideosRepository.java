package com.example.testeglobojeremias.network;

import static com.example.testeglobojeremias.utils.Keys.apiKey;
import static com.example.testeglobojeremias.utils.Keys.language;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.testeglobojeremias.models.VideosResult;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class VideosRepository {

    private final VideosAPI videosAPI;
    private final MutableLiveData<VideosResult> videoMutableLiveData;
    private final MutableLiveData<Boolean> progressMutableLiveData;
    private final MutableLiveData<Boolean> failureMutableLiveData;

    public VideosRepository(long movieID){
        videosAPI = RetrofitService.createService(VideosAPI.class);
        videoMutableLiveData = new MutableLiveData<>();
        progressMutableLiveData = new MutableLiveData<>(false);
        failureMutableLiveData = new MutableLiveData<>(false);

        getVideos(movieID);
    }

    public void getVideos(long movieID){
        progressMutableLiveData.setValue(true);
        failureMutableLiveData.setValue(false);

        videosAPI.getVideoResults(movieID, apiKey, language).enqueue(new Callback<VideosResult>() {
            @Override
            public void onResponse(Call<VideosResult> call, Response<VideosResult> response) {
                if(response.isSuccessful()){
                    videoMutableLiveData.setValue(response.body());
                }
                progressMutableLiveData.setValue(false);
                failureMutableLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<VideosResult> call, Throwable t) {
                progressMutableLiveData.setValue(false);
                failureMutableLiveData.setValue(true);
                Log.e("Video Failure: ", Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }

    public MutableLiveData<VideosResult> getVideoMutableLiveData() {
        return videoMutableLiveData;
    }

    public MutableLiveData<Boolean> getProgressMutableLiveData() {
        return progressMutableLiveData;
    }

    public MutableLiveData<Boolean> getFailureMutableLiveData() {
        return failureMutableLiveData;
    }
}
