package com.mazer.globoplayapp.presentation.wrapper

import com.mazer.globoplayapp.domain.entities.Video

data class VideoUI(
    var video: Video,
    var isPlaying: Boolean
)
