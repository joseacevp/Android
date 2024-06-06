package com.example.reproductormultimedia;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.reproductormultimedia.databinding.ActivityVentanaStreamingBinding;
import com.example.reproductormultimedia.databinding.ActivityVideoBinding;

public class VentanaStreamingActivity extends AppCompatActivity {
    private ActivityVentanaStreamingBinding binding;
    private View view;
    private VideoView videoView;
    private MediaController mediaController ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_ventana_streaming);
        binding = ActivityVentanaStreamingBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);

        // Inicializar videoView y mediaController
        videoView = binding.videoView;

        mediaController = new MediaController(this);

        // Establecer la vista de anclaje del mediaController
        mediaController.setAnchorView(videoView);

        // Establecer la URI del video y empezar la reproducción cuando esté preparado
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(Uri.parse("https://ia601003.us.archive.org/25/items/BorisBrejchaTomorrowlandBelgium2018/Boris%20Brejcha%20%40%20Tomorrowland%20Belgium%202018.mp4"));
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