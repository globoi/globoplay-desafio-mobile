@file:Suppress("UnusedImports")

package br.com.common.Extensions

import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDeepLinkRequest
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import br.com.common.R


fun NavController.navigateToMovie(fragment: Fragment,movieId: Int) {
    val deeplinkUri = context.getString(R.string.deep_link_details_movie).replace(
        oldValue = "{${context.getString(R.string.argument_movie_id)}}",
        newValue = movieId.toString(),
    ).toUri()
    val request = NavDeepLinkRequest.Builder
        .fromUri(deeplinkUri)
        .build()

    findNavController(fragment).navigate(request)
}