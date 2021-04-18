package com.example.globoplay_desafio_mobile.ui.movieList;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import com.example.globoplay_desafio_mobile.R;
import com.example.globoplay_desafio_mobile.databinding.MainListActivityBinding;

public class MainListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ViewBinding
        MainListActivityBinding mainListActivityBinding = MainListActivityBinding.inflate(getLayoutInflater());
        setContentView(mainListActivityBinding.getRoot());

        // Bottom nav
        NavController navController = Navigation.findNavController(this,R.id.nav_host_fragment);

        NavigationUI.setupWithNavController(mainListActivityBinding.bottomNavigationView,navController);

    }

}
