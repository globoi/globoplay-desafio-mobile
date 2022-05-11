package com.ftoniolo.globoplay.presentation.home

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ftoniolo.core.domain.model.Film
import com.ftoniolo.core.domain.model.HomeData
import com.ftoniolo.core.usecase.GetFilmsByCategoryUseCase
import com.ftoniolo.core.usecase.base.ResultStatus
import com.ftoniolo.globoplay.R
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getFilmsByCategoryUseCase: GetFilmsByCategoryUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> get() = _uiState

    fun getFilmsByCategory() = viewModelScope.launch {
        getFilmsByCategoryUseCase(GetFilmsByCategoryUseCase.GetMoviesByCategoryParams(ID))
            .observeStatus()
    }

    private fun Flow<ResultStatus<HomeData>>.observeStatus() = viewModelScope.launch {
        collect { status ->
            _uiState.value = when (status) {
                ResultStatus.Loading -> UiState.Loading
                is ResultStatus.Success -> {

                    val homeParentList = mutableListOf<HomeParentVE>()

                    val popularFilms = status.data.popularFilms
                    mapHomeData(homeParentList,popularFilms,R.string.popular_film )

                    val animeFilms = status.data.animeFilms
                    mapHomeData(homeParentList,animeFilms,R.string.anim_film )

                    val adventureFilms = status.data.adventureFilms
                    mapHomeData(homeParentList,adventureFilms,R.string.adventure_film )

                    val documentaryFilms = status.data.documentaryFilm
                    mapHomeData(homeParentList,documentaryFilms,R.string.documentary_film )

                    val crimeFilms = status.data.crimeFilms
                    mapHomeData(homeParentList,crimeFilms,R.string.crime_film )

                    val horrorFilms = status.data.horrorFilms
                    mapHomeData(homeParentList,horrorFilms,R.string.horror_film )

                    val romanceFilms = status.data.romanceFilms
                    mapHomeData(homeParentList,romanceFilms,R.string.romance_film )

                    val warFilms = status.data.warFilms
                    mapHomeData(homeParentList,warFilms,R.string.war_film )

                    if(homeParentList.isNotEmpty()){
                        UiState.Success(homeParentList)

                    } else UiState.Empty
                }
                is ResultStatus.Error -> UiState.Error
            }

        }
    }

    sealed class UiState {
        object Loading : UiState()
        data class Success(val homeParentList: List<HomeParentVE>) : UiState()
        object Error : UiState()
        object Empty : UiState()
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

    companion object{
        private const val ID = 10752L
    }
}