package me.davidpcosta.tmdb.ui.highlight

import android.content.Intent
import android.os.Bundle
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
import me.davidpcosta.tmdb.adapters.MovieAdapter
import me.davidpcosta.tmdb.data.model.Cast
import me.davidpcosta.tmdb.data.model.Movie

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        highlightViewModel = ViewModelProvider(this, HighlightViewModelFactory()).get(HighlightViewModel::class.java)
    }

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View

        when (sectionNumber) {
            SECTION_SIMILAR -> {
                view = inflater.inflate(R.layout.activity_highlight_fragment_similar, container, false)
                val movieAdapter = MovieAdapter(requireActivity().applicationContext)
                view.findViewById<GridView>(R.id.similar_movies).apply {
                    adapter = movieAdapter
                    onItemClickListener =  AdapterView.OnItemClickListener { parent, view, position, id ->
                        val movie = highlightViewModel.similarMovies.value!![position]
                        val intent = Intent(requireActivity(), HighlightActivity::class.java)
                        intent.putExtra("movie", movie)
                        requireActivity().startActivity(intent)
                    }
                }
                highlightViewModel.fetchSimilarMovies(movie.id)
                highlightViewModel.similarMovies.observe(viewLifecycleOwner, Observer<List<Movie>> {
                    movieAdapter.movies = it
                    movieAdapter.notifyDataSetChanged()
                })
            }
            SECTION_DETAILS -> {
                view = inflater.inflate(R.layout.activity_highlight_fragment_details, container, false)

                val originalTitle = view.findViewById<TextView>(R.id.original_title)
                val originalTitleHtml = "<b>Título original:</b> " + movie.originalTitle
                originalTitle.text = HtmlCompat.fromHtml(originalTitleHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)

                val genres = view.findViewById<TextView>(R.id.genres)
                val genresHtml = "<b>Generos:</b> " + movie.genreIds.joinToString(", ")
                genres.text = HtmlCompat.fromHtml(genresHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)

                val releaseDate = view.findViewById<TextView>(R.id.release_date)
                val releaseDateLanguageHtml = "<b>Data de lançamento:</b> " + movie.releaseDate.toString()
                releaseDate.text =HtmlCompat.fromHtml(releaseDateLanguageHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)

                val originalLanguage = view.findViewById<TextView>(R.id.original_language)
                val originalLanguageHtml = "<b>Idioma original:</b> " + movie.originalLanguage
                originalLanguage.text =HtmlCompat.fromHtml(originalLanguageHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)

                val averageRate = view.findViewById<TextView>(R.id.average_rate)
                val averageRateHtml = "<b>Nota:</b> " +movie.voteAbarege.toString()
                averageRate.text = HtmlCompat.fromHtml(averageRateHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)

                val cast = view.findViewById<TextView>(R.id.cast)

                highlightViewModel.fetchCredits(movie.id)
                highlightViewModel.cast.observe(viewLifecycleOwner, Observer<List<Cast>> {
                    val castHtml = "<b>Elenco:</b> " + castToStringList(it)
                    cast.text = HtmlCompat.fromHtml(castHtml, HtmlCompat.FROM_HTML_MODE_LEGACY)
                })
            }
            else -> {
                view = inflater.inflate(R.layout.activity_highlight_fragment_details, container, false)
                R.layout.activity_highlight_fragment_details
            }
        }

        return view
    }

    private fun castToStringList(cast: List<Cast>?): String {
        val maxCast = 15;
        cast?.let { cast ->
            val newMaxCast = if (cast.size < maxCast) cast.size else maxCast
            return cast.subList(0, newMaxCast).joinToString {
                it.name
            }
        }
        return ""
    }
}