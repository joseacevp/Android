package com.example.servicios;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class WirelessTester extends Service {
    final String tag = "Demo Servicio";
    public boolean enEjecucion = false;
    public boolean wifi_activo = false;
    Tester tester = new Tester();

    /**
     * Llamado cuando se crea el servicio.
     */
    @Override
    public void onCreate() {
        Log.i(tag, "Servicio WirelessTester creado!");
    }

    /**
     * El servicio se arranca mediante una llamada startService()
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (!enEjecucion) {
            enEjecucion = true;
            /*
        Después en la función onStartComand() invoca al método start() del thread, para que el thread
        "tester"se lance la primera vez que se arranca el servicio:
         */
            Log.i(tag, "Servicio WirelessTester arrancado!");
            tester.start();
        } else {
            Log.i(tag, "El servicio WirelessTester ya estaba arrancado!");
        }
        return START_STICKY;
    }

    /**
     * un cliente se vincula cuando llama a bindService()
     * Como es un servicio no vinculado, devolvemos null
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    /**
     * Llamado cuando se destruye el servicio
     */
    @Override
    public void onDestroy() {
        Log.i(tag, "Servicio WirelessTester destruido!");
        /*
        Finalmente, termina el thread cuando se destruye el servicio:
         */
        if (enEjecucion) {
            tester.interrupt();
        }
    }

    /*
   Tienes que tener en cuenta que necesitas algún mecanismo para que el servicio ejecute la tarea indefinidamente (es
un servicio) y qué mejor que un hilo (thread) para hacerlo. El thread se programa sobreescribiendo el método run,
que se ejecutará mientras el servicio esté arrancado. El método CompruebaConnexiónWifi utiliza un objeto
ConnectivityManager para conseguir información sobre la red y saber si el dispositivo está o no conectado a la wifi.
El bucle del thread itera cada tres segundos gracias a la instrucción, sleep(3000), y cuando se detecta algún cambio
de estado en la conexión por wifi, se reporta a través del Log.

1º Crea una subclase de la clase thread:
    */
    private class Tester extends Thread {
        @Override
        public void run() {
            while (enEjecucion) {
                try {
                    Log.i(tag, "servicio ejecutándose....");

                    if (wifi_activo != CompruebaConexionWifi()) {
                        wifi_activo = !wifi_activo; //Cambio de estado
                        if (wifi_activo)
                            //comprueba la conexion cada 3 segundos
                            Log.i(tag, "Conexión wifi activada");
                        else
                            Log.i(tag, "Conexión wifi desactivada");
                    }
                    this.sleep(3000);
                } catch (InterruptedException e) {
                    enEjecucion = false;
                    Log.i(tag, "hilo del servicio interrumpido....");
                }
            }

        }
    }

    public boolean CompruebaConexionWifi() {
        //crea un objeto ConnectivityManager que nos da información de la red
        ConnectivityManager connectivity = (ConnectivityManager)
                getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            //Objtener información de la red: acceso vía WIFI
            NetworkInfo info = connectivity.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            if (info != null) {
                //Mirar si el dispositivo está conectado por WIFI
                if (info.isConnected()) {
                    Log.i(tag,"conexion WIFI ON");
                    return true;//hay conexión
                }
            }
        }

        Log.i(tag,"conexion WIFI OFF");
        return false; //no hay conexión
    }

}
