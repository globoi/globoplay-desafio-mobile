package com.example.globoplay_desafio_mobile.ui.movieList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.adapters.recyclerviewparent.MovieListParentApdater;
import com.example.globoplay_desafio_mobile.databinding.MovieListFragmentBinding;
import com.example.globoplay_desafio_mobile.interfaces.OnBtnMoreListener;
import com.example.globoplay_desafio_mobile.interfaces.OnMovieListener;
import com.example.globoplay_desafio_mobile.models.movies.GenderModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.viewmodels.movies.MovieListViewModel;
import java.util.List;

public class MovieListFragment extends Fragment implements OnMovieListener, OnBtnMoreListener {

    //Adapter
    private MovieListParentApdater movieListParentApdater;

    //ViewModel
    private MovieListViewModel movieListViewModel;

    //ViewBinding
    private MovieListFragmentBinding movieListFragmentBinding;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        movieListFragmentBinding = MovieListFragmentBinding.inflate(inflater,container,false);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        configureRecyclerView();

        //Calling the observer
        observeGendersChange();

        if(movieListParentApdater.movie_gender_list !=null) {
            if(movieListParentApdater.movie_gender_list.size() == 0)
                //get genres
                movieListViewModel.getGenresMovies();
        } else
            //get genres
            movieListViewModel.getGenresMovies();


        return movieListFragmentBinding.getRoot();

    }

    private void observeGendersChange() {
        movieListViewModel.getGenres().observe(getViewLifecycleOwner(),new Observer<List<GenderModel>>() {
            @Override
            public void onChanged(List<GenderModel> genderModels) {
                //Observing for any data change
                if(genderModels!=null) {
                    //add genres to recyclerview vertical
                    movieListParentApdater.movie_gender_list = genderModels;
                    movieListParentApdater.notifyDataSetChanged();
                }
            }
        });
    }

    //Configure RecyclerView
    private void configureRecyclerView() {
        movieListParentApdater = new MovieListParentApdater(getActivity(),this,this);
        movieListFragmentBinding.rvParent.setLayoutManager(new LinearLayoutManager(getContext()));
        movieListFragmentBinding.rvParent.setAdapter(movieListParentApdater);

    }

    @Override
    public void onMovieClick(MovieModel movie) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.movieDetailsFragment,bundle);
    }

    @Override
    public void onBtnMoreClick(int id) {
        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        Navigation.findNavController(requireActivity(),R.id.nav_host_fragment).navigate(R.id.movieListMoreFragment,bundle);
    }
}