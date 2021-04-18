package com.example.globoplay_desafio_mobile.adapters.recyclerviewparent;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.adapters.recyclerviewchild.MovieListChildAdapter;
import com.example.globoplay_desafio_mobile.interfaces.OnBtnMoreListener;
import com.example.globoplay_desafio_mobile.interfaces.OnMovieListener;
import com.example.globoplay_desafio_mobile.models.movies.GenderModel;
import java.util.List;

public class MovieListParentApdater extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public List<GenderModel> movie_gender_list;
    private Context context;
    private OnMovieListener onMovieListener;
    private OnBtnMoreListener onBtnMoreListener;

    public MovieListParentApdater(Context context, OnMovieListener onMovieListener, OnBtnMoreListener onBtnMoreListener) {
        this.context = context;
        this.onMovieListener = onMovieListener;
        this.onBtnMoreListener=onBtnMoreListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.movie_list_child_item, parent,false);

        return new MovieListParentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        ((MovieListParentViewHolder) holder).title.setText(movie_gender_list.get(position).getName());

        //add horizontal adapter
        ((MovieListParentViewHolder)holder).rv_child.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));

        MovieListChildAdapter childAdapter = new MovieListChildAdapter(onMovieListener,((MovieListParentViewHolder)holder).progressBar);
        childAdapter.movies = movie_gender_list.get(position).getMovies();


        ((MovieListParentViewHolder)holder).progressBar.setVisibility(View.VISIBLE);


        ((MovieListParentViewHolder)holder).rv_child.setAdapter(childAdapter);

        //btn more click
        ((MovieListParentViewHolder)holder).btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnMoreListener.onBtnMoreClick(movie_gender_list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return (movie_gender_list != null ? movie_gender_list.size() : 0);
    }
}

