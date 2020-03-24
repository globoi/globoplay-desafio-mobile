package br.com.nerdrapido.themoviedbapp.domain.usecase

import java.util.*

/**
 * Created By FELIPE GUSBERTI @ 15/03/2020
 *
 * Movie DB Language selection did not affect account language so this use case returns the device
 * lang
 */
class GetLanguageUseCase {

    fun getLanguage(): String {
        return Locale.getDefault().language + "-" + Locale.getDefault().country
    }
}