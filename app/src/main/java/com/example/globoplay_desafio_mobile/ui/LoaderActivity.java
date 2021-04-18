package com.example.globoplay_desafio_mobile.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.globoplay_desafio_mobile.databinding.LoaderActivityBinding;
import com.example.globoplay_desafio_mobile.ui.account.LoginActivity;

public class LoaderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ViewBinding
        LoaderActivityBinding loaderActivityBinding = LoaderActivityBinding.inflate(getLayoutInflater());
        setContentView(loaderActivityBinding.getRoot());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startActivity = new Intent(LoaderActivity.this, LoginActivity.class);
                startActivity(startActivity);
                finish();
            }
        }, 2000);
    }
}
