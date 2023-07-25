package com.nunkison.globoplaymobilechallenge

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nunkison.globoplaymobilechallenge.project.structure.MovieDetailViewModel
import com.nunkison.globoplaymobilechallenge.ui.VideoDetailsFragment
import com.nunkison.globoplaymobilechallenge.viewmodel.MovieDetailViewModelImpl
import kotlinx.coroutines.launch
import org.koin.android.ext.android.get
import org.koin.core.parameter.parametersOf

class DetailsActivity : FragmentActivity() {

    private lateinit var vm: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        vm = get<MovieDetailViewModelImpl> {
            val id = (intent?.getSerializableExtra(MOVIE) as Movie?)?.id?.toString()
            parametersOf(id)
        }

        val fragment = VideoDetailsFragment()
        fragment.onFragmentReady = {
            lifecycleScope.launch {
                lifecycle.repeatOnLifecycle(Lifecycle.State.RESUMED) {
                    vm.uiState.collect {
                        when (it) {
                            is MovieDetailViewModel.UiState.Success -> {
                                it.data?.let { mdd ->
                                    fragment.updateMovie(mdd.toMovie())
                                    fragment.setupRelatedMovieListRow(
                                        mdd.relatedMovies
                                    )
                                }
                            }

                            else -> {
                                val intent =
                                    Intent(
                                        this@DetailsActivity,
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
                .replace(R.id.details_fragment, fragment)
                .commitNow()
        }
    }

    companion object {
        const val SHARED_ELEMENT_NAME = "hero"
        const val MOVIE = "Movie"
    }
}