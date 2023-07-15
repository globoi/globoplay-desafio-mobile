package com.nunkison.globoplaymobilechallenge

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.nunkison.globoplaymobilechallenge.ui.theme.GloboplayMobileChallengeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Toast.makeText(this, getString(R.string.api_key), Toast.LENGTH_LONG).show()
        setContent {
            GloboplayMobileChallengeTheme {
                Navigation(navController = rememberNavController())
            }
        }
    }
}