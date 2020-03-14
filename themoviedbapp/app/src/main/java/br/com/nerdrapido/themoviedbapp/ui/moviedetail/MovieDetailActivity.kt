package br.com.nerdrapido.themoviedbapp.ui.moviedetail

import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.common.MovieListResultObject
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.data.model.recommendation.RecommendationResponse
import br.com.nerdrapido.themoviedbapp.ui.abstracts.AbstractActivity
import br.com.nerdrapido.themoviedbapp.ui.abstracts.navigation.NavigationActivity
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment.InfoMovieDetailFragment
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment.MovieDetailFragment
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment.MovieDetailPageAdapter
import br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment.RelatedMovieDetailFragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_movie_detail.*
import org.koin.android.ext.android.inject
import timber.log.Timber


/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class MovieDetailActivity : NavigationActivity<MovieDetailView, MovieDetailPresenter>(),
    MovieDetailView, RelatedMovieDetailFragment.OnRelatedMovieNewPageLoad {

    override val nestedActivityLayoutId = R.layout.activity_movie_detail

    companion object {
        const val MOVIE_OBJECT_RESULT = "MOVIE_OBJECT_RESULT"
    }

    override val presenter: MovieDetailPresenter by inject()

    private var movieTitle = ""

    private lateinit var movieListResultObject: MovieListResultObject

    private val relatedMovieDetailFragment = RelatedMovieDetailFragment(this)

    private val infoMovieDetailFragment = InfoMovieDetailFragment()

    override fun getActivityTitle(): String {
        return movieTitle
    }

    override fun movieInfoLoaded(movieResponse: MovieResponse) {
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

        // get the fragments
        val fragments = getFragments()
        // initialize fragment pager adapter
        detailVp.adapter = MovieDetailPageAdapter(
            fragments,
            supportFragmentManager,
            FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        )
        // initialize pager tabs
        setTabPagerTabs(tabPager, fragments)
        // initialize listeners
        detailVp.addOnPageChangeListener(onPageChangeListener())
        tabPager.addOnTabSelectedListener(onTabSelectedListener(detailVp))

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

    private fun onTabSelectedListener(pager: ViewPager): OnTabSelectedListener {
        return object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                pager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        }
    }

    private fun onPageChangeListener(): ViewPager.OnPageChangeListener {
        return object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                tabPager.setScrollPosition(position, positionOffset, false)
            }
            override fun onPageSelected(position: Int){}
        }
    }

    private fun getFragments(): List<MovieDetailFragment> {
        // Initialize fragments
        val list = listOf(
            relatedMovieDetailFragment,
            infoMovieDetailFragment
        )
        // Here we set fragment titles
        relatedMovieDetailFragment.title = getString(R.string.movie_detail_related_fragment_title)
        infoMovieDetailFragment.title = getString(R.string.movie_detail_detail_fragment_title)
        return list
    }

    private fun setTabPagerTabs(tabLayout: TabLayout, fragments: List<MovieDetailFragment>) {
        fragments.forEach { fragment ->
            val tab = tabPager.newTab()
            tab.text = fragment.title
            tabLayout.addTab(tab)
        }

    }

    override suspend fun onRelatedMovieNewPageLoad(page: Int): List<MovieListResultObject> {
        return presenter.loadRelatedMoviePage(page)
    }


}