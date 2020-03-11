package br.com.nerdrapido.themoviedbapp.ui.home

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl

/**
 * Created By FELIPE GUSBERTI @ 10/03/2020
 */
class HomePresenterImpl(getLogInStateUseCase: GetLogInStateUseCase) :
    AbstractPresenterImpl<HomeView>(
        getLogInStateUseCase
    ), HomePresenter {

}