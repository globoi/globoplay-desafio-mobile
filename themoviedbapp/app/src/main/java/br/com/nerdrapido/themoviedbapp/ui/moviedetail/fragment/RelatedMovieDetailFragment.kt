package br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListView
import kotlinx.android.synthetic.main.fragment_related_movies.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class RelatedMovieDetailFragment() :
    MovieDetailFragment() {

    var onRelatedMovieNewPageLoad: OnRelatedMovieNewPageLoad? = null

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
            5,
            20,
            object : MovieListView.OnNextPageNeeded {
                override fun onNextPageNeeded(page: Int) {
                    GlobalScope.launch {
                        relatedListV.addItemList(
                            onRelatedMovieNewPageLoad?.onRelatedMovieNewPageLoad(
                                page
                            ) ?: emptyList()
                        )
                    }
                }
            }
        )
    }

    interface OnRelatedMovieNewPageLoad {
        suspend fun onRelatedMovieNewPageLoad(page: Int): List<MovieListResultObject>
    }

}