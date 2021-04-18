package com.example.globoplay_desafio_mobile.ui.details;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import com.example.globoplay_desafio_mobile.databinding.FragmentDetailsBinding;
import com.example.globoplay_desafio_mobile.models.movies.CastModel;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.viewmodels.movies.MovieDetailsViewModel;
import java.util.List;

public class FragmentDetails extends Fragment {

    //ViewBinding
    private FragmentDetailsBinding fragmentDetailsBinding;

    //ViewModel
    private MovieDetailsViewModel movieDetailsViewModel;

    //Movie
    private MovieModel movie;

    private String namesCast = "";

    private int maxCast = 0;

    public Fragment newInstance(MovieModel movie) {
        this.movie = movie;
        return new FragmentDetails();
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        fragmentDetailsBinding = FragmentDetailsBinding.inflate(inflater,container,false);

        movieDetailsViewModel = new ViewModelProvider(this).get(MovieDetailsViewModel.class);

        //Calling the observer
        observeCastChange();

        //get cast
        movieDetailsViewModel.getCastID(movie.getId());

        fragmentDetailsBinding.tvMovieTitle.setText(movie.getOriginal_title());
        fragmentDetailsBinding.tvMovieDate.setText(movie.getRelease_date());
        fragmentDetailsBinding.tvMovieLanguage.setText(movie.getOriginal_language());
        fragmentDetailsBinding.tvMovieGender.setText(movie.getGenre_names());


        return fragmentDetailsBinding.getRoot();
    }

    private void observeCastChange() {
        movieDetailsViewModel.getMovieCast().observe(getViewLifecycleOwner(), new Observer<List<CastModel>>() {
            @Override
            public void onChanged(List<CastModel> castModels) {
                if(castModels!=null) {

                    for (CastModel cast: castModels)
                    {
                        if(maxCast == 20)
                            break;

                        if(cast.getKnown_for_department().equalsIgnoreCase("Acting")) {
                            namesCast = namesCast + cast.getName() + ";";
                            maxCast ++;
                        }

                    }

                    fragmentDetailsBinding.tvMovieCast.setText(namesCast);
                    movieDetailsViewModel.setNullmovieCast();
                }
            }
        });
    }
}
