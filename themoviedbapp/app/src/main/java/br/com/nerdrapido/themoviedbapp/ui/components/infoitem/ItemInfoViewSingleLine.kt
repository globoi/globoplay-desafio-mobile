package br.com.nerdrapido.themoviedbapp.ui.components.infoitem

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintSet
import br.com.nerdrapido.themoviedbapp.R

/**
 * Created By FELIPE GUSBERTI @ 16/03/2020
 */
class ItemInfoViewSingleLine@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : ItemInfoView(context, attrs, defStyleAttr) {

    override val layoutId = R.layout.view_item_info

    init {
        inflate(context, layoutId, this)
        val set = ConstraintSet()
        set.clone(this)
    }
}