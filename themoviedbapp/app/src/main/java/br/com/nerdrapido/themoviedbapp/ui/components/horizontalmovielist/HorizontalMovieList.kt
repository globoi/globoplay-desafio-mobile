package br.com.nerdrapido.themoviedbapp.ui.components.horizontalmovielist

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
import br.com.nerdrapido.themoviedbapp.data.model.MovieListResultObject


/**
 * Created By FELIPE GUSBERTI @ 12/03/2020
 */
class HorizontalMovieList @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr),
    HorizontalMovieListOnScrollListener.OnNextPageNeeded {

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

    private val layoutManager: LinearLayoutManager

    private val pageSize: Int

    /**
     * The number of pages to be loaded
     */
    private var lastPage: Int

    private val itemList = mutableListOf<MovieListResultObject>()

    private val adapter =
        HorizontalMovieAdapter(
            itemList,
            context
        )

    private val movieListTitleTextView: TextView?

    private val movieListRecyclerView: RecyclerView

    init {

        val a = context.obtainStyledAttributes(
            attrs,
            R.styleable.HorizontalMovieList, 0, 0
        )
        titleText = a.getString(R.styleable.HorizontalMovieList_titleText)
        lastPage = a.getInt(R.styleable.HorizontalMovieList_lastPage, 5)
        pageSize = a.getInt(R.styleable.HorizontalMovieList_pageSize, -1)
        // If pageSize is not defined the view wont load properly unless lastPage is set to 1
        if (pageSize == -1) {
            lastPage = 1
        }

        val view = inflate(context, R.layout.component_horizontal_movie_list, this)
        val set = ConstraintSet()
        set.clone(this)

        layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        //defino o titulo
        movieListTitleTextView = findViewById(R.id.movieListTitleTv)
        movieListTitleTextView.text = titleText

        //defino o adapter
        movieListRecyclerView = findViewById(R.id.movieListRv)
        movieListRecyclerView.layoutManager = layoutManager
        movieListRecyclerView.adapter = adapter


        a.recycle()

    }

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
        itemList.add(movieListResultObject)
        adapter.notifyDataSetChanged()
    }

    fun addItemList(movieListResultObjectList: List<MovieListResultObject>) {
        itemList.addAll(movieListResultObjectList)
        post { adapter.notifyDataSetChanged() }

    }

    fun replaceItemList(movieListResultObjectList: List<MovieListResultObject>) {
        clearItemList()
        itemList.addAll(movieListResultObjectList)
        adapter.notifyDataSetChanged()
    }

    fun clearItemList() {
        itemList.clear()
    }

    override fun loadPage(page: Int) {
        onLoadNextPage?.let {
            it.onLoadNextPage(page)
        }
    }

    interface OnLoadNextPage {
        fun onLoadNextPage(page: Int)
    }
}