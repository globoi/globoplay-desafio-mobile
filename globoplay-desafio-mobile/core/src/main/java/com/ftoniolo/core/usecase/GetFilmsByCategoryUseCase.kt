package com.ftoniolo.core.usecase

import com.ftoniolo.core.data.repository.FilmsRepository
import com.ftoniolo.core.domain.model.HomeData
import com.ftoniolo.core.usecase.base.CoroutinesDispatchers
import com.ftoniolo.core.usecase.base.ResultStatus
import com.ftoniolo.core.usecase.base.UseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetFilmsByCategoryUseCase {

    operator fun invoke(params: GetMoviesByCategoryParams): Flow<ResultStatus<HomeData>>

    data class GetMoviesByCategoryParams(val genreId: Long)
}

@Suppress("MagicNumber")
class GetFilmsByCategoryUseCaseImpl @Inject constructor(
    private val repository: FilmsRepository,
    private val dispatchers: CoroutinesDispatchers
) : GetFilmsByCategoryUseCase,
    UseCase<GetFilmsByCategoryUseCase.GetMoviesByCategoryParams,
            HomeData>() {
    override suspend fun doWork(
        params: GetFilmsByCategoryUseCase.GetMoviesByCategoryParams
    ): ResultStatus<HomeData> {

        return withContext(dispatchers.io()) {

            val popularFilmsDeferred = async { repository.getPopularFilms() }
            val adventureDeferred = async { repository.getMoviesByCategory(12) }
            val animeDeferred = async { repository.getMoviesByCategory(16) }
            val documentaryDeferred = async { repository.getMoviesByCategory(99) }
            val crimeDeferred = async { repository.getMoviesByCategory(80) }
            val horrorDeferred = async { repository.getMoviesByCategory(27) }
            val romanceDeferred = async { repository.getMoviesByCategory(10749) }
            val warDeferred = async { repository.getMoviesByCategory(10752) }

            ResultStatus.Success(
                HomeData(
                    popularFilmsDeferred.await(),
                    adventureDeferred.await(),
                    animeDeferred.await(),
                    crimeDeferred.await(),
                    documentaryDeferred.await(),
                    horrorDeferred.await(),
                    romanceDeferred.await(),
                    warDeferred.await()
                )
            )
        }
    }
}