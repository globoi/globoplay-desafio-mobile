package com.example.testeglobojeremias;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.splashscreen.SplashScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.example.testeglobojeremias.adapters.MoviesAdapter;
import com.example.testeglobojeremias.models.MovieEntity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<MovieEntity> movieEntities;

    private RecyclerView moviesRecyclerView;
    private ShimmerFrameLayout shimmerFrameLayout;
    private ExtendedFloatingActionButton failureFab;
    private TextView emptyView;

    private PopularMoviesViewModel popularMoviesViewModel;

    private MoviesAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashscreen;
        splashscreen = SplashScreen.installSplashScreen(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        initViewModels();
        bindViews();

        movieEntities = new ArrayList<>();
        setupRecyclerView();

        handleAPIFailure();
        handleProgress();
        handleDataChange();

        failureFab.setOnClickListener(view -> popularMoviesViewModel.getPopularMovies());
    }

    private void bindViews(){
        moviesRecyclerView = findViewById(R.id.movies_recyclerview);
        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        failureFab = findViewById(R.id.retry_fab);
        emptyView = findViewById(R.id.empty_view);
    }

    private void initViewModels(){
        popularMoviesViewModel = new ViewModelProvider(this).get(PopularMoviesViewModel.class);
    }

    private void setupRecyclerView(){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        moviesRecyclerView.setLayoutManager(gridLayoutManager);

        adapter = new MoviesAdapter(movieEntities, this::goToDetails);

        moviesRecyclerView.setAdapter(adapter);
        manageFabOnScroll();
    }

    private void goToDetails(MovieEntity movie, ImageView imageView){
        Intent intent = new Intent(this, MovieDetailActivity.class);
        intent.putExtra("MOVIE", movie);
        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, imageView, "poster");
        startActivity(intent, options.toBundle());
    }

    private void handleAPIFailure(){
        popularMoviesViewModel.getFailureLiveData().observe(this, failure->{
            if(failure){
                failureFab.setVisibility(View.VISIBLE);
            } else {
                failureFab.setVisibility(View.GONE);
            }
        });
    }

    private void handleProgress(){
        popularMoviesViewModel.getProgressLiveData().observe(this, progress -> {
            if(progress){
                shimmerFrameLayout.startShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.VISIBLE);
                moviesRecyclerView.setVisibility(View.GONE);
            } else {
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);
                moviesRecyclerView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void handleDataChange(){
        popularMoviesViewModel.getMovieEntityLiveData().observe(this, movieEntities -> {
            if(movieEntities!=null){
                this.movieEntities.clear();
                if(!movieEntities.isEmpty()) {
                    this.movieEntities.addAll(movieEntities);
                    emptyView.setVisibility(View.GONE);
                } else {
                    emptyView.setVisibility(View.VISIBLE);
                }
                adapter.notifyDataSetChanged();
            } else {
                emptyView.setVisibility(View.VISIBLE);
            }
        });
    }

    private void manageFabOnScroll(){
        moviesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy!=0 && failureFab.isExtended())
                    failureFab.shrink();
                super.onScrolled(recyclerView, dx, dy);

            }

            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                if(newState == RecyclerView.SCROLL_STATE_IDLE && !failureFab.isExtended() && moviesRecyclerView.computeVerticalScrollOffset()==0)
                    failureFab.extend();
                super.onScrollStateChanged(recyclerView, newState);
            }
        });
    }
}
