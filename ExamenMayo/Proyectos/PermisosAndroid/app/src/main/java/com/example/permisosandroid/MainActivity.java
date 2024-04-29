package com.example.permisosandroid;

import android.Manifest;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*
        Nota: Cuando tu app llama a requestPermissions(), el sistema muestra al usuario un cuadro
        de diálogo estándar.Tu app no puede configurar ni modificar ese cuadro de diálogo. Si
        necesitas proporcionar información o una explicación al usuario, debes hacerlo antes de
        llamar a requestPermissions(), como se describe en Explica la razón por la cual la necesita
        permisos.
         */
        // Here, thisActivity is the current activity
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant. The callback method gets the
            // result of the request.
        }

    }

    /*
    Cuando tu app solicita permisos, el sistema muestra al usuario un cuadro de diálogo. Cuando el
    usuario responde, el sistema invoca el método onRequestPermissionsResult() de tu app y le
    pasa la respuesta del usuario. Tu app debe anular ese método para averiguar si se otorgó el
    permiso. El callback recibe el mismo código de solicitud que le pasaste a requestPermissions().
    Por ejemplo, si una app solicita acceso a READ_CONTACTS, es posible que tenga el siguiente método
    callback:
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }

    }
}
