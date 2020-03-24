package br.com.nerdrapido.themoviedbapp.ui.components.infoitem

import android.content.Context
import android.util.AttributeSet
import androidx.annotation.AttrRes
import androidx.constraintlayout.widget.ConstraintLayout
import br.com.nerdrapido.themoviedbapp.R
import kotlinx.android.synthetic.main.view_item_info.view.*

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
abstract class ItemInfoView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    @AttrRes defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val separator = ": "

    private val infoDefaultValue = context.getString(R.string.nao_informado)

    protected abstract val layoutId: Int

    var title: String? = null
        set(value) {
            field = value
            updateText(title, info)
        }

    var info: String? = null
        set(value) {
            field = value
            updateText(title, info)
        }

    private fun updateText(title: String?, info: String?) {
        titleTv.text = title?.let { title + separator }
        infoTv.text = info ?: infoDefaultValue
    }
}