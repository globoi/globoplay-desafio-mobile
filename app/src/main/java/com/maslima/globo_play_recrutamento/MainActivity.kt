package com.maslima.globo_play_recrutamento

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.maslima.globo_play_recrutamento.screen.DetailsScreen
import com.maslima.globo_play_recrutamento.screen.HomeScreen
import com.maslima.globo_play_recrutamento.screen.MyListScreen
import com.maslima.globo_play_recrutamento.screen.SplashScreen
import com.maslima.globo_play_recrutamento.ui.theme.GloboplayrecrutamentoTheme
import com.maslima.globo_play_recrutamento.utils.Screen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Screen.SplashScreen.route) {
                composable(Screen.SplashScreen.route) {
                    BaseComposableComponent {
                        SplashScreen(
                            navController = navController
                        )
                    }
                }
                composable(Screen.Home.route) {
                    BaseComposableComponent {
                        HomeScreen(
                            navController = navController
                        )
                    }
                }
                composable(Screen.MyList.route) {
                    BaseComposableComponent {
                        MyListScreen(
                            navController = navController
                        )
                    }
                }
                composable(Screen.Details.route) {
                    BaseComposableComponent {
                        DetailsScreen(
                            navController = navController
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun BaseComposableComponent(content: @Composable() () -> Unit) {
    GloboplayrecrutamentoTheme {
        content()
    }
}