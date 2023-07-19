package com.app.fakegloboplay.features.detailmovie.screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.fakegloboplay.features.detailmovie.repository.DetailMoveRepository
import com.app.fakegloboplay.network.response.DetailsMove
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import okhttp3.MediaType

class DetailDatasheetViewModel(
    private val repository: DetailMoveRepository
) : ViewModel() {

    private val _detailsTv = MutableLiveData<DetailDatasheetStateView>()
    val detailsTv: LiveData<DetailDatasheetStateView> = _detailsTv

    fun getDetailsTv(seriesId: Int, mediaType: String?) {
        viewModelScope.launch {
            repository.getDetails(seriesId, mediaType)
                .catch { _ -> _detailsTv.value = DetailDatasheetStateView.Error }
                .collect { detailsMove ->
                detailsMove?.let {
                       _detailsTv.value = DetailDatasheetStateView.Success(it)
                }
            }
        }
    }

}