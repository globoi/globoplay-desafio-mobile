package com.com.ifood.repository.model

import androidx.annotation.StringRes
import com.com.ifood.R

enum class MoviesTitleCategory(@StringRes val message: Int) {
    POPULAR(R.string.popular),
    TOP_RATED(R.string.top_rated),
    UPCOMING(R.string.upcoming),
    NOW_PLAYING(R.string.now_playing);
}