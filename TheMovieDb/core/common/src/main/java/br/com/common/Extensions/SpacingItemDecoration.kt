package br.com.common.Extensions

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SpacingItemDecoration(
    private val spacingPx: Int,
    private val includeEdge: Boolean = false,
) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        when (parent.layoutManager) {
            is GridLayoutManager -> handleSpacingForGridLayoutManager(
                outRect = outRect,
                view = view,
                parent = parent,
            )
            is LinearLayoutManager -> handleSpacingForLinearLayoutManager(
                outRect = outRect,
                view = view,
                parent = parent,
                state = state
            )
        }
    }

    private fun handleSpacingForLinearLayoutManager(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        if (parent.layoutManager !is LinearLayoutManager) {
            return
        }
        val layoutManager = parent.layoutManager as LinearLayoutManager
        val position = parent.getChildAdapterPosition(view)
        if (layoutManager.canScrollVertically()) {
            if (includeEdge) {
                outRect.top = if (position == 0) spacingPx else 0
                outRect.bottom = spacingPx
            } else {
                outRect.bottom = if (position != state.itemCount - 1) spacingPx else 0
            }
        } else {
            if (includeEdge) {
                outRect.left = if (position == 0) spacingPx else 0
                outRect.right = spacingPx
            } else {
                outRect.right = if (position != state.itemCount - 1) spacingPx else 0
            }
        }
    }

    private fun handleSpacingForGridLayoutManager(
        parent: RecyclerView,
        view: View,
        outRect: Rect
    ) {
        if (parent.layoutManager !is GridLayoutManager) {
            return
        }
        val spanCount = (parent.layoutManager as GridLayoutManager).spanCount
        val position = parent.getChildAdapterPosition(view)
        val column = position % spanCount
        if (includeEdge) {
            outRect.left = spacingPx - column * spacingPx / spanCount
            outRect.right = (column + 1) * spacingPx / spanCount
            if (position < spanCount) {
                outRect.top = spacingPx
            }
            outRect.bottom = spacingPx
        } else {
            outRect.left = column * spacingPx / spanCount
            outRect.right = spacingPx - (column + 1) * spacingPx / spanCount
            if (position >= spanCount) {
                outRect.top = spacingPx
            }
        }
    }
}
