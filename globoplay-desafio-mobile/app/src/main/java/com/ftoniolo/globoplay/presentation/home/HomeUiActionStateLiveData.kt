package com.ftoniolo.globoplay.presentation.home

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.usecase.GetFilmsByCategoryUseCase
import com.ftoniolo.globoplay.R
import com.ftoniolo.globoplay.presentation.extensions.watchStatus
import kotlin.coroutines.CoroutineContext

class HomeUiActionStateLiveData (
    private val coroutineContext: CoroutineContext,
    private val getFilmsByCategoryUseCase: GetFilmsByCategoryUseCase
){

    private val action = MutableLiveData<Action>()
    val state:  LiveData<UiState> = action.switchMap {
        liveData(coroutineContext) {
            when (it){
                is Action.Load -> {
                    getFilmsByCategoryUseCase.invoke(
                        GetFilmsByCategoryUseCase.GetMoviesByCategoryParams()
                    ).watchStatus(
                        loading = {
                            emit(UiState.Loading)
                        },
                        success = {
                            val homeParentList = mutableListOf<HomeParentVE>()

                            val popularFilms = it.popularFilms
                            mapHomeData(homeParentList,popularFilms, R.string.popular_film )

                            val animeFilms = it.animeFilms
                            mapHomeData(homeParentList,animeFilms,R.string.anim_film )

                            val adventureFilms = it.adventureFilms
                            mapHomeData(homeParentList,adventureFilms,R.string.adventure_film )

                            val documentaryFilms = it.documentaryFilm
                            mapHomeData(homeParentList,documentaryFilms,R.string.documentary_film )

                            val crimeFilms = it.crimeFilms
                            mapHomeData(homeParentList,crimeFilms,R.string.crime_film )

                            val horrorFilms = it.horrorFilms
                            mapHomeData(homeParentList,horrorFilms,R.string.horror_film )

                            val romanceFilms = it.romanceFilms
                            mapHomeData(homeParentList,romanceFilms,R.string.romance_film )

                            val warFilms = it.warFilms
                            mapHomeData(homeParentList,warFilms,R.string.war_film )

                            if(homeParentList.isNotEmpty()){
                                emit(UiState.Success(homeParentList))
                            } else emit(UiState.Empty)
                        },
                        error = {
                            emit(UiState.Error)
                        }
                    )
                }
            }
        }
    }

    fun load(){
        action.value = Action.Load
    }

    private fun mapHomeData(homeParentList: MutableList<HomeParentVE>, list: List<Film>, @StringRes category: Int){
        if(list.isNotEmpty()){
            list.map {
                HomeChildVE(it.id, it.overview, it.title, it.genreIds, it.imageUrl,
                    it.releaseDate, it.originalLanguage)
            }.also {
                homeParentList.add(
                    HomeParentVE(
                        category,
                        it
                    )
                )
            }
        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val homeParentList: List<HomeParentVE>) : UiState()
        object Error : UiState()
        object Empty : UiState()
    }

    sealed class Action {
        object Load : Action()
    }
}