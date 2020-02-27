package com.example.treinoappudemy;

import android.os.Bundle;
import android.widget.TextView;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class VideoActivity extends YouTubeBaseActivity {
    private TextView txt_title2;
    private String atividade;
    private String videoPath;
    private YouTubePlayerView play_treino;
    private YouTubePlayer.OnInitializedListener initializedListener;
    private YoutubeKey youtubeKey;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        play_treino = findViewById(R.id.play_treino);
        txt_title2 = findViewById(R.id.txt_title2);

        getData();
        setTitle2(atividade);
        selectVideo(atividade);
        playVideo(videoPath);


    }

    public void getData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            atividade = bundle.getString("video");
        }
    }

    public void setTitle2(String atividade) {
        txt_title2.setText(atividade);
    }

    public void selectVideo(String atividade) {
        switch (atividade) {
            case "Corrida":
                videoPath = "g_nzW-BPv_Q";
                break;
            case "Barra":
                videoPath = "JqeQvFQxIig";
                break;
            case "Flexão":
                videoPath = "ImDugdLos_U";
                break;
            case "Abdominal":
                videoPath = "h_gJbEXOIXE";
                break;
            case "Musculação":
                videoPath = "WJZatdxOq0s";
                break;
            case "Luta":
                videoPath = "9V6zyoLWpBI";
                break;
            case "Aquecimento":
                videoPath = "Mblrfue0mT4";
                break;
        }
    }

    public void playVideo(final String videoPath) {
        youtubeKey = new YoutubeKey();
        initializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(videoPath);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

            }
        };

        play_treino.initialize(youtubeKey.getKey(), initializedListener);
    }

}
