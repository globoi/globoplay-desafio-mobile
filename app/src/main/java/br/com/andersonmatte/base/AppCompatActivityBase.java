package br.com.andersonmatte.base;

import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import br.com.andersonmatte.R;
import br.com.andersonmatte.activity.HomeActivity;
import br.com.andersonmatte.activity.MinhaListaActivity;
import br.com.andersonmatte.entity.Genre;

public class AppCompatActivityBase extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView navigationView;

    private List<Genre> genres = new ArrayList<Genre>();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //Cria o BottomNavigationView.
        this.navigationView = (BottomNavigationView) findViewById(R.id.bottomNav);
        this.navigationView.setOnNavigationItemSelectedListener(this);
    }


    //Define os itens do BottomNavigationView e chama as suas Activities conforme seleção.
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home: {
                Intent intent = new Intent(this, HomeActivity.class);
                startActivity(intent);
                finish();
                break;
            }
            case R.id.minha_lista: {
                Intent intent = new Intent(this, MinhaListaActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
        return true;
    }

}