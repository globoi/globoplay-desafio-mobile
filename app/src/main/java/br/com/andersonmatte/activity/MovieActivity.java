package br.com.andersonmatte.activity;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import br.com.andersonmatte.R;
import br.com.andersonmatte.entity.Result;

public class MovieActivity extends AppCompatActivity {

    private static final String URL_GET_IMAGE = "https://image.tmdb.org/t/p/w600_and_h900_bestv2";

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        imageView = (ImageView) findViewById(R.id.moviethumbnail);

        Bundle bundle = getIntent().getBundleExtra("movie");
        if (bundle != null) {
            bundle.getSerializable("movie");
            Result result = (Result) bundle.getSerializable("resultado");
            Picasso.get().load(URL_GET_IMAGE + result.getPosterPath()).into(imageView);
        }
    }

}