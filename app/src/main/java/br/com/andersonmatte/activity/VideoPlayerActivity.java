package br.com.andersonmatte.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import br.com.andersonmatte.R;
import br.com.andersonmatte.entity.Movie;

public class VideoPlayerActivity extends AppCompatActivity {

    public static final String HTTPS_YOUTUBE = "https://www.youtube.com/watch?v=";
    private Movie movie;
    private String TAG = "Executando Video";
    private VideoView videoView;
    private MediaController mediaControls;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        //Recebe os dados passados na Intent da Classe MovieActivity por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("movie");
        if (bundle != null) {
            this.movie = (Movie) bundle.getSerializable("resultado");
            videoView = findViewById(R.id.videoView);
            if (this.movie.getUrlVideo() != null) {
                this.executaVideo(this.movie.getUrlVideo());
            }
        }
    }

    public void executaVideo(String urlVideo){
        //String url = "http://trueliketop.org/play/player/serverjw.php\\?f=cavaleiros-do-zodiaco/dub/97.mp4";
        String url = HTTPS_YOUTUBE + urlVideo;
        videoView.setVideoURI(Uri.parse(url));
        videoView.requestFocus();
        videoView.start();
    }

}