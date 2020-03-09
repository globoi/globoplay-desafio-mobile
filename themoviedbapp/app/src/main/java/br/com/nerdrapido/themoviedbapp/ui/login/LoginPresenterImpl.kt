package br.com.nerdrapido.themoviedbapp.ui.login

import br.com.nerdrapido.themoviedbapp.domain.usecase.GetLogInStateUseCase
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractPresenterImpl
import br.com.nerdrapido.themoviedbapp.ui.abstracts.Presenter

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
class LoginPresenterImpl(view: LoginView, getLogInStateUseCase: GetLogInStateUseCase) : AbstractPresenterImpl<LoginView>(
    getLogInStateUseCase
), LoginPresenter {
}