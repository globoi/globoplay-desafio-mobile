package com.nunkison.globoplaymobilechallenge

import androidx.annotation.StringRes
import com.nunkison.globoplaymobilechallenge.project.structure.MovieDetailData
import com.nunkison.globoplaymobilechallenge.ui.movies.data.MovieCover

fun stringResource(@StringRes id: Int) = TvApp.instance.getString(id)

fun MovieCover.toMovie() = Movie(
    id = id.toLong(),
    title = name,
    description = "",
    backgroundImageUrl = originalImage(cover),
    cardImageUrl = thumbImage(cover),
    videoUrl = "",
    studio = ""
)

fun MovieDetailData.toMovie() = Movie(
    id = id.toLong(),
    title = name,
    description = description,
    backgroundImageUrl = originalImage(coverPath),
    cardImageUrl = thumbImage(coverPath),
    videoUrl = youtubeKey,
    studio = "$producer - $country - $category"
)