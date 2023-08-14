package br.com.common.Extensions

import android.widget.Toolbar
import com.google.android.material.appbar.MaterialToolbar

object ToolbarUtil {
    fun initToolbar(toolbar: MaterialToolbar, menuId: Int, title: String) {
        with(toolbar) {
            inflateMenu(menuId)
            this.title = title
        }
    }
}