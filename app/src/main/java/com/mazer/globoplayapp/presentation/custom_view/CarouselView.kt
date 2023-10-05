package com.mazer.globoplayapp.presentation.custom_view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.LayoutCarouselBinding
import com.mazer.globoplayapp.presentation.adapter.CarouselMoviesAdapter
import com.mazer.globoplayapp.presentation.adapter.decorator.CarouselDecoration
import com.mazer.globoplayapp.presentation.adapter.pagination.PaginationScrollListener

/**
 * Custom View que contém um texto para o Título/Genro e uma lista de posteres de filmes
 */
class CarouselView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var typedArray: TypedArray
    private lateinit var binding: LayoutCarouselBinding
    private var onLoadMoreListener: ((Int) -> Unit)? = null

    private val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    private val paginationScrollListener = object : PaginationScrollListener(layoutManager) {
        override fun loadMoreItems(currentPage: Int) {
            onLoadMoreListener?.invoke(currentPage)
        }
    }

    fun setOnLoadMoreListener(listener: (Int) -> Unit) {
        onLoadMoreListener = listener
    }
    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {
        try {
            binding = LayoutCarouselBinding.inflate(LayoutInflater.from(context), this, true)
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CarouselView)

            setupLayoutRecyclerView()

            val title = typedArray.getString(R.styleable.CarouselView_title)
            binding.tvCarouselTitle.text = title
        } catch (e: Exception) {
            throw Exception()
        }
        requestLayout()
    }

    private fun setupLayoutRecyclerView() {
        binding.rvMoviesList.layoutManager = layoutManager

        val verticalSpaceHeight = resources.getDimensionPixelSize(R.dimen.carousel_space_height)
        val verticalSpaceItemDecoration = CarouselDecoration(verticalSpaceHeight)
        binding.rvMoviesList.addItemDecoration(verticalSpaceItemDecoration)
        binding.rvMoviesList.addOnScrollListener(paginationScrollListener)
    }

    fun setAdapter(adapter: CarouselMoviesAdapter) {
        binding.rvMoviesList.adapter = adapter
    }

    fun setDataLoaded(){
        paginationScrollListener.setLoaded()
    }

}