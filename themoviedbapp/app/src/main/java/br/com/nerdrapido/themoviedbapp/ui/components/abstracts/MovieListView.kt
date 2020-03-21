package br.com.nerdrapido.themoviedbapp.ui.components.abstracts

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist.HorizontalMovieAdapter
import br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist.HorizontalMovieListOnScrollListener

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
abstract class MovieListView<T: MovieListViewHolder> @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr),
    MovieListOnScrollListener.OnNextPageNeeded {

    abstract val orientation: Int

    var titleText: String? = null
        set(value) {
            movieListTitleTextView?.text = value
            if (value != null) {
                movieListTitleTextView?.visibility = View.VISIBLE
            } else {
                movieListTitleTextView?.visibility = View.GONE
            }
            field = value
        }

    private var onLoadNextPage: OnLoadNextPage? = null

    protected var layoutManager: LinearLayoutManager

    protected var pageSize: Int

    /**
     * The number of pages to be loaded
     */
    protected var lastPage: Int

    protected val itemList = mutableListOf<MovieListResultObject>()

    protected abstract val adapter: MovieListAdapter<T>

    protected var movieListTitleTextView: TextView? = null

    protected var movieListRecyclerView: RecyclerView

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
            LinearLayoutManager(context, orientation, false)

        //defino o titulo
        movieListTitleTextView = findViewById(R.id.movieListTitleTv)
        movieListTitleTextView?.text = titleText

        //defino o adapter
        movieListRecyclerView = findViewById(R.id.movieListRv)
        movieListRecyclerView.layoutManager = layoutManager
        movieListRecyclerView.adapter = adapter
    }

    protected fun callInit() {

    }

    abstract fun inflateLayout()

    fun setOnPageChangeListener(
        onLoadNextPage: OnLoadNextPage,
        lastPage: Int = this.lastPage,
        pageSize: Int = this.pageSize
    ) {
        movieListRecyclerView.addOnScrollListener(
            HorizontalMovieListOnScrollListener(
                pageSize,
                lastPage,
                layoutManager,
                this
            )
        )
        this.onLoadNextPage = onLoadNextPage
        //Tudo certo carrega a primeira pagina
        onLoadNextPage.onLoadNextPage(1)

    }

    fun addItem(movieListResultObject: MovieListResultObject) {
        val oldItemCount = itemList.size
        itemList.add(movieListResultObject)
        post { adapter.notifyItemInserted(oldItemCount) }
    }

    fun addItemList(movieListResultObjectList: List<MovieListResultObject>) {
        val oldItemCount = itemList.size
        if (oldItemCount == 0) {
            replaceItemList(movieListResultObjectList)
            return
        }
        itemList.addAll(movieListResultObjectList)
        post { adapter.notifyItemRangeInserted(oldItemCount, movieListResultObjectList.size) }

    }

    fun replaceItemList(movieListResultObjectList: List<MovieListResultObject>) {
        itemList.clear()
        itemList.addAll(movieListResultObjectList)
        post { adapter.notifyDataSetChanged() }
    }

    fun clearItemList() {
        itemList.clear()
        post { adapter.notifyDataSetChanged() }
    }

    override fun loadPage(page: Int) {
        onLoadNextPage?.onLoadNextPage(page)
    }

    interface OnLoadNextPage {
        fun onLoadNextPage(page: Int)
    }
}