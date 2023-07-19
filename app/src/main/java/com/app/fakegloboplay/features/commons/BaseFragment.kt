package com.app.fakegloboplay.features.commons

import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    open fun showLoading() {}
    open fun hideLoading() {}
}