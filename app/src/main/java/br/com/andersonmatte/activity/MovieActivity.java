package br.com.andersonmatte.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.io.Serializable;

import br.com.andersonmatte.R;
import br.com.andersonmatte.entity.Movie;
import br.com.andersonmatte.entity.ResultPlayer;
import br.com.andersonmatte.service.ITheMovieDB;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static br.com.andersonmatte.activity.HomeActivity.API_KEY;
import static br.com.andersonmatte.activity.HomeActivity.IDIOMA;

public class MovieActivity extends AppCompatActivity {

    private static final String URL_GET_IMAGE = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    private ImageView imageView;
    private TextView nameMovie, textDescription;
    private Movie movie;
    private Button buttonBuscarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        imageView = (ImageView) findViewById(R.id.moviethumbnail);
        nameMovie = (TextView) findViewById(R.id.nameMovie);
        textDescription = (TextView) findViewById(R.id.textDescription);
        buttonBuscarUsuario = (Button) findViewById(R.id.playMovie);

        Bundle bundle = getIntent().getBundleExtra("movie");
        if (bundle != null) {
            bundle.getSerializable("movie");
            Movie movie = (Movie) bundle.getSerializable("resultado");

            Picasso.get().load(URL_GET_IMAGE + movie.getPosterPath()).into(imageView);

            nameMovie.setText(movie.getTitle());
            textDescription.setText(movie.getOverview());

            this.movie = movie;

            buttonBuscarUsuario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buscaUrlPlayer(movie.getId());
                }
            });

        }
    }

    private void buscaUrlPlayer(int moveId) {
        ITheMovieDB iTheMovieDB = ITheMovieDB.retrofit.create(ITheMovieDB.class);
        final Call<ResultPlayer> callResultPlayer = iTheMovieDB.getPlayer(moveId, API_KEY, IDIOMA);
        callResultPlayer.enqueue(new Callback<ResultPlayer>() {
            @Override
            public void onResponse(Call<ResultPlayer> call, Response<ResultPlayer> response) {
                ResultPlayer resultPlayer = response.body();
                if (resultPlayer != null && !resultPlayer.getPlayer().isEmpty()) {
                    movie.setUrlVideo(resultPlayer.getPlayer().get(0).getKey());

                    // Chama o video player já com a URL do video no objeto video
                    Intent intent = new Intent(MovieActivity.this, VideoPlayerActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("resultado", (Serializable) movie);
                    intent.putExtra("movie", bundle);
                    startActivity(intent);

                } else {
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.nenhum_video), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResultPlayer> call, Throwable t) {
                Log.i("ERRO", getResources().getString(R.string.erro_busca_servico_video) + t.getMessage());
            }
        });
    }

}