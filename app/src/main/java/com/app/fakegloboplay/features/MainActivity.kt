package com.app.fakegloboplay.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.app.fakegloboplay.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<BottomNavigationView>(R.id.bottomNavigationView).apply {
            setupWithNavController(this@MainActivity.findNavController(R.id.my_nav_host_fragment))
        }
    }
}