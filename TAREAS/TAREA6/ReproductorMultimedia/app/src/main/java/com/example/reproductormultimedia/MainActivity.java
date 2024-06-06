package com.example.reproductormultimedia;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.reproductormultimedia.databinding.ActivityMainBinding;

import java.io.IOException;


public class MainActivity extends AppCompatActivity implements MediaController.MediaPlayerControl {

    private MediaController mc;
    private Handler h;
    private MediaPlayer mediaPlayer;
    View view;
    ActivityMainBinding binding;
    static Bitmap bitmap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        view = binding.getRoot();
        setContentView(view);


        //construcción de la lista que mandaremos como parametro
        LeerJson.clearLista();
        //System.out.println(ProductContent.ITEMS.size());

        /*
         * INICIO CREACIÓN DEL RecyclerView
         */
        //referencia al id del recycleView
        //instancia a la referencias
        //indicamos que el recycleView sea una lista horizontal o vertical
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));
        //instancia de la lista de datos a presentar en el recycleView
        //llenamos la lista de datos
        LeerJson.loadListaDesdeJSON(this);
        //15. enviamos la lista con los datos al adaptador
        Adaptador adapter = new Adaptador(LeerJson.ITEMS, this);//referencia a onclick
        //16. indicamos al recycleView que adaptador usar
        binding.recyclerView.setAdapter(adapter);

        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    mc.show();
                }
                return true;
            }
        });
    }

    private void reproducirMusica(MediaPlayer mediaPlayer) {
        mc = new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(view);
        h = new Handler();
        //inicia el control de la reproducción cuando este preparado
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                h.post(new Runnable() {
                    public void run() {
                        mc.show(0);
                        mp.start();
                    }
                });
            }
        });
    }

    public void Stop(View v) throws IOException {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.prepare();
        }
    }

    public void Hide(View v) {
        mc.hide();
    }

    @Override
    public void start() {
        if (!mediaPlayer.isPlaying())
            mediaPlayer.start();
    }

    @Override
    public void pause() {
        if (mediaPlayer.isPlaying())
            mediaPlayer.pause();
    }

    @Override
    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    @Override
    public void seekTo(int i) {
        mediaPlayer.seekTo(i);
    }

    @Override
    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mediaPlayer.getAudioSessionId();
    }


    public void reenviarDatos(AppMedia objeto) {

        System.out.println(objeto.getUri().toString());
        // Detener y liberar el MediaController si está activo
        if (mc != null) {
            mc.hide();
        }

        // Detener y liberar el MediaPlayer anterior si está reproduciendo
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
        switch (objeto.getUri()) {
            case "alexander_nakaradathe_return.mp3":
                mediaPlayer = MediaPlayer.create(this, R.raw.alexander_nakaradathe_return);
                reproducirMusica(mediaPlayer);
                break;
            case "teylor.mkv":

                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
                break;
            case "https://youtu.be/pCFNnAjp-Wg?si=koOdaykQwdX42Rc8":
                Intent intent1 = new Intent(MainActivity.this, VentanaStreamingActivity.class);
                startActivity(intent1);
                break;
            case "https://dn720303.ca.archive.org/0/items/pangaea-2097/01%20Summer%20Rain.mp3":

                bitmap = objeto.getPhoto();
                Intent enviar = new Intent(MainActivity.this, MusicStreamingActivity.class);
                Bundle miDato = new Bundle();
                miDato.putString("dato", objeto.getUri());
                enviar.putExtras(miDato);
                startActivity(enviar);
                break;
        }
    }


}