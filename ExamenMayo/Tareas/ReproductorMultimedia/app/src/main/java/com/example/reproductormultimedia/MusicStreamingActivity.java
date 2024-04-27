package com.example.reproductormultimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.reproductormultimedia.databinding.ActivityMusicStreamingBinding;

public class MusicStreamingActivity extends AppCompatActivity {
    private ActivityMusicStreamingBinding binding;
    private View view;
    private MediaController mediaController;
    private VideoView videoView;
    String string ;

    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_streaming);
        binding = ActivityMusicStreamingBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        imageView = binding.imageView;
        imageView.setImageBitmap(MainActivity.bitmap);

        Bundle datoRecivido = this.getIntent().getExtras();
        if (datoRecivido!=null){
            String datoString = datoRecivido.getString("dato");

            string = datoString;

        }
        // Inicializar videoView y mediaController
        videoView = binding.videoView2;

        mediaController = new MediaController(this);

        // Establecer la vista de anclaje del mediaController
        mediaController.setAnchorView(videoView);

        // Establecer la URI del video y empezar la reproducción cuando esté preparado
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse(string));
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaController.show(10000);
                videoView.start();
            }
        });

    }

    // muestra las herramientas de reproduccion al pulsar la pantalla
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mediaController.show();
        return true;
    }


}