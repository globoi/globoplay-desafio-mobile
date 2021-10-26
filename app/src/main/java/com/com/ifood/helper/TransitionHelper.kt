package com.com.ifood.helper

import android.app.Activity
import android.content.Intent
import android.transition.TransitionInflater
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.core.util.Pair
import com.com.ifood.R

object TransitionHelper {

    @JvmStatic
    fun enableTransition(activity: Activity) {
        activity
            .window
            .sharedElementExitTransition = TransitionInflater
            .from(activity)
            .inflateTransition(R.transition.transitions)
    }

    @JvmStatic
    fun startActivityWithTransition(
        activity: Activity,
        it: Intent,
        vararg pair: Pair<View, String>
    ) {
        activity.startActivity(
            it, ActivityOptionsCompat.makeSceneTransitionAnimation(
                activity,
                *pair
            ).toBundle()
        )
    }


}
