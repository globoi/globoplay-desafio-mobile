package com.example.testeglobojeremias.network;

import static com.example.testeglobojeremias.utils.Keys.apiKey;
import static com.example.testeglobojeremias.utils.Keys.language;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.testeglobojeremias.models.ReviewResult;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReviewsRepository {

    private final ReviewsAPI reviewsAPI;
    private final MutableLiveData<ReviewResult> reviewMutableLiveData;
    private final MutableLiveData<Boolean> progressMutableLiveData;
    private final MutableLiveData<Boolean> failureMutableLiveData;

    public ReviewsRepository(long movieID){
        reviewsAPI = RetrofitService.createService(ReviewsAPI.class);
        reviewMutableLiveData = new MutableLiveData<>();
        progressMutableLiveData = new MutableLiveData<>(false);
        failureMutableLiveData = new MutableLiveData<>(false);


        getReviews(movieID);
    }

    public void getReviews(long movieId){
        progressMutableLiveData.setValue(true);
        failureMutableLiveData.setValue(false);

        reviewsAPI.getReviewResults(movieId, apiKey, language, 1).enqueue(new Callback<ReviewResult>() {
            @Override
            public void onResponse(Call<ReviewResult> call, Response<ReviewResult> response) {
                if(response.isSuccessful()){
                    reviewMutableLiveData.setValue(response.body());
                }
                progressMutableLiveData.setValue(false);
            }

            @Override
            public void onFailure(Call<ReviewResult> call, Throwable t) {
                progressMutableLiveData.setValue(false);
                failureMutableLiveData.setValue(true);
                Log.e("Review Failure: ", Objects.requireNonNull(t.getLocalizedMessage()));
            }
        });
    }

    public MutableLiveData<ReviewResult> getReviewMutableLiveData() {
        return reviewMutableLiveData;
    }

    public MutableLiveData<Boolean> getProgressMutableLiveData() {
        return progressMutableLiveData;
    }

    public MutableLiveData<Boolean> getFailureMutableLiveData() {
        return failureMutableLiveData;
    }
}
