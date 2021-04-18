package com.example.globoplay_desafio_mobile.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.globoplay_desafio_mobile.models.movies.MovieModel;
import com.example.globoplay_desafio_mobile.ui.details.FragmentDetails;
import com.example.globoplay_desafio_mobile.ui.details.FragmentSimilar;

public class FragmentAdapter extends FragmentStatePagerAdapter  {

    private static final int NUMBER_OF_TABS = 2;
    private Fragment[] tabList = new Fragment[NUMBER_OF_TABS];
    private  MovieModel movie;

    public FragmentAdapter(FragmentManager fragmentManager, MovieModel movie) {
        super(fragmentManager);
        this.movie = movie;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    FragmentSimilar fragmentSimilar = new FragmentSimilar();
                    fragmentSimilar.newInstance(movie);
                    tabList[0] = fragmentSimilar;
                    return tabList[0];
                case 1:
                    FragmentDetails fragmentDetails = new FragmentDetails();
                    fragmentDetails.newInstance(movie);
                    tabList[1] = fragmentDetails;
                    return tabList[1];
            }

        return null;
    }


    @Override
    public int getCount() {
        return NUMBER_OF_TABS;
    }
}
