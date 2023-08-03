package com.reisdeveloper.globoplay.ui.components

import android.content.Context
import android.util.AttributeSet
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.children
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.reisdeveloper.globoplay.R

class ShimmerLoadingView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : ConstraintLayout(context, attrs, defStyleAttr), LifecycleOwner {

    private val lifecycleRegistry: LifecycleRegistry by lazy {
        LifecycleRegistry(this)
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry

    @LayoutRes
    private var layout: Int = R.layout.shimmer_loading
        set(value) {
            field = value
            onInitViews()
        }

    init {
        onInitViews()
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onDetachedFromWindow() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        super.onDetachedFromWindow()
    }

    private fun onInitViews() {
        clearCardViews()
        with(this) {
            isClickable = true
            isFocusable = true
            this. setBackgroundColor(ContextCompat.getColor(context, android.R.color.white))
            val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
            addView(inflate(context, layout, null), layoutParams)
        }
    }

    private fun clearCardViews() {
        (this as? ViewGroup)?.removeAllViews()
    }

    companion object {

        fun show(parent: ViewGroup, loadingLayout: Int) {
            if (parent.children.any { it is ShimmerLoadingView }.not()) {
                val loadingView = ShimmerLoadingView(parent.context)
                loadingView.layout = loadingLayout
                val layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
                parent.addView(loadingView, layoutParams)
            }
        }

        fun hide(parent: ViewGroup) {
            parent.children.firstOrNull {
                it is ShimmerLoadingView
            }?.also {
                parent.removeView(it)
            }
        }
    }

}