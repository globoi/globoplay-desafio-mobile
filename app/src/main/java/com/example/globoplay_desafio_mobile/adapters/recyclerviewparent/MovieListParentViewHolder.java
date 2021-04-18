package com.example.globoplay_desafio_mobile.adapters.recyclerviewparent;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.interfaces.OnBtnMoreListener;

public class MovieListParentViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public Button btnMore;
    public RecyclerView rv_child;
    public ProgressBar progressBar;

    public MovieListParentViewHolder(@NonNull View view) {
        super(view);

        title = view.findViewById(R.id.title);
        btnMore = view.findViewById(R.id.btn_more);
        rv_child = view.findViewById(R.id.rv_child);
        progressBar = view.findViewById(R.id.progressBar_app);
    }

}
