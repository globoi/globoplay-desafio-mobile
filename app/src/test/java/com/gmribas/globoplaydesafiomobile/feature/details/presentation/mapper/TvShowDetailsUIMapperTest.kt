package com.gmribas.globoplaydesafiomobile.feature.details.presentation.mapper

import com.gmribas.globoplaydesafiomobile.core.domain.model.TvShowDetails
import com.gmribas.globoplaydesafiomobile.feature.details.domain.usecase.GetTvShowDetailsUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class TvShowDetailsUIMapperTest {

    private val mapper = TvShowDetailsUIMapper()

    @Test
    fun convertSuccess() {
        val tvShow =
            TvShowDetails(
                adult = true,
                backdropPath = null,
                episodeRunTime = emptyList(),
                firstAirDate = "",
                homepage = "",
                id = 1,
                inProduction = true,
                languages = emptyList(),
                lastAirDate = "",
                name = "",
                numberOfEpisodes = 0L,
                numberOfSeasons = 0L,
                originCountry = emptyList(),
                originalLanguage = "",
                originalName = "",
                overview = "",
                popularity = 0.0,
                posterPath = "",
                spokenLanguages = emptyList(),
                status = "",
                tagline = "",
                type = "",
                voteAverage = 0.0,
                voteCount = 0L,
                isTvShow = true
            )

        val response = GetTvShowDetailsUseCase.Response(tvShow)

        val result = mapper.convertSuccess(response)

        assertEquals(response.details, result)
    }
}