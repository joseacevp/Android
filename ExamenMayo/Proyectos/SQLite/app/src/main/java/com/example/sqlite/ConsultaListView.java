package com.example.sqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sqlite.entidades.Usuario;
import com.example.sqlite.utilidades.Utilidades;

import java.time.OffsetDateTime;
import java.util.ArrayList;

public class ConsultaListView extends AppCompatActivity {

    ListView vistaLista;
    ConexionSQLiteHelper con;
    ArrayList<Usuario> listaUsuarios = null;
    ArrayList<String> listaString = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_list_view);
        vistaLista = (ListView) findViewById(R.id.listVista);
        con = new ConexionSQLiteHelper(getApplicationContext(),"db_usuarios",null,1);


        recupertarUsuariosBd();

        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(),
                android.R.layout.simple_list_item_1,listaString);
        vistaLista.setAdapter(adapter);

        vistaLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicion, long l) {
                String informacion = "id: " + listaUsuarios.get(posicion).getId().toString() + "\n";
                informacion += "nombre: " + listaUsuarios.get(posicion).getNombre()+ "\n";
//                informacion += "telefono: " + listaUsuarios.get(posicion).getTelefono()+ "\n";
                Toast.makeText(getApplicationContext(), informacion, Toast.LENGTH_SHORT).show();

                //recuperamos el usuario seleccionado en el evento
                Usuario user = listaUsuarios.get(posicion);
                //creamos el intencion para lanzar la actividad detalle Persona
                Intent eventoDetallePersona = new Intent(ConsultaListView.this,DetallePersona.class);
                //creamos el bundle para enviar en la intencion el objeto serializable usuario
                Bundle bundle = new Bundle();
                bundle.putSerializable("Persona",user);
                //indicamos a la intencion que envie el bundle con el objeto
                eventoDetallePersona.putExtras(bundle);
                startActivity(eventoDetallePersona);
            }
        });
    }

    private void recupertarUsuariosBd() {
        SQLiteDatabase bd = con.getReadableDatabase();

        Usuario usuario;
        listaUsuarios = new ArrayList<Usuario>();

        Cursor cursor = bd.rawQuery("SELECT * FROM " + Utilidades.TABLA_USUARIO , null);

        while (cursor.moveToNext()){

            usuario = new Usuario();
            usuario.setId(cursor.getInt(0));
            usuario.setNombre(cursor.getString(1));
            usuario.setTelefono(cursor.getString(2));
            Log.i("info",usuario.getId().toString());
            Log.i("info",usuario.getNombre());
            Log.i("info",usuario.getTelefono());

            listaUsuarios.add(usuario);
        }
        bd.close();
        recuperarLista();

    }

    private void recuperarLista() {
        listaString = new ArrayList<String>();
        for (int i = 0; i < listaUsuarios.size(); i++){
            listaString.add(listaUsuarios.get(i).getId().toString() +
                    " " + listaUsuarios.get(i).getNombre() );

        }
    }
}