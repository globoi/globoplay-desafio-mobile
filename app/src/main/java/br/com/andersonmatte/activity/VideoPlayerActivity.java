package br.com.andersonmatte.activity;

import android.os.Bundle;
import android.util.SparseArray;
import android.view.View;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MergingMediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource;
import com.google.android.exoplayer2.util.Util;

import at.huber.youtubeExtractor.VideoMeta;
import at.huber.youtubeExtractor.YouTubeExtractor;
import at.huber.youtubeExtractor.YtFile;
import br.com.andersonmatte.R;
import br.com.andersonmatte.entity.Movie;

public class VideoPlayerActivity extends AppCompatActivity {

    public static final String HTTPS_YOUTUBE = "https://www.youtube.com/watch?v=";
    private final String TAG = "Executando Video";

    private PlayerView playerView;
    private SimpleExoPlayer simpleExoPlayer;
    private Movie movie;

    boolean playWhenReady = true;
    int currentWindow = 0;
    int playBackPosition = 0;

    @Override
    protected void onStart() {
        super.onStart();
        if (Util.SDK_INT >= 24) {
            initPlayer();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (Util.SDK_INT >= 24) {
            releasePlayer();
        }
    }

    private void releasePlayer() {
        if (simpleExoPlayer != null) {
            playWhenReady = simpleExoPlayer.getPlayWhenReady();
            playBackPosition = (int) simpleExoPlayer.getCurrentPosition();
            currentWindow = simpleExoPlayer.getCurrentWindowIndex();
            simpleExoPlayer.release();
            simpleExoPlayer = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Util.SDK_INT <= 24 || simpleExoPlayer == null) {
            initPlayer();
            hideSystemUi();
        }
    }

    private void hideSystemUi() {
        playerView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LOW_PROFILE |
                        View.SYSTEM_UI_FLAG_FULLSCREEN |
                        View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                        View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION |
                        View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        );
    }

    @Override
    protected void onPause() {
        if (Util.SDK_INT <= 24) {
            releasePlayer();
        }
        super.onPause();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);
        this.hideHideActionBar();
        // inicia a view
        playerView = findViewById(R.id.player_view);
        //Recebe os dados passados na Intent da Classe MovieActivity por mecanismo de Bundle.
        Bundle bundle = getIntent().getBundleExtra("movie");
        if (bundle != null) {
            this.movie = (Movie) bundle.getSerializable("resultado");
        }
        this.initPlayer();
        this.fullScreen();
    }

    private void initPlayer() {
        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(simpleExoPlayer);

        if (movie.getUrlVideo() != null) {
            this.executaVideo(movie.getUrlVideo());
        }
    }

//    private void setFullScreen() {
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//    }

    public void executaVideo(String urlVideo) {
        String url = HTTPS_YOUTUBE + urlVideo;

        simpleExoPlayer = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(simpleExoPlayer); //Add Player to ExoView
//        playerView.setKeepScreenOn(true);
//        playerView.showController();
//        simpleExoPlayer.setMediaItem(MediaItem.fromUri(url));
//        simpleExoPlayer.prepare();
//        simpleExoPlayer.setPlayWhenReady(true);

        new YouTubeExtractor(this) {
            @Override
            public void onExtractionComplete(SparseArray<YtFile> ytFiles, VideoMeta vMeta) {
                if (ytFiles != null) {

                    int videoTag = 18;
                    int audioTag = 18;

                    System.out.println(ytFiles);


                    MediaSource videoSource = new ProgressiveMediaSource
                            .Factory(new DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(ytFiles.get(videoTag).getUrl()));

                    MediaSource audioSource = new ProgressiveMediaSource
                            .Factory(new DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(ytFiles.get(audioTag).getUrl()));

                    simpleExoPlayer.setMediaSource(new MergingMediaSource(
                                    true,
                                    videoSource,
                                    audioSource),
                            true
                    );

                    simpleExoPlayer.prepare();
                    simpleExoPlayer.setPlayWhenReady(playWhenReady);
                    simpleExoPlayer.seekTo(currentWindow, playBackPosition);
                }
            }
        }.extract(url, false, true);


    }

    private void hideHideActionBar() {
        getSupportActionBar().hide();
    }

    private void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

}