package br.com.nerdrapido.themoviedbapp.ui.abstracts

import br.com.nerdrapido.themoviedbapp.data.model.ResponseWrapper

/**
 * Created By FELIPE GUSBERTI @ 08/03/2020
 */
interface Presenter<V : View> {

    /**
     * Initializes a view in the presenter. Must always be called in the view.
     */
    fun initializeView(view: V)

    /**
     * The view is been invoked and we need to check if it can really be shown or call a specific
     * routine.
     */
    fun viewIsInvoked()

    /**
     * The view is already loaded and is ready to show up. We need to check if it can really be
     * shown or call a specific routine.
     */
    fun viewIsAboutToBeShown()

    /**
     * The view is been closed.
     */
    fun viewIsClosed()

    /**
     * This function lower the verbosity and establishes a default behavior when an error is thrown
     * in a API use case call.
     *
     * [response] is the response from an API use case. It might contain a
     * [ResponseWrapper.Success] with an response object [T], a[ResponseWrapper.GenericError] when
     * the API responds with status code greater than 299 and a [ResponseWrapper.NetworkError] when
     * the API request error occurs due to network interference.
     *
     * [onSuccessAction] is the piece of code to be executed in case off success.
     *
     * if [response] is null then a generic error should be shown on screen.
     */
    fun <T> onResponseWrapper(response: ResponseWrapper<T>?, onSuccessAction: (responseObject: T) -> Unit)

}