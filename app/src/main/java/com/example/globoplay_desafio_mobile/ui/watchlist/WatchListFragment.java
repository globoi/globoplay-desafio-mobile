package com.example.globoplay_desafio_mobile.ui.watchlist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.adapters.GridViewAdapter;
import com.example.globoplay_desafio_mobile.databinding.WatchlistFragmentBinding;
import com.example.globoplay_desafio_mobile.interfaces.OnMovieListener;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.utils.Variables;
import com.example.globoplay_desafio_mobile.viewmodels.watchlist.WatchListViewModel;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class WatchListFragment extends Fragment implements OnMovieListener {

    //ViewBinding
    private WatchlistFragmentBinding watchlistFragmentBinding;
    //Adapter
    private GridViewAdapter gridViewAdapter;
    //ViewModel
    WatchListViewModel watchListViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        watchlistFragmentBinding = WatchlistFragmentBinding.inflate(inflater,container,false);

        watchListViewModel = new ViewModelProvider(requireActivity()).get(WatchListViewModel.class);

        Variables.WATCHLIST = true;

        //ConfigureGridView
        configureGridView();

        //Calling the observer
        observerWatchListChange();

        //get info user
        SharedPreferences prefs = getActivity().getSharedPreferences("detailsUser", MODE_PRIVATE);
        String sessionId = prefs.getString("sessionId","");
        String accountId = prefs.getString("accountId","");

        if(gridViewAdapter.movies !=null) {
            if(gridViewAdapter.movies.size() == 0) {
                //get WatchList
                watchListViewModel.getWatchList(accountId,sessionId);
            }
        }
        else {
            //get WatchList
            watchListViewModel.getWatchList(accountId,sessionId);
        }


        return watchlistFragmentBinding.getRoot();
    }

    //observer
    private void observerWatchListChange() {
        watchListViewModel.getMovies().observe(getViewLifecycleOwner(), new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                Variables.watchList = movieModels;
                gridViewAdapter.movies = movieModels;
                gridViewAdapter.notifyDataSetChanged();
            }
        });
    }


    //configure GridView
    private void configureGridView() {
        gridViewAdapter = new GridViewAdapter(this);
        watchlistFragmentBinding.gridview.setAdapter(gridViewAdapter);
    }

    //when user click on the movie
    @Override
    public void onMovieClick(MovieModel movie) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("movie", movie);
        Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.movieDetailsFragment,bundle);
    }

}
