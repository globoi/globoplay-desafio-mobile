package com.nroncari.movieplay.utils

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import com.nroncari.movieplay.R

const val themeId = R.style.Theme_Movieplay

inline fun <reified F : Fragment> launchFragment(
    fragmentArgs: Bundle? = null,
    initialState: Lifecycle.State = Lifecycle.State.RESUMED,
    themeResId: Int = themeId
) =
    launchFragmentInContainer<F>(
        themeResId = themeResId,
        fragmentArgs = fragmentArgs,
        initialState = initialState
    )