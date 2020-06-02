package me.davidpcosta.tmdb.ui.highlight

import android.content.Intent
import android.os.Bundle
import android.text.Spanned
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.ui.main.watchlist.MovieAdapter
import me.davidpcosta.tmdb.data.model.Cast
import me.davidpcosta.tmdb.data.model.Genre
import me.davidpcosta.tmdb.data.model.Movie
import me.davidpcosta.tmdb.data.model.MovieDetails
import me.davidpcosta.tmdb.toLongFormatString

class PlaceholderFragment : Fragment() {

    companion object {
        private const val SECTION_SIMILAR = 1
        private const val SECTION_DETAILS = 2

        fun newInstance(highlightedMovie: Movie, position: Int): PlaceholderFragment {
            return PlaceholderFragment().apply {
                movie = highlightedMovie
                sectionNumber = position
            }
        }
    }

    var sectionNumber: Int = -1
    lateinit var movie: Movie
    private lateinit var highlightViewModel: HighlightViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        highlightViewModel = ViewModelProvider(this, HighlightViewModelFactory(requireActivity())).get(HighlightViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View

        when (sectionNumber) {
            SECTION_SIMILAR -> {
                view = inflater.inflate(R.layout.activity_highlight_fragment_similar, container, false)
                movieAdapter =
                    MovieAdapter(
                        requireActivity().applicationContext
                    )
                view.findViewById<GridView>(R.id.similar_movies).apply {
                    adapter = movieAdapter
                    onItemClickListener =  AdapterView.OnItemClickListener { _, _, position, _ ->
                        goToMovie(highlightViewModel.similarMovies.value!![position])
                    }
                }
                fetchSimilarMovies()
            }
            SECTION_DETAILS -> {
                view = inflater.inflate(R.layout.activity_highlight_fragment_details, container, false)
                fetchMovieDetails()
                fetchMovieCast()
            }
            else -> {
                throw Error("View not supported")
            }
        }

        return view
    }

    private fun fetchMovieCast() {
        highlightViewModel.fetchCredits(movie.id)
        highlightViewModel.cast.observe(viewLifecycleOwner, Observer<List<Cast>> {
            view?.let {view ->
                val cast = view.findViewById<TextView>(R.id.cast)
                cast.text = formatHtmlTextView("Elenco", castToStringList(it))
            }
        })
    }

    private fun fetchMovieDetails() {
        highlightViewModel.movieDetails(movie.id)
        highlightViewModel.movieDetails.observe(viewLifecycleOwner, Observer<MovieDetails> {
            view?.let {view ->
                val originalTitle = view.findViewById<TextView>(R.id.original_title)
                originalTitle.text = formatHtmlTextView("Título original", it.originalTitle)

                val genres = view.findViewById<TextView>(R.id.genres)
                genres.text = formatHtmlTextView("Generos", genresToStringList(it.genres))

                val releaseDate = view.findViewById<TextView>(R.id.release_date)
                releaseDate.text = formatHtmlTextView("Data de lançamento", it.releaseDate.toLongFormatString())

                val originalLanguage = view.findViewById<TextView>(R.id.original_language)
                originalLanguage.text = formatHtmlTextView("Idioma original", it.originalLanguage)

                val averageRate = view.findViewById<TextView>(R.id.average_rate)
                averageRate.text = formatHtmlTextView("Nota", it.voteAbarege.toString())
            }
        })
    }

    private fun fetchSimilarMovies() {
        highlightViewModel.fetchSimilarMovies(movie.id)
        highlightViewModel.similarMovies.observe(viewLifecycleOwner, Observer<List<Movie>> {
            movieAdapter.movies = it
            movieAdapter.notifyDataSetChanged()
        })
    }

    private fun goToMovie(movie: Movie) {
        val intent = Intent(requireActivity(), HighlightActivity::class.java)
        intent.putExtra("movie", movie)
        requireActivity().startActivity(intent)
    }

    private fun castToStringList(castLis: List<Cast>?): String {
        val maxCast = 15
        castLis?.let { cast ->
            val newMaxCast = if (cast.size < maxCast) cast.size else maxCast
            return cast.subList(0, newMaxCast).joinToString {
                it.name
            }
        }
        return ""
    }

    private fun genresToStringList(genres: List<Genre>?): String {
        genres?.let { genre ->
            return genre.joinToString {
                it.name
            }
        }
        return ""
    }

    private fun formatHtmlTextView(label: String, value: String): Spanned {
        return HtmlCompat.fromHtml("<b>$label:</b> $value", HtmlCompat.FROM_HTML_MODE_LEGACY)

    }
}