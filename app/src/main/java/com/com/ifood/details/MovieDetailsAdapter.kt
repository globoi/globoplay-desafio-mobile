package com.com.ifood.details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.com.ifood.R
import com.com.ifood.helper.createSpannableString

class MovieDetailsAdapter(
    private val data: MutableMap<Int, String>
) : RecyclerView.Adapter<MovieDetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieDetailsViewHolder {
        return MovieDetailsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.view_movie_details, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MovieDetailsViewHolder, position: Int) {
        val tag = data.keys.elementAt(position)
        holder.bind(tag, data[tag]!!)
    }
}

class MovieDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textViewTag = itemView.findViewById<TextView>(R.id.textview_tag)

    fun bind(stringTag: Int, valueTag: String) {
        textViewTag.setText(
            createSpannableString(
                itemView.context,
                Pair(itemView.context.getString(stringTag), R.style.style_tag),
                Pair(valueTag, R.style.style_value_tag)
            ),
            TextView.BufferType.SPANNABLE
        )
    }
}