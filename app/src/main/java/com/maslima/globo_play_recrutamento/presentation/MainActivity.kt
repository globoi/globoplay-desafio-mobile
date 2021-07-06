package com.maslima.globo_play_recrutamento.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.maslima.globo_play_recrutamento.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}