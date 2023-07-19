package com.app.fakegloboplay.features.detailmovie.screen

import com.app.fakegloboplay.network.response.DetailsMove

sealed class DetailDatasheetStateView {
    data class Success(val detailsMove: DetailsMove): DetailDatasheetStateView()
    object Error : DetailDatasheetStateView()
}