package com.example.globoplay_desafio_mobile.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.interfaces.OnMovieListener;
import com.example.globoplay_desafio_mobile.interfaces.OnScrollListener;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GridViewAdapter extends BaseAdapter implements Filterable {

    public List<MovieModel> movies =  new ArrayList<>();
    private OnMovieListener onMovieListener;
    private OnScrollListener onScrollListener;
    public static ArrayList<MovieModel> allMovies =  new ArrayList<>();

    public GridViewAdapter(OnMovieListener onMovieListener, OnScrollListener onScrollListener){
        this.onMovieListener = onMovieListener;
        this.onScrollListener = onScrollListener;
    }

    public GridViewAdapter(OnMovieListener onMovieListener){
        this.onMovieListener = onMovieListener;
    }

    @Override
    public int getCount() {
        return (movies != null ? movies.size() : 0);
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position){return position;}


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

            convertView = layoutInflater.inflate(R.layout.movie_list_item, null);
        }

        ImageView image = convertView.findViewById(R.id.image);

        Glide.with(image).load("https://image.tmdb.org/t/p/w500/" + movies.get(position).getPoster_path())
                    .placeholder(R.drawable.movie).into(image);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onMovieListener.onMovieClick(movies.get(position));
                }
            });


        return convertView;
    }

    @Override
    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {

        @Override
        protected FilterResults performFiltering(CharSequence newText) {

            List<MovieModel> filteredMovies = new ArrayList<>();

            if (newText == null || newText.length() == 0) {
                filteredMovies.addAll(GridViewAdapter.allMovies);
                onScrollListener.setLastPageScroll(false);
            } else {
                for (MovieModel m : GridViewAdapter.allMovies) {
                    if (m.getOriginal_title().toLowerCase().contains(newText.toString().toLowerCase())) {
                        filteredMovies.add(m);
                    }
                }
                onScrollListener.setLastPageScroll(true);
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredMovies;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            movies.clear();
            movies.addAll((Collection<? extends MovieModel>) filterResults.values);
            notifyDataSetChanged();
        }
    };

}
