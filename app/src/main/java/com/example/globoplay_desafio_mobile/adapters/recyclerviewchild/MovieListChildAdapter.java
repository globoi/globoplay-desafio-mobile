package com.example.globoplay_desafio_mobile.adapters.recyclerviewchild;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.interfaces.OnMovieListener;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;

import java.util.List;

public class MovieListChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<MovieModel> movies;
    private OnMovieListener onMovieListener;
    ProgressBar progressBar;

    public MovieListChildAdapter(OnMovieListener onMovieListener, ProgressBar progressBar) {
        this.onMovieListener = onMovieListener;
        this.progressBar = progressBar;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.movie_list_item, parent,false);

        return new MovieListChildViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        progressBar.setVisibility(View.INVISIBLE);

        Glide.with(((MovieListChildViewHolder)holder).image).load("https://image.tmdb.org/t/p/w500/" +
                 movies.get(position).getPoster_path()).placeholder(R.drawable.movie).into(((MovieListChildViewHolder)holder).image);

        //image click
        ((MovieListChildViewHolder)holder).image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMovieListener.onMovieClick(movies.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return (movies != null ? movies.size() : 0);
    }

}
