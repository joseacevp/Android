package com.example.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //9. construcción de la lista que mandaremos como parametro
    ArrayList<String> listaDatos;
    //10. referencia al id del recycleView del layout main
    RecyclerView recyclerView;

    public static void reenviarDatos(TextView dato) {
        Toast.makeText(dato.getContext(), "hola "+ dato.getText(), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //11. instancia a la referencias
        recyclerView = findViewById(R.id.recycleId);
        //12. indicamos que el recycleView sea una lista horizontal o vertical
        recyclerView.setLayoutManager(new LinearLayoutManager(this
                , LinearLayoutManager.VERTICAL, false));
       
        // para que se muestren los datos en foma de celdas
        // recyclerView.setLayoutManager(new GridLayoutManager(this, 2));//2 columnas
        //13. instancia de la lista de datos a presentar en el recycleView
        listaDatos = new ArrayList<String>();
        //14. llenamos la lista de datos
        listaDatos.add("primer dato");
        listaDatos.add("segundo dato");
        listaDatos.add("tercer dato");
        listaDatos.add("cuarto dato");
        listaDatos.add("quinto dato");
        listaDatos.add("sexto dato");
        listaDatos.add("octavo dato");
        listaDatos.add("noveno dato");
        listaDatos.add("decimo dato");

        //15. enviamos la lista con los datos al adaptador
        AdapterDatos adapter = new AdapterDatos(listaDatos);
        //16. indicamos al recycleView que adaptador usar
        recyclerView.setAdapter(adapter);
    }

}