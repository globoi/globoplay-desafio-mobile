package com.example.globoplay_desafio_mobile.ui.details;

import android.content.Intent;
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

import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.adapters.GridViewAdapter;
import com.example.globoplay_desafio_mobile.databinding.FragmentSimilarBinding;
import com.example.globoplay_desafio_mobile.interfaces.OnMovieListener;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.utils.Variables;
import com.example.globoplay_desafio_mobile.viewmodels.movies.MovieDetailsViewModel;

import java.util.List;

public class FragmentSimilar extends Fragment implements OnMovieListener {

    //ViewBinding
    private FragmentSimilarBinding fragmentSimilarBinding;

    //ViewModel
    private MovieDetailsViewModel movieDetailsViewModel;

    //Movie
    private MovieModel movie;

    //Adapter
    private GridViewAdapter gridViewAdapter;

    public  Fragment newInstance(MovieModel movie) {
        this.movie = movie;
        return new FragmentSimilar();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentSimilarBinding = FragmentSimilarBinding.inflate(inflater,container,false);

        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        //configure gridView
        configureGridView();

        //Calling the observer
        observerSimilarMoviesChange();

        //get similar movies
        movieDetailsViewModel.getSimilarMoviesID(movie.getId());


        return fragmentSimilarBinding.getRoot();
    }

    //observer
    private void observerSimilarMoviesChange() {
        movieDetailsViewModel.getSimilarMovies().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if(movieModels!=null) {
                    gridViewAdapter.movies = movieModels;
                    gridViewAdapter.notifyDataSetChanged();
                    movieDetailsViewModel.setNullsimilarMovies();
                }
            }
        });
    }

    //configure GridView
    private void configureGridView() {
        gridViewAdapter = new GridViewAdapter(this);
        fragmentSimilarBinding.gridview.setAdapter(gridViewAdapter);
    }

    @Override
    public void onMovieClick(MovieModel movie) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.movieDetailsFragment,bundle);
    }

}
