package br.com.nerdrapido.themoviedbapp.ui.abstracts

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

}