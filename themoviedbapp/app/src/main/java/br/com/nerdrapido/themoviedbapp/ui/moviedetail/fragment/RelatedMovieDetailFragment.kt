package br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListView
import kotlinx.android.synthetic.main.fragment_related_movies.*
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class RelatedMovieDetailFragment(private var onRelatedMovieNewPageLoad: OnRelatedMovieNewPageLoad) :
    MovieDetailFragment() {

    override var title = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val newVIew = inflater.inflate(R.layout.fragment_related_movies, null)


        return newVIew
    }

    override fun onResume() {
        super.onResume()
        relatedListV.setOnPageChangeListener(
            object : MovieListView.OnLoadNextPage {
                override fun onLoadNextPage(page: Int) {
                    runBlocking {
                        async(coroutineContext) {
                            relatedListV.addItemList(
                                onRelatedMovieNewPageLoad.onRelatedMovieNewPageLoad(
                                    page
                                )
                            )
                        }
                    }
                }
            },
            5,
            20
        )
    }

    interface OnRelatedMovieNewPageLoad {
        suspend fun onRelatedMovieNewPageLoad(page: Int): List<MovieListResultObject>
    }

}