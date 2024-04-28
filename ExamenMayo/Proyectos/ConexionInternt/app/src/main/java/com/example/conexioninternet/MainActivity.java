package com.example.conexioninternet;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.conexioninternet.databinding.ActivityMainBinding;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        DescargaPaginaWeb descargaPaginaWeb = new DescargaPaginaWeb();
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //metodo comprueba el acceso a Internet
                comprobarAccesoInternt();
            }
        });

        binding.botonDescargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //descarga en forma de String una paguina web indicada en un editext
                descargaPaginaWeb.descargar(view);
            }
        });

    }

    private void comprobarAccesoInternt() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(getApplicationContext().CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "Acceso internet OK", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "SIN acceso Internet", Toast.LENGTH_LONG).show();
        }
    }


    private class DescargaPaginaWeb extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... urls) {
            // params viene del método execute() call: params[0] es la url.
            try {
                return descargaUrl(urls[0]);
            } catch (IOException e) {
                return "Imposible cargar la web! URL mal formada";
            }
        }

        // onPostExecute visualiza los resultados del AsyncTask.
        @Override
        protected void onPostExecute(String result) {
            binding.txtDescarga.setText(result);
        }

        /**
         * Este método lee el inputstream convirtiéndolo en una cadena
         * ayudándonos con un ByteArrayOutputStream()
         */
        private String leer(InputStream is) {
            try {
                ByteArrayOutputStream bo = new ByteArrayOutputStream();
                int i = is.read();
                while (i != -1) {
                    bo.write(i);
                    i = is.read();
                }
                return bo.toString();
            } catch (IOException e) {
                return "";
            }
        }

        // Dada una URL, establece una conexión HttpUrlConnection y devuelve
        // el contenido de la página web con un InputStream, y que se transforma a un String.
        private String descargaUrl(String myurl) throws IOException {
            InputStream is = null;
            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000 /* milisegundos */);
                conn.setConnectTimeout(15000 /* milisegundos */);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                // comienza la consulta
                conn.connect();
                int response = conn.getResponseCode();
                is = conn.getInputStream();
                // convertir el InputStream a string
                return leer(is);
            }

            finally {
                if (is != null) {
                    //Nos aseguramos de cerrar el inputStream.
                    is.close();
                }
            }
        }
        public void descargar(View v){

            binding.txtDescarga.setMovementMethod(new ScrollingMovementMethod());

            ConnectivityManager connMgr = (ConnectivityManager)
                    getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                new DescargaPaginaWeb().execute(binding.editextUrl.getText().toString());
            } else {
                binding.editextUrl.setText("No hay acceso a Internet");
            }
        }
    }
}