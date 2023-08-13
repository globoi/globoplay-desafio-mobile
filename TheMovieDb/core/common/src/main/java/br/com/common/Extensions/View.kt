package br.com.common.Extensions

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.core.content.ContextCompat

fun Context.showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, duration).show()
}

fun ImageButton.setFaveImageAndColor(drawableResId: Int, colorResId: Int?) {
    setImageResource(drawableResId)
    if(colorResId != null){
        colorResId.let {color ->
            imageTintList = ColorStateList.valueOf(ContextCompat.getColor(context, color))
        }
    } else {
        imageTintList = null
    }
}

fun View.hide() {
    visibility = View.GONE
}

fun View.show() {
    visibility = View.VISIBLE
}