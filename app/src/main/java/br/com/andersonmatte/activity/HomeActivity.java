package br.com.andersonmatte.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.andersonmatte.R;
import br.com.andersonmatte.adapter.RecyclerViewAdapter;
import br.com.andersonmatte.base.AppCompatActivityBase;
import br.com.andersonmatte.entity.Result;
import br.com.andersonmatte.entity.Welcome;
import br.com.andersonmatte.service.ITheMovieDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivityBase {

    private static final String URL_GET_IMAGE = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";
    public static final String API_KEY = "d4ac950397b93ecc6119e1ea932b0dfa";
    public static final String IDIOMA = "pt";

    private List<Result> resultList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.buscaPopularFilmes();
    }

    public void buscaPopularFilmes() {
        ITheMovieDB iTheMovieDB = ITheMovieDB.retrofit.create(ITheMovieDB.class);
        final Call<Welcome> callUsuario = iTheMovieDB.getPopularFilmes(API_KEY, IDIOMA);
        callUsuario.enqueue(new Callback<Welcome>() {
            @Override
            public void onResponse(Call<Welcome> call, Response<Welcome> response) {
                Welcome welcome = response.body();
                if (welcome != null && !welcome.getResults().isEmpty()) {
                    resultList = welcome.getResults();
                    chamaAdapter();
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

    public void chamaAdapter() {
        RecyclerView myrv = (RecyclerView) findViewById(R.id.recyclerview_id);
        Log.i("INFO", getResources().getString(R.string.total_registros) + resultList.size());
        this.montaURLCompleta();
        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(this, resultList);
        myrv.setLayoutManager(new GridLayoutManager(this, 3));
        myrv.setAdapter(myAdapter);
    }

    private void montaURLCompleta() {
        for (Result result : this.resultList) {
            result.setURLCompleta(URL_GET_IMAGE + result.getPosterPath());
        }
    }

}