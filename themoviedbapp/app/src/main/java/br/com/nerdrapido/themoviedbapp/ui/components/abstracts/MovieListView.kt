package br.com.nerdrapido.themoviedbapp.ui.components.abstracts

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.TextView
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject

/**
 * Created By FELIPE GUSBERTI @ 14/03/2020
 */
abstract class MovieListView<T : MovieListViewHolder> @JvmOverloads constructor(
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

    private var onNextPageNeeded: OnNextPageNeeded? = null

    protected abstract var layoutManager: LinearLayoutManager

    protected val itemList = mutableListOf<MovieListResultObject>()

    protected abstract val adapter: MovieListAdapter<T>

    protected var movieListTitleTextView: TextView? = null

    protected abstract var movieListRecyclerView: RecyclerView?

    private var movieListOnScrollListener: MovieListOnScrollListener? = null

    abstract fun inflateLayout()

    fun setOnPageChangeListener(
        lastPage: Int,
        pageSize: Int,
        onNextPageNeeded: OnNextPageNeeded
    ) {
        movieListOnScrollListener?.let { movieListRecyclerView?.removeOnScrollListener(it) }
        val movieListOnScrollListenerTemp = object : MovieListOnScrollListener(
            pageSize,
            lastPage,
            layoutManager,
            this
        ) {}
        movieListRecyclerView?.addOnScrollListener(movieListOnScrollListenerTemp)
        movieListOnScrollListener = movieListOnScrollListenerTemp
        this.onNextPageNeeded = onNextPageNeeded
    }

    /**
     * Adds a single item to the View.
     *
     * [movieListResultObject] is the item to be added.
     */
    fun addItem(movieListResultObject: MovieListResultObject) {
        val oldItemCount = itemList.size
        itemList.add(movieListResultObject)
        post { adapter.notifyItemInserted(oldItemCount) }
    }

    /**
     * Adds a list of items to the View.
     *
     * [movieListResultObjectList] is the item list to be added.
     */
    fun addItemList(movieListResultObjectList: List<MovieListResultObject>) {
        val oldItemCount = itemList.size
        if (oldItemCount == 0) {
            replaceItemList(movieListResultObjectList)
            return
        }
        itemList.addAll(movieListResultObjectList)
        post { adapter.notifyItemRangeInserted(oldItemCount + 1, movieListResultObjectList.size) }

    }

    /**
     * Replaces the item list and reload the data set.
     *
     * [movieListResultObjectList] is the new item list.
     */
    fun replaceItemList(movieListResultObjectList: List<MovieListResultObject>) {
        itemList.clear()
        itemList.addAll(movieListResultObjectList)
        post { adapter.notifyDataSetChanged() }
    }

    /**
     * Makes the item list empty.
     */
    fun clearItemList() {
        itemList.clear()
        post { adapter.notifyDataSetChanged() }
    }

    override fun loadPage(page: Int) {
        onNextPageNeeded?.onNextPageNeeded(page)
    }

    interface OnNextPageNeeded {
        fun onNextPageNeeded(page: Int)
    }
}