package com.reisdeveloper.globoplay.extensions

import android.os.Bundle
import androidx.navigation.NavController

fun NavController.safeNavigate(direction: Int, bundle: Bundle? = null) {
    currentDestination?.getAction(direction)?.run {
        navigate(direction, bundle)
    }
}