package br.com.andersonmatte.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import br.com.andersonmatte.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //Controla o tempo de exibição da Splash Screen.
        Handler handle = new Handler();
        handle.postDelayed(new Runnable() {
            @Override
            public void run() {
                mostrarHome();
            }
        }, 3000);
    }

    /**
     * Chama a HomeActivity.
     */
    private void mostrarHome() {
        Log.i("ASSERT", this.getResources().getString(R.string.open_app));
        Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
        startActivity(intent);
        this.finish();
    }

}