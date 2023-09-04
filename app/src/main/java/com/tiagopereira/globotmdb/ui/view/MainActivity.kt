package com.tiagopereira.globotmdb.ui.view

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.core.content.res.ResourcesCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tiagopereira.globotmdb.R
import com.tiagopereira.globotmdb.application.MyApplication
import com.tiagopereira.globotmdb.databinding.ActivityMainBinding
import com.tiagopereira.globotmdb.ui.adapter.MovieAdapter
import com.tiagopereira.globotmdb.ui.adapter.MovieLoadStateAdapter
import com.tiagopereira.globotmdb.utils.Constants.Companion.ID_MOVIE
import com.tiagopereira.globotmdb.utils.Constants.Companion.NOW_PLAYING
import com.tiagopereira.globotmdb.utils.Constants.Companion.POPULAR
import com.tiagopereira.globotmdb.utils.Constants.Companion.TOP_RATED
import com.tiagopereira.globotmdb.utils.Constants.Companion.UPCOMING
import com.tiagopereira.globotmdb.utils.translateToPortuguese
import com.tiagopereira.globotmdb.viewmodel.MainViewModel
import com.tiagopereira.globotmdb.viewmodel.factory.MainViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityMainBinding
    private val binding get() = _binding
    private lateinit var mAdapter: MovieAdapter
    private var type: String = POPULAR
    private var isPopular: Boolean = true
    private var isTopRated: Boolean = false
    private var isUpcoming: Boolean = false
    private var isNowPlaying: Boolean = false
    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory((application as MyApplication).mainRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_icon_white)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayUseLogoEnabled(true)
        binding.toolbar.title = POPULAR.translateToPortuguese()

        val menuItemIdToSelect: Int = R.id.menu_home
        binding.bottomNavigation.selectedItemId = menuItemIdToSelect
    }

    override fun onNavigateUp(): Boolean {
        setResult(Activity.RESULT_CANCELED)
        finish()
        return super.onNavigateUp()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search, menu)

        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    binding.rcvMovies.scrollToPosition(0)
                    viewModel.searchMovie(query)
                    searchView.clearFocus()
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query.isNullOrEmpty()) {
                    binding.rcvMovies.scrollToPosition(0)
                    viewModel.searchMovie(type)
                    hideKeyboard()
                    searchView.clearFocus()
                }
                return true
            }

        })

        return true
    }

    override fun onResume() {
        super.onResume()

        binding.prgBarNetwork.visibility = View.VISIBLE

        viewModel.checkNetwork(this@MainActivity)

        viewModel.statusNetwork.observe(this) {
            if (it) {
                binding.txtNotNetwork.visibility = View.GONE
                binding.btnRetry.visibility = View.GONE
                binding.prgBarNetwork.visibility = View.GONE
                binding.rcvMovies.visibility = View.VISIBLE

                viewModel.searchMovie(type)
            } else {
                binding.txtNotNetwork.visibility = View.VISIBLE
                binding.btnRetry.visibility = View.VISIBLE
                binding.prgBarNetwork.visibility = View.GONE
                binding.rcvMovies.visibility = View.GONE
            }
        }

        viewModel.movies.observe(this) {
            binding.rcvMovies.visibility = View.VISIBLE
            binding.txtNotNetwork.visibility = View.GONE
            binding.btnRetry.visibility = View.GONE
            binding.prgBarNetwork.visibility = View.GONE

            it.let {
                binding.rcvMovies.scrollToPosition(0)
                mAdapter.submitData(this.lifecycle, it)
            }
        }

        binding.apply {
            rcvMovies.setHasFixedSize(true)
            rcvMovies.adapter = mAdapter.withLoadStateFooter(
                footer = MovieLoadStateAdapter { mAdapter.retry() }
            )
        }
    }

    override fun onStart() {
        super.onStart()

        setupRecyclerView()

        binding.llPopular.setOnClickListener {
            isPopular = !isPopular
            isUpcoming = false
            isTopRated = false
            isNowPlaying = false
            type = POPULAR
            changeColor()

            binding.toolbar.title = POPULAR.translateToPortuguese()
            viewModel.searchMovie(POPULAR)
        }

        binding.llTopRated.setOnClickListener {
            isTopRated = !isTopRated
            isUpcoming = false
            isPopular = false
            isNowPlaying = false
            type = TOP_RATED
            changeColor()

            binding.toolbar.title = TOP_RATED.translateToPortuguese()
            viewModel.searchMovie(TOP_RATED)
        }

        binding.llUpcoming.setOnClickListener {
            isUpcoming = !isUpcoming
            isTopRated = false
            isPopular = false
            isNowPlaying = false
            type = UPCOMING
            changeColor()

            binding.toolbar.title = UPCOMING.translateToPortuguese()
            viewModel.searchMovie(UPCOMING)
        }

        binding.llNowPlaying.setOnClickListener {
            isNowPlaying = !isNowPlaying
            isTopRated = false
            isPopular = false
            isUpcoming = false
            type = NOW_PLAYING
            changeColor()

            binding.toolbar.title = NOW_PLAYING.translateToPortuguese()
            viewModel.searchMovie(NOW_PLAYING)
        }

        binding.btnRetry.setOnClickListener {

            binding.prgBarNetwork.visibility = View.VISIBLE
            binding.btnRetry.visibility = View.GONE
            binding.txtNotNetwork.visibility = View.GONE

            viewModel.checkNetwork(this@MainActivity)
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.menu_star -> {
                    goToFavorites()
                }
            }
            false
        }
    }

    private fun setupRecyclerView() {
        mAdapter = MovieAdapter {
            goToDetailsMovieDetails(it.id)
        }

        binding.rcvMovies.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun changeColor() {
        binding.llPopular.background = ResourcesCompat.getDrawable(resources, if (isPopular) R.drawable.rect_selected else R.drawable.rect_unselect, null)
        binding.llNowPlaying.background = ResourcesCompat.getDrawable(resources, if (isNowPlaying) R.drawable.rect_selected else R.drawable.rect_unselect, null)
        binding.llUpcoming.background = ResourcesCompat.getDrawable(resources, if (isUpcoming) R.drawable.rect_selected else R.drawable.rect_unselect, null)
        binding.llTopRated.background = ResourcesCompat.getDrawable(resources, if (isTopRated) R.drawable.rect_selected else R.drawable.rect_unselect, null)
    }

    private fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun goToDetailsMovieDetails(id: Int) {
        val intent = Intent(this, DetailsMovieActivity::class.java)
        intent.putExtra(ID_MOVIE, id)
        startActivity(intent)
    }

    private fun goToFavorites() {
        val intent = Intent(this, FavoriteMoviesActivity::class.java)
        startActivity(intent)
    }
}