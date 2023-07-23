package com.nunkison.globoplaymobilechallenge

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.nunkison.globoplaymobilechallenge.ui.MainFragment

/**
 * Loads [MainFragment].
 */
class MainActivity : FragmentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, MainFragment())
                .commitNow()
        }
    }
}