package com.example.globoplay.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.globoplay.data.remote.SeriesAPI
import com.example.globoplay.data.remote.response.PopularTVSeriesResponse
import com.example.globoplay.domain.PopularTVSeries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TVSeriesViewModel(private val api:SeriesAPI):ViewModel() {
    private val _popularSeries = MutableLiveData<List<PopularTVSeries>>()
    val popularSeries: LiveData<List<PopularTVSeries>> get() = _popularSeries

    private val _popularSeriesErrorResponse = MutableLiveData<String>()
    val popularSeriesErrorResponse: LiveData<String> get() = _popularSeriesErrorResponse

    suspend fun getPopularSeries() = withContext(Dispatchers.Main){
        val call: Call<PopularTVSeriesResponse> = api.getPopularSeries()
        call.enqueue(
            object : Callback<PopularTVSeriesResponse>{
                override fun onResponse(
                    call: Call<PopularTVSeriesResponse>,
                    response: Response<PopularTVSeriesResponse>
                ) {
                    if(response.isSuccessful){
                        _popularSeries.value = response.body()?.popularTVSeries?.map {
                            it.toPopularSeries()
                        }
                    }
                }

                override fun onFailure(call: Call<PopularTVSeriesResponse>, error: Throwable) {
                    _popularSeriesErrorResponse.value = error.message
                }
            }
        )

    }
}