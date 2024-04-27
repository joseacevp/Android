package com.example.broadcastsnotificacionsistema;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/*
Un receptor de multidifusión, en inglés, Broadcast Receiver, es un componente de una App que recibe
 notificaciones de eventos que se han producido en el sistema. Por ejemplo, podemos programar una clase que obtenga
información de cuándo el sistema operativo ha terminado de cargar después de encender el dispositivo,
 o que el dispositivo se está quedando sin batería, etc.
Para poder recibir estas notificaciones, tenemos que hacer dos cosas.
1º: Crear una subclase de la clase BroadcastReceiver e implementar el método onReceive() que
 recibirá un intent a través del cual se puede saber qué notificación hemos recibido. Por ejemplo para programar un
receptor que nos
avise de haber recibido un SMS:
*/
public class Receptor extends BroadcastReceiver {
    private final String SMS_RECEIVED="android.provider.Telephony.SMS_RECEIVED";
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(SMS_RECEIVED))
            Toast.makeText(context, "Recibido SMS", Toast.LENGTH_LONG).show();
    }
}


