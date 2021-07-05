package com.maslima.globo_play_recrutamento.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.imageResource
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.maslima.globo_play_recrutamento.R
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SplashScreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                SplashScreen(findNavController())
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    ScreenContent()
    Thread.sleep(4000)
    navController.navigate(R.id.splashToMovieList)
}

@Composable
fun ScreenContent() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            bitmap = imageResource(id = R.drawable.splash),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}