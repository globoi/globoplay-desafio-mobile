package com.example.testeglobojeremias;

import static com.example.testeglobojeremias.utils.Keys.imageBaseURL;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.example.testeglobojeremias.adapters.ReviewsAdapter;
import com.example.testeglobojeremias.adapters.VideosAdapter;
import com.example.testeglobojeremias.models.MovieEntity;
import com.example.testeglobojeremias.models.Review;
import com.example.testeglobojeremias.models.Video;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

public class MovieDetailActivity extends AppCompatActivity {

    private ArrayList<Review> reviews;
    private ArrayList<Video> videos;
    private static final String TAG = MovieDetailActivity.class.getSimpleName();

    private ImageView backdropHeader;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private TextView titleTv;
    private TextView ratingTv;
    private TextView releaseDateTv;
    private ImageView posterImageView;
    private TextView overviewTv;
    private RecyclerView reviewsRv;
    private ShimmerFrameLayout videoShimmer;
    private ShimmerFrameLayout reviewShimmer;
    private LinearLayout videoRetry;
    private Button videoRetryButton;
    private LinearLayout reviewRetry;
    private Button reviewRetryButton;
    private RecyclerView videosRv;
    private ReviewViewModel reviewViewModel;
    private VideosViewModel videosViewModel;

    private ReviewsAdapter adapter;
    private VideosAdapter videosAdapter;

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        bindViews();

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(view -> this.supportFinishAfterTransition());

        Intent intent = getIntent();
        MovieEntity movie = intent.getParcelableExtra("MOVIE");

        reviews = new ArrayList<>();
        videos = new ArrayList<>();

        initViewModels(movie);

        videoRetryButton.setOnClickListener(view -> videosViewModel.getVideos(movie.getMovieId()));
        reviewRetryButton.setOnClickListener(view -> reviewViewModel.getReviews(movie.getMovieId()));

        setupReviewsRecyclerView();
        setupVideosRecyclerView();

        handleReviewData();
        handleReviewProgress();
        handleReviewFailure();

        handleVideoProgress();
        handleVideoData();
        handleVideoFailure();

        collapsingToolbarLayout.setTitle(movie.getMovieTitle());

        loadDataToViews(movie);
    }

    private void bindViews(){
        backdropHeader = findViewById(R.id.backdrop_header);
        toolbar = findViewById(R.id.anim_toolbar);
        collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        titleTv = findViewById(R.id.title_tv);
        ratingTv = findViewById(R.id.rating_tv);
        releaseDateTv = findViewById(R.id.release_date_tv);
        posterImageView = findViewById(R.id.detail_poster);
        overviewTv = findViewById(R.id.overview_tv);
        reviewsRv = findViewById(R.id.reviews_rv);
        videoShimmer = findViewById(R.id.video_shimmer);
        reviewShimmer = findViewById(R.id.review_shimmer);
        videoRetry = findViewById(R.id.video_failure);
        videoRetryButton = videoRetry.findViewById(R.id.retry_button);
        reviewRetry = findViewById(R.id.review_failure);
        reviewRetryButton = reviewRetry.findViewById(R.id.retry_button);
        videosRv = findViewById(R.id.videos_rv);
    }

    private void initViewModels(MovieEntity movie){
        reviewViewModel = new ViewModelProvider(this, new ReviewViewModelFactory(this.getApplication(), movie.getMovieId())).get(ReviewViewModel.class);
        videosViewModel = new ViewModelProvider(this, new VideoViewModelFactory(this.getApplication(), movie.getMovieId())).get(VideosViewModel.class);
    }

    private void setupReviewsRecyclerView(){
        reviewsRv.setLayoutManager(new LinearLayoutManager(this));
        adapter = new ReviewsAdapter(reviews);
        reviewsRv.setAdapter(adapter);
    }

    private void setupVideosRecyclerView(){
        videosRv.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        videosAdapter = new VideosAdapter(videos, this::openYoutubeVideo);

        videosRv.setAdapter(videosAdapter);
    }

    private void openYoutubeVideo(String videoID){
        Intent ytIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v="+videoID));
        try{
            this.startActivity(ytIntent);
        } catch (ActivityNotFoundException e){
            Log.i(TAG, Objects.requireNonNull(e.getLocalizedMessage()));
        }
    }

    private void handleReviewData(){
        reviewViewModel.getReviewResultLiveData().observe(this, reviewResult -> {
            if(reviewResult!=null){
                reviews.clear();
                reviews.addAll(reviewResult.getResults());
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void handleReviewProgress(){
        reviewViewModel.progressMutableLiveData().observe(this, progress->{
            if(progress) {
                reviewShimmer.setVisibility(View.VISIBLE);
                reviewShimmer.startShimmerAnimation();
                reviewsRv.setVisibility(View.GONE);
            } else {
                reviewShimmer.startShimmerAnimation();
                reviewShimmer.setVisibility(View.GONE);
                reviewsRv.setVisibility(View.VISIBLE);
            }
        });
    }

    private void handleReviewFailure(){
        reviewViewModel.failureMutableLiveData().observe(this, failure->{
            if(failure){
                reviewRetry.setVisibility(View.VISIBLE);
                reviewsRv.setVisibility(View.GONE);
            } else {
                reviewRetry.setVisibility(View.GONE);
                reviewsRv.setVisibility(View.VISIBLE);
            }
        });
    }

    private void handleVideoProgress(){
        videosViewModel.progressMutableLiveData().observe(this, progress->{
            if(progress) {
                videoShimmer.setVisibility(View.VISIBLE);
                videoShimmer.startShimmerAnimation();
                videosRv.setVisibility(View.GONE);
            } else {
                videoShimmer.startShimmerAnimation();
                videoShimmer.setVisibility(View.GONE);
                videosRv.setVisibility(View.VISIBLE);
            }
        });
    }

    private void handleVideoData(){
        videosViewModel.getVideosLiveData().observe(this, videosResult -> {
            if(videosResult!=null){
                videos.clear();
                videos.addAll(videosResult.getResults());
                videosAdapter.notifyDataSetChanged();
            }
        });
    }

    private void handleVideoFailure(){
        videosViewModel.failureMutableLiveData().observe(this, failure->{
            if(failure){
                videoRetry.setVisibility(View.VISIBLE);
                videosRv.setVisibility(View.GONE);
            } else {
                videoRetry.setVisibility(View.GONE);
                videosRv.setVisibility(View.VISIBLE);
            }
        });
    }

    private void loadDataToViews(MovieEntity movie){
        Picasso.get()
                .load(imageBaseURL+movie.getBackdropPath())
                .into(backdropHeader);

        Picasso.get()
                .load(imageBaseURL+movie.getPosterPath())
                .into(posterImageView);


        titleTv.setText(movie.getMovieTitle());
        ratingTv.setText(Double.toString(movie.getVoteAverage()));
        releaseDateTv.setText(movie.getReleaseDate());
        overviewTv.setText(movie.getOverview());
    }
}