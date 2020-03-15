package br.com.nerdrapido.themoviedbapp.ui.moviedetail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.nerdrapido.themoviedbapp.R
import br.com.nerdrapido.themoviedbapp.data.model.movie.MovieResponse
import br.com.nerdrapido.themoviedbapp.ui.components.infoitem.ItemInfoView
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar.YEAR

/**
 * Created By FELIPE GUSBERTI @ 13/03/2020
 */
class InfoMovieDetailFragment : MovieDetailFragment() {

    override var title = ""

    var movieResponse: MovieResponse? = null
        set(value) {
            setInfo(value)
            field = value
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInfo(movieResponse)
    }

    private fun setInfo(movieResponse: MovieResponse?) {
        addInfoView("Tíitulo original", movieResponse?.originalTitle)

        // TODO: wrap calendar in function
        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val calendar = Calendar.getInstance()
        calendar.time = format.parse(movieResponse?.releaseDate)
        addInfoView("Data de lançamento", calendar.get(YEAR).toString())
        addInfoView("Sinopse", movieResponse?.overview)
    }

    private fun addInfoView(title: String?, info: String?) {
        context?.let {
            val infoView = ItemInfoView(it)
            infoView.title = title
            infoView.info = info
            detailInfoContainer.addView(infoView)
        }
    }
}