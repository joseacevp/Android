package com.example.notificaciones;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      /*  PASO 1: Crear la notificación con sus propiedades: Con el objeto constructorNotif de
        la clase
        NotificationCompat.Builder, se establecen las propiedades.
        */

//PARA SOLO UNA LINEA DE NOTIFICACION......................................
        int notifId = 1; //Identificador de la notificación, para futuras modificaciones.
        NotificationCompat.Builder constructorNotif = new
                NotificationCompat.Builder(this, "mi_canal");
        constructorNotif.setSmallIcon(android.R.drawable.ic_dialog_alert);
        constructorNotif.setContentTitle("Mi notificación");
        constructorNotif.setContentText("Has recibido una notificación!!");
//........................................................
        /*
        PASO 2: Creamos un intent para abrir la actividad cuando se pulse la notificación:
        Se utiliza una estructura de datos de tipo pila para asegurarnos de que el botón de "Atrás"
        del dispositivo nos lleva desde la Actividad a la pantalla principal, y se establece el
        intent que creará la notificación a través de un objeto PendingIntent.
        */
        Intent resultadoIntent = new Intent(this, MainActivity.class);
        TaskStackBuilder pila = TaskStackBuilder.create(this);
        pila.addParentStack(MainActivity.class);
// Añade el Intent que comienza la Actividad al inicio de la pila
        pila.addNextIntent(resultadoIntent);
        PendingIntent resultadoPendingIntent =
                pila.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        constructorNotif.setContentIntent(resultadoPendingIntent);

        /*
        PASO 3: Enviar la notificación
        */
        NotificationManager notificador =
                (NotificationManager)
                        getSystemService(getApplicationContext().NOTIFICATION_SERVICE);
//A partir de la version O, hay que crear un canal de notificación
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel("mi_canal",
                    "título del canal de notificación",
                    NotificationManager.IMPORTANCE_DEFAULT);
            notificador.createNotificationChannel(channel);
        }

//...........................................
//        /*
//        PASO EXTRA: [Opcional] Crear notificación con layout expandible: Antes de enviar la notificación,
//        hay que crear un objeto NotificationCompat.InboxStyle y definir sus propiedades. Estas
//        propiedades son el BigContentTitle y las líneas de la descripción, tal y como puedes ver en
//        la figura anterior:
//        */
//        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
//        String[] eventos = new String[5];
//// Título del expanded layout
//        inboxStyle.setBigContentTitle("Notificación expandible:");
//        eventos[0] = "Esto es la primera línea";
//        eventos[1] = "Esto es la segunda línea";
//        eventos[2] = "Esto es la tercera línea";
//        eventos[3] = "Esto es la cuarta línea";
//        eventos[4] = "Esto es la quita línea";
//// Mueve eventos dentro del expanded layout
//        for (int i = 0; i < eventos.length; i++)
//            inboxStyle.addLine(eventos[i]);
//// Mueve el expanded layout a la notificación.
//        constructorNotif.setStyle(inboxStyle);
////Dar máxima prioridad y ponerlo en la cima de las notificaciones
//        constructorNotif.setWhen(0);
//        constructorNotif.setPriority(Notification.PRIORITY_MAX);
// PARA NOTIFICACIONE CON VARIAS LIENAS........................................
        //envia la notificacion
        notificador.notify(notifId, constructorNotif.build());
    }
}