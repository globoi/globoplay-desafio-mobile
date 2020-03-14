package br.com.nerdrapido.themoviedbapp.ui.components.verticamovielist

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
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

    override val adapter = VerticalMovieAdapter(
        itemList,
        context
    )

    override fun inflateLayout() {
        inflate(context, R.layout.view_movie_list_vertical, this)
        val set = ConstraintSet()
        set.clone(this)
    }

    init {
        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.MovieListView, 0, 0
        )
        titleText = a.getString(R.styleable.MovieListView_titleText)
        lastPage = a.getInt(R.styleable.MovieListView_lastPage, 5)
        pageSize = a.getInt(R.styleable.MovieListView_pageSize, -1)
        // If pageSize is not defined the view wont load properly unless lastPage is set to 1
        if (pageSize == -1) {
            lastPage = 1
        }
        a.recycle()

        inflateLayout()

        layoutManager =
            GridLayoutManager(context, 3, VERTICAL, false)

        //defino o titulo
        movieListTitleTextView = findViewById(R.id.movieListTitleTv)
        movieListTitleTextView?.text = titleText

        //defino o adapter
        movieListRecyclerView = findViewById(R.id.movieListRv)
        movieListRecyclerView.layoutManager = layoutManager
        movieListRecyclerView.adapter = adapter
    }
}