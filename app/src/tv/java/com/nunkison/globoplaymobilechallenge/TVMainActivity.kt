package com.nunkison.globoplaymobilechallenge

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nunkison.globoplaymobilechallenge.project.structure.MoviesViewModel
import com.nunkison.globoplaymobilechallenge.ui.MainFragment
import com.nunkison.globoplaymobilechallenge.viewmodel.MoviesViewModelImpl
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class TVMainActivity : FragmentActivity() {

    private val vm by viewModel<MoviesViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val mainFragment = MainFragment()
        mainFragment.onFragmentReady = {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    vm.uiState.collect {
                        when (it) {
                            is MoviesViewModel.UiState.Success -> {
                                mainFragment.loadRows(it.successState.data)
                            }
                            else -> {
                                val intent = Intent(
                                    this@TVMainActivity,
                                    BrowseErrorActivity::class.java
                                )
                                startActivity(intent)
                            }
                        }

                    }
                }
            }
        }
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_browse_fragment, mainFragment)
                .commitNow()
        }
    }

}