package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentPagerAdapter
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment.InfoMovieDetailFragment
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment.MovieDetailPageAdapter
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment.RelatedMovieDetailFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.ext.android.inject
import timber.log.Timber

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MovieDetailActivity : AbstractActivity<MovieDetailView, MovieDetailPresenter>(),
    MovieDetailView {

    companion object {
        const val MOVIE_OBJECT_RESULT = "MOVIE_OBJECT_RESULT"
    }

    override val presenter: MovieDetailPresenter by inject()

    override val layoutId = R.layout.activity_movie_detail

    private var movieTitle = ""

    private lateinit var movieListResultObject: MovieListResultObject

    private val relatedMovieDetailFragment = RelatedMovieDetailFragment()

    private val infoMovieDetailFragment =  InfoMovieDetailFragment()

    override fun getActivityTitle(): String {
        return movieTitle
    }

    override fun movieLoaded(movieResponse: MovieResponse) {
        infoMovieDetailFragment.movieResponse = movieResponse
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        val extras = intent.extras
        if (extras != null) {
            val movieListResultObjectString = extras.getString(MOVIE_OBJECT_RESULT)
            val movieListResultObjectTemp = Gson().fromJson(
                movieListResultObjectString,
                MovieListResultObject::class.java
            )
            movieListResultObjectTemp?.let { movieListResultObject = movieListResultObjectTemp }
        } else {
            Timber.w("Could not get the MovieListResultObject ending activity")
            this.finish()
        }
        movieTitle = movieListResultObject.title ?: ""

        super.onCreate(savedInstanceState)

        //Load main image
        loadImageIntoView(movieBackgroundIv)


        val fragments = listOf(
            relatedMovieDetailFragment,
            infoMovieDetailFragment
        )

        detailVp.adapter = MovieDetailPageAdapter(
            fragments,
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )

    }

    override fun onResume() {
        super.onResume()
        presenter.setMovie(movieListResultObject)
    }

    private fun loadImageIntoView(imageView: ImageView) {
        var requestOptions = RequestOptions()
        requestOptions = requestOptions.placeholder(R.drawable.poster_progress)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/original" + movieListResultObject.backdropPath)
            .apply(requestOptions).into(imageView)
    }




}