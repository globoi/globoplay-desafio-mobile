package com.mazer.globoplayapp.presentation.custom_view

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import com.mazer.globoplayapp.R
import com.mazer.globoplayapp.databinding.LayoutCarouselBinding

/**
 * Custom View que contém um texto para o Título/Genro e uma lista de posteres de filmes
 */
class CarouselView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private lateinit var typedArray: TypedArray
    private lateinit var binding: LayoutCarouselBinding

    init {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet? = null) {
        try {
            binding = LayoutCarouselBinding.inflate(LayoutInflater.from(context), this, true)
            typedArray = context.obtainStyledAttributes(attrs, R.styleable.CarouselView)

            val title = typedArray.getString(R.styleable.CarouselView_title)
            binding.tvCarouselTitle.text = title
        } catch (e: Exception) {
            throw Exception()
        }
        requestLayout()
    }

}