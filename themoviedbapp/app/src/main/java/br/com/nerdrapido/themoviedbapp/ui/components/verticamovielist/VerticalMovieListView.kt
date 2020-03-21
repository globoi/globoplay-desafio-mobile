package br.com.nerdrapido.themoviedbapp.ui.components.verticamovielist

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListView


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class VerticalMovieListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : MovieListView<VerticalMovieViewHolder>(context, attrs, defStyleAttr) {

    override val orientation = VERTICAL

    override lateinit var layoutManager: LinearLayoutManager

    override var movieListRecyclerView: RecyclerView? = null

    override val adapter = VerticalMovieAdapter(
        itemList,
        context
    )

    init {
        inflateLayout()
    }

    override fun inflateLayout() {
        inflate(context, R.layout.view_movie_list_vertical, this)
        val set = ConstraintSet()
        set.clone(this)
        layoutManager =
            GridLayoutManager(context, 3, VERTICAL, false)

        //defino o adapter
        movieListRecyclerView = findViewById(R.id.movieListRv)
        movieListRecyclerView?.layoutManager = layoutManager
        movieListRecyclerView?.adapter = adapter
    }


}