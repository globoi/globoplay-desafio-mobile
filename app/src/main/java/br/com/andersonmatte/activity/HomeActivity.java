package br.com.andersonmatte.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.andersonmatte.R;
import br.com.andersonmatte.adapter.RecyclerViewAdapter;
import br.com.andersonmatte.base.AppCompatActivityBase;
import br.com.andersonmatte.entity.Movie;
import br.com.andersonmatte.entity.Welcome;
import br.com.andersonmatte.service.ITheMovieDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivityBase {

    private static final String URL_GET_IMAGE = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    public static final String API_KEY = "d4ac950397b93ecc6119e1ea932b0dfa";
    public static final String IDIOMA = "pt";

    private List<Movie> movieList = new ArrayList<Movie>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.buscaPopularFilmes();
    }

    public void buscaPopularFilmes() {
        int contador = 0;
        // Chamar até o 10, apensa para ter uma quantidade razoável de filmes
        for (int i = 0; i < 10; i++) {
            contador++;
            ITheMovieDB iTheMovieDB = ITheMovieDB.retrofit.create(ITheMovieDB.class);
            final Call<Welcome> callUsuario = iTheMovieDB.getFilmesPopulares(API_KEY, IDIOMA, contador);
            int finalContador = contador;
            callUsuario.enqueue(new Callback<Welcome>() {
                @Override
                public void onResponse(Call<Welcome> call, Response<Welcome> response) {
                    Welcome welcome = response.body();
                    if (welcome != null && !welcome.getResults().isEmpty()) {
                        List<Movie> filmesPorPagina;
                        // Todo não fezo Cast, ver o pq,
                        //  java.lang.ClassCastException: java.util.ArrayList cannot be cast to br.com.andersonmatte.entity.Result
                        // Gambis!
                        filmesPorPagina = welcome.getResults();
                        movieList.addAll(filmesPorPagina);
                        if (finalContador == 10) {
                            chamaAdapter();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), getResources().getString(R.string.erro_busca_servico), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Welcome> call, Throwable t) {
                    Log.i("ERRO", getResources().getString(R.string.erro_busca_servico) + t.getMessage());
                }
            });
        }
    }

    public void chamaAdapter() {
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        Log.i("INFO", getResources().getString(R.string.total_registros) + movieList.size());
        this.montaURLCompleta();
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, movieList);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
    }

    private void montaURLCompleta() {
        for (Movie movie : this.movieList) {
            movie.setURLCompleta(URL_GET_IMAGE + movie.getPosterPath());
        }
    }

}