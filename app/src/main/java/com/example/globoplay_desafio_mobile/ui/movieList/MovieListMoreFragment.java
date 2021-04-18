package com.example.globoplay_desafio_mobile.ui.movieList;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.adapters.GridViewAdapter;
import com.example.globoplay_desafio_mobile.databinding.MovieListMoreFragmentBinding;
import com.example.globoplay_desafio_mobile.interfaces.OnMovieListener;
import com.example.globoplay_desafio_mobile.interfaces.OnScrollListener;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.viewmodels.movies.MovieListMoreViewModel;

import java.util.List;

public class MovieListMoreFragment extends Fragment implements OnMovieListener, OnScrollListener, AbsListView.OnScrollListener {

    //ViewBinding
    private MovieListMoreFragmentBinding movieListMoreFragmentBinding;
    //ViewModel
    private MovieListMoreViewModel movieListMoreViewModel;
    //Adapter
    private GridViewAdapter gridViewAdapter;
    private boolean isLoading,lastPageScroll = false;
    private int id_genre = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        movieListMoreFragmentBinding = MovieListMoreFragmentBinding.inflate(inflater, container, false);

        movieListMoreViewModel = new ViewModelProvider(this).get(MovieListMoreViewModel.class);

        //configure gridView
        configureGridView();

        //Calling the observer
        observerAllMoviesChange();

        //configure searchView
        configureSearchView();

        id_genre = getArguments().getInt("id");

        if (gridViewAdapter.movies.size() != 0) {
            gridViewAdapter.movies.clear();
        }

        return movieListMoreFragmentBinding.getRoot();
    }


    //observer
    private void observerAllMoviesChange() {
        movieListMoreViewModel.getAllMovies().observe(requireActivity(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {

                    gridViewAdapter.movies.addAll(movieModels);

                    GridViewAdapter.allMovies.clear();
                    GridViewAdapter.allMovies.addAll(gridViewAdapter.movies);

                    gridViewAdapter.notifyDataSetChanged();

                    movieListMoreFragmentBinding.progressBarApp.setVisibility(View.INVISIBLE);
                    isLoading = false;
                }
            }

        });
    }

    //configure GridView
    private void configureGridView() {
        gridViewAdapter = new GridViewAdapter(this,this);
        movieListMoreFragmentBinding.gridview.setAdapter(gridViewAdapter);

        movieListMoreFragmentBinding.gridview.setOnScrollListener(this);
    }

    //configure searchView
    private void configureSearchView() {
        //search movie
        movieListMoreFragmentBinding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                gridViewAdapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    //when user clicks on the movie
    @Override
    public void onMovieClick(MovieModel movie) {
        movieListMoreViewModel.setNullMoviesPagination();
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment).navigate(R.id.movieDetailsFragment, bundle);

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        if ((visibleItemCount + firstVisibleItem) >= totalItemCount) {
            Log.v("Tag", "Last Page");

           if(id_genre != 0)
           {
               if (!lastPageScroll) {
                   if (!isLoading) {
                       isLoading = true;
                       movieListMoreFragmentBinding.progressBarApp.setVisibility(View.VISIBLE);
                       movieListMoreViewModel.getMoviesPagination(id_genre);
                   }
               }
           }
        }
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void setLastPageScroll(boolean lastPageScroll) {
        this.lastPageScroll = lastPageScroll;
    }
}
