package com.example.globoplay_desafio_mobile.ui.details;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import com.bumptech.glide.Glide;
import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.adapters.FragmentAdapter;
import com.example.globoplay_desafio_mobile.databinding.MovieDetailsFragmentBinding;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.models.movies.VideoModel;
import com.example.globoplay_desafio_mobile.request.MediaRequest;
import com.example.globoplay_desafio_mobile.utils.Variables;
import com.example.globoplay_desafio_mobile.viewmodels.movies.MovieDetailsViewModel;
import com.google.android.material.tabs.TabLayout;
import java.util.List;
import static android.content.Context.MODE_PRIVATE;

public class MovieDetailsFragment extends Fragment {

    //ViewBinding
    private MovieDetailsFragmentBinding movieDetailsFragmentBinding;
    //ViewModel
    private MovieDetailsViewModel movieDetailsViewModel;
    //Movie
    private MovieModel movie;

    private static boolean isFavorite = false;
    String sessionId,accountId ="";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        movieDetailsFragmentBinding = MovieDetailsFragmentBinding.inflate(inflater,container,false);

        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        movieDetailsFragmentBinding.btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigateUp();
            }
        });

        movie = getArguments().getParcelable("movie");

        //check if it's on the favorites list or not
        checkMovieFav();

        //btn play video
        movieDetailsFragmentBinding.btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(movie.getVideo() == null) // if movie hasn't video
                {
                    //Calling the observer
                    observerUrlVideoMovieChange();
                    movieDetailsViewModel.getUrlVideoMoviesID(movie.getId()); //get url
                }
                else
                    showVideo(movie.getVideo());
            }
        });

        //btn add and rm movie from favorites
        movieDetailsFragmentBinding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get info user
                SharedPreferences prefs = getActivity().getSharedPreferences("detailsUser", MODE_PRIVATE);
                String sessionId = prefs.getString("sessionId","");
                String accountId = prefs.getString("accountId","");

                //Calling the observer
                observerFavMovieChange();

                MediaRequest mediaRequest = null;

                if (isFavorite)
                     mediaRequest = new MediaRequest("movie",movie.getId(),false);
                else
                     mediaRequest = new MediaRequest("movie",movie.getId(),true);

                movieDetailsViewModel.addRmMovieFav(accountId,sessionId,mediaRequest);
            }
        });

        //show details
        showDetails();

        return movieDetailsFragmentBinding.getRoot();
    }

    //observer
    private void observerFavMovieChange() {
        movieDetailsViewModel.getResponse().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer status_code) {
                if(status_code != null)
                {
                    if(status_code == 1) //Sucess added
                    {
                        Variables.watchList.add(movie);
                        isFavorite = true;
                        AddRmFavMovie(); //update layout
                    }
                    else if(status_code == 13) //Sucess removed
                    {
                        Variables.watchList.remove(movie);
                        isFavorite = false;
                        AddRmFavMovie(); //update layout
                    }
                    else
                        Toast.makeText(getContext(),"Error executing request ",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //observer
    private void observerUrlVideoMovieChange() {
        movieDetailsViewModel.getUrlVideosMovie().observe(getViewLifecycleOwner(), new Observer<List<VideoModel>>() {
            @Override
            public void onChanged(List<VideoModel> videoModels) {
                if(videoModels != null){
                    for (VideoModel video : videoModels)
                    {
                        movie.setVideo(video.getKey());
                        showVideo(video.getKey());
                        break;
                    }
                }

            }
        });
    }

    //show details on the screen
    private void showDetails(){
        Glide.with(movieDetailsFragmentBinding.backgroundImage).load("https://image.tmdb.org/t/p/w500/" +
                movie.getPoster_path()).placeholder(R.drawable.movie).into(movieDetailsFragmentBinding.backgroundImage);

        Glide.with(movieDetailsFragmentBinding.moviePoster).load("https://image.tmdb.org/t/p/w500/" +
                movie.getPoster_path()).placeholder(R.drawable.movie).into(movieDetailsFragmentBinding.moviePoster);

        movieDetailsFragmentBinding.movieTitle.setText(movie.getOriginal_title());

        movieDetailsFragmentBinding.movieOverview.setText(movie.getOverview());

        //add tabs and view pager
        addTabs();
    }

    //add tabs on the screen
    private void addTabs(){
        TabLayout tabLayout = movieDetailsFragmentBinding.tabLayout;
        ViewPager viewPager = movieDetailsFragmentBinding.pager;

        //add tabs
        tabLayout.addTab(tabLayout.newTab().setText("Similar Movies"));
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //add fragments
        FragmentAdapter adapter = new FragmentAdapter(getChildFragmentManager(),movie);
        viewPager.setAdapter(adapter);

        //slide tabs
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        //click tabs
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });
    }

    //check if it's on the favorites list or not
    private void checkMovieFav() {

        movieDetailsViewModel.setNullresponseAddRm();

        //If the user adds a movie to their favorites before clicking on the "My List" tab,
        // they will need to obtain a list of favorites to update the layout when opening the details of the movie
        if(!Variables.WATCHLIST)
        {
            getInfoUser();
            //calling observer
            observerWatchListChange();
            movieDetailsViewModel.getWatchList(accountId,sessionId);
        }
        else {

            if (Variables.watchList.size() == 0)
                isFavorite = false;

            for (MovieModel movieWatchList : Variables.watchList) {

                if (movie.getId() == movieWatchList.getId()) {
                    isFavorite = true;
                    break;
                } else
                    isFavorite = false;
            }
        }

        AddRmFavMovie(); //update layout
    }

    private void getInfoUser(){
        //get info user
        SharedPreferences prefs = getActivity().getSharedPreferences("detailsUser", MODE_PRIVATE);
         sessionId = prefs.getString("sessionId","");
         accountId = prefs.getString("accountId","");
    }

    //observer
    private void observerWatchListChange() {
        movieDetailsViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                Variables.watchList = movieModels;

                if (Variables.watchList.size() == 0)
                    isFavorite = false;

                for (MovieModel movieWatchList : Variables.watchList) {

                    if (movie.getId() == movieWatchList.getId()) {
                        isFavorite = true;
                        break;
                    } else
                        isFavorite = false;
                }
            }
        });
    }

    //update layout
    private void AddRmFavMovie(){
        if (isFavorite) {
            movieDetailsFragmentBinding.btnAdd.setText("Added");
            movieDetailsFragmentBinding.btnAdd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_check_24, 0, 0, 0);
        }
        else{
            movieDetailsFragmentBinding.btnAdd.setText("My List");
            movieDetailsFragmentBinding.btnAdd.setCompoundDrawablesWithIntrinsicBounds(R.drawable.baseline_star_rate_24, 0, 0, 0);
        }

        movieDetailsViewModel.setNullresponseAddRm();
    }

    //show trailer movie
    private void showVideo(String videoId) {
        movieDetailsViewModel.setNullurlVideosMovie();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + videoId));
        startActivity(intent);
    }

}