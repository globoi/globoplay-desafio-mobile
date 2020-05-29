package me.davidpcosta.tmdb.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import me.davidpcosta.tmdb.R
import me.davidpcosta.tmdb.data.model.Genre

class GenreAdapter(applicationContext: Context): RecyclerView.Adapter<GenreAdapter.ViewHolder>() {


    private var inflater: LayoutInflater = LayoutInflater.from(applicationContext)
    var genres: List<Genre> = ArrayList()

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val genre = view.findViewById(R.id.genre) as TextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.genre_item, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return genres.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.genre.text = genres[position].name
    }

//    override fun getCount(): Int {
//        return genres.size
//    }
//
//    override fun getItem(i: Int): Genre {
//        return genres[i]
//    }
//
//    override fun getItemId(i: Int): Long {
//        return genres[i].id
//    }
//
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {
//        val view: View?
//        val vh: ViewHolder
//        val genge = getItem(position)
//
//        if (convertView == null) {
//            view = inflater.inflate(R.layout.genre_item, parent, false)
//            vh = ViewHolder(view)
//            view.tag = vh
//        } else {
//            view = convertView
//            vh = view.tag as ViewHolder
//        }
//
//        vh.genre.text = genge.name
//
//        return view
//    }
}

