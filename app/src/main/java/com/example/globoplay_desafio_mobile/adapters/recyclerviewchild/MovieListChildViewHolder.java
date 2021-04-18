package com.example.globoplay_desafio_mobile.adapters.recyclerviewchild;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.globoplay_desafio_mobile.R;

public class MovieListChildViewHolder extends RecyclerView.ViewHolder {

    public ImageView image;

    public MovieListChildViewHolder(@NonNull View view) {
        super(view);

        image = view.findViewById(R.id.image);
    }

}