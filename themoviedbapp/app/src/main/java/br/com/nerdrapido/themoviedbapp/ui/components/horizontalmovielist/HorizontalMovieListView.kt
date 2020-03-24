package br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.ui.components.abstracts.MovieListView
import kotlinx.android.synthetic.main.view_movie_list_horizontal.view.*


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class HorizontalMovieListView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : MovieListView<HorizontalMovieViewHolder>(context, attrs, defStyleAttr) {

    override val orientation = HORIZONTAL

    override val adapter = HorizontalMovieAdapter(itemList, context)

    override lateinit var layoutManager: LinearLayoutManager

    override var movieListRecyclerView: RecyclerView? = null

    init {
        inflateLayout()
        layoutManager = LinearLayoutManager(context, orientation, false)

        //defino o titulo
        movieListTitleTextView = movieListTitleTv
        movieListTitleTextView?.text = titleText

        //defino o adapter
        movieListRecyclerView = findViewById(R.id.movieListRv)
        movieListRecyclerView?.layoutManager = layoutManager
        movieListRecyclerView?.adapter = adapter
    }

    override fun inflateLayout() {
        inflate(context, R.layout.view_movie_list_horizontal, this)
        val set = ConstraintSet()
        set.clone(this)
    }


}