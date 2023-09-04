package com.tiagopereira.globotmdb.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tiagopereira.globotmdb.R
import com.tiagopereira.globotmdb.application.MyApplication
import com.tiagopereira.globotmdb.databinding.ActivityFavoriteMovieBinding
import com.tiagopereira.globotmdb.ui.adapter.MovieDaoAdapter
import com.tiagopereira.globotmdb.utils.Constants.Companion.ID_MOVIE
import com.tiagopereira.globotmdb.viewmodel.FavoritesViewModel
import com.tiagopereira.globotmdb.viewmodel.factory.FavoritesViewModelFactory

class FavoriteMoviesActivity: AppCompatActivity() {

    private lateinit var _binding: ActivityFavoriteMovieBinding
    private val binding get() = _binding
    private lateinit var mAdapter: MovieDaoAdapter
    private val viewModel: FavoritesViewModel by viewModels {
        FavoritesViewModelFactory((application as MyApplication).favoritesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityFavoriteMovieBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onStart() {
        super.onStart()

        setupRecyclerView()

        viewModel.countFavorites()

        binding.prgBarMovies.visibility = View.VISIBLE
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                setResult(Activity.RESULT_CANCELED)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.movies.observe(this) {
            binding.prgBarMovies.visibility = View.GONE
            binding.txtNotFavorites.visibility = View.GONE
            binding.rcvFavorites.visibility = View.VISIBLE

            it.let {
                mAdapter.submitData(this.lifecycle, it)
            }
        }

        viewModel.showMessage.observe(this) {
            binding.txtNotFavorites.visibility = if (it) View.VISIBLE else View.GONE
        }
    }

    private fun setupRecyclerView() {
        mAdapter = MovieDaoAdapter {
            goToDetailsMovieDetails(it.id)
        }
        binding.rcvFavorites.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            adapter = mAdapter
            setHasFixedSize(true)
        }
    }

    private fun goToDetailsMovieDetails(id: Int) {
        val intent = Intent(this, DetailsMovieActivity::class.java)
        intent.putExtra(ID_MOVIE, id)
        startActivity(intent)
    }
}