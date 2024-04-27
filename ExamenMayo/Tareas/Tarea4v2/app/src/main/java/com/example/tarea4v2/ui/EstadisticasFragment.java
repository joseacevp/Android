package com.example.tarea4v2.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.tarea4v2.ConexionSqlLite;
import com.example.tarea4v2.R;
import com.example.tarea4v2.Utilidades;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EstadisticasFragment extends Fragment {

    View view;
    Spinner usuarios, fechas, numero_tablas;
    TextView campo_fallos;
    ConexionSqlLite con;
    ArrayList<String> lista_usuarios, lista_fechas, lista_tablas, lista_fallos, lista_limpia;
    Button resetEsta;
    public static String tablaEst, fechaEsta, fallosEsta, usuarioEsta;
    ArrayAdapter<CharSequence> adapter2;
    ArrayAdapter<CharSequence> adapter3;

    public EstadisticasFragment() {
        // Required empty public constructor
    }


    public static EstadisticasFragment newInstance(String param1, String param2) {
        EstadisticasFragment fragment = new EstadisticasFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estadisticas, container, false);

        //instancia de la base de datos para consultar leer
        con = new ConexionSqlLite(getActivity(), "BaseDatosTarea4", null, 1);


        usuarios = view.findViewById(R.id.spinner_estadis_usuarios);
        fechas = view.findViewById(R.id.spinner_estadis_fechas);
        numero_tablas = view.findViewById(R.id.spinner_estadis_tablas);
        campo_fallos = view.findViewById(R.id.area_fallos_estadis);

        //forma de crear un adaptador para usar un ArrayList en vez de una lista de Item desde Archivo en Values
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter(getActivity(),
                android.R.layout.simple_spinner_item, cargarDatosUsuariosSQL());
        usuarios.setAdapter(adapter);


        //evento del spinner captura la seleccion entre la lista de opciones del Spinner
        usuarios.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterViewUsuario, View view, int posicionUsuario, long l) {
                //forma de crear un adaptador para usar un ArrayList en vez de una lista de Item desde Archivo en Values
                usuarioEsta = adapterViewUsuario.getItemAtPosition(posicionUsuario).toString();
                guardarDatosEstadisticos();
                adapter2 = new ArrayAdapter(getActivity(),
                        android.R.layout.simple_spinner_item, cargarDatosFechasSQL(adapterViewUsuario.getItemAtPosition(posicionUsuario).toString()));
                fechas.setAdapter(adapter2);

                fechas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterViewfechas, View view, int posicionFecha, long id) {
                        fechaEsta = adapterViewfechas.getItemAtPosition(posicionFecha).toString();
                        guardarDatosEstadisticos();
                        //forma de crear un adaptador para usar un ArrayList en vez de una lista de Item desde Archivo en Values
                        adapter3 = new ArrayAdapter(getActivity(),
                                android.R.layout.simple_spinner_item, cargarDatosTablasSQL(adapterViewfechas.getItemAtPosition(posicionFecha).toString(), usuarioEsta));
                        numero_tablas.setAdapter(adapter3);
                        numero_tablas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterViewTabla, View view, int positionTabla, long id) {
                                tablaEst = adapterViewTabla.getItemAtPosition(positionTabla).toString();
                                guardarDatosEstadisticos();
                                //implementar fallos de usuario fecha y tabla
                                //select fallos from partidas where usuario = usuario and fecha = fecha and tabla = tabla
                                campo_fallos.setText(cargarDatosFallosSQL(adapterViewTabla.getItemAtPosition(positionTabla).toString(),fechaEsta,usuarioEsta));
                                fallosEsta = cargarDatosFallosSQL(adapterViewTabla.getItemAtPosition(positionTabla).toString(),fechaEsta,usuarioEsta);
                                guardarDatosEstadisticos();
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        resetEsta = view.findViewById(R.id.botonResetEstadisticas);
        resetEsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetearEstadisticas();
                adapter.clear(); // Elimina todos los elementos del adaptador
                adapter.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
                adapter2.clear(); // Elimina todos los elementos del adaptador
                adapter2.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
                adapter3.clear(); // Elimina todos los elementos del adaptador
                adapter3.notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
                campo_fallos.setText("");
            }
        });


        return view;
    }

    private ArrayList<String> cargarDatosUsuariosSQL() {
        lista_usuarios = new ArrayList<>();
        String nombre = "";
        SQLiteDatabase db = con.getReadableDatabase();
        //parametros de la consulta
        String[] parametros = {" usuario "};
        try {
            Cursor cursor = db.rawQuery("SELECT  DISTINCT " + Utilidades.USUARIO + " FROM " + Utilidades.TABLA_PARTIDAS, null);

            while (cursor.moveToNext()) {
                String usuario = cursor.getString(0);
                lista_usuarios.add(usuario);
            }
            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Sin usuarios registrados", Toast.LENGTH_SHORT).show();
        }
        //COMPROBACION DE DATOS RECUPERADOS
        for (int i = 0; i < lista_usuarios.size(); i++) {
            nombre += lista_usuarios.get(i).toString();
        }
        System.out.println(nombre);
        //
        return lista_usuarios;
    }

    private ArrayList<String> cargarDatosFechasSQL(String usuario) {
        lista_fechas = new ArrayList<>();
        String nombre = "";
        SQLiteDatabase db = con.getReadableDatabase();
        //parametros de la consulta
        String[] parametros = {usuario};
        try {
            Cursor cursor = db.rawQuery("SELECT DISTINCT "
                    + Utilidades.FECHA
                    + " FROM "
                    + Utilidades.TABLA_PARTIDAS
                    + " WHERE "
                    + Utilidades.USUARIO
                    + " =?", parametros);

            while (cursor.moveToNext()) {
                String fecha = cursor.getString(0);
                lista_fechas.add(fecha);
            }

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Sin fechas registrados", Toast.LENGTH_SHORT).show();
        }
        //COMPROBACION DE DATOS RECUPERADOS
        for (int i = 0; i < lista_fechas.size(); i++) {
            nombre += lista_fechas.get(i).toString();
        }
        System.out.println(nombre);
        return lista_fechas;
    }

    private ArrayList<String> cargarDatosTablasSQL(String fecha, String nombre) {
        System.out.println(nombre + " cargado");
        lista_tablas = new ArrayList<>();
        String dato = "";
        SQLiteDatabase db = con.getReadableDatabase();
        //parametros de la consulta
        String[] parametros = {fecha, nombre};
        try {
            Cursor cursor = db.rawQuery("SELECT DISTINCT "
                    + Utilidades.NUMERO_TABLA
                    + " FROM "
                    + Utilidades.TABLA_PARTIDAS
                    + " WHERE "
                    + Utilidades.FECHA
                    + "=?AND "
                    + Utilidades.USUARIO
                    + "=?", parametros);

            while (cursor.moveToNext()) {
                String tabla = cursor.getString(0);
                lista_tablas.add(tabla);
            }

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Sin tablas registrados", Toast.LENGTH_SHORT).show();
        }
        //COMPROBACION DE DATOS RECUPERADOS
        for (int i = 0; i < lista_tablas.size(); i++) {
            dato += lista_tablas.get(i).toString();
        }
        System.out.println(dato);
        //
        return lista_tablas;
    }

    private String cargarDatosFallosSQL(String numero, String fecha, String usuario) {
        lista_fallos = new ArrayList<>();
        String dato = "";
        SQLiteDatabase db = con.getReadableDatabase();
        //parametros de la consulta
        String[] parametros = {numero, fecha, usuario};
        try {
            Cursor cursor = db.rawQuery("SELECT  "
                    + Utilidades.FALLOS
                    + " FROM "
                    + Utilidades.TABLA_PARTIDAS
                    + " WHERE "
                    + Utilidades.NUMERO_TABLA
                    + " =?AND "
                    + Utilidades.FECHA
                    + " =?AND "
                    + Utilidades.USUARIO
                    + " =?", parametros);

            while (cursor.moveToNext()) {
                String tabla = cursor.getString(0);
                lista_fallos.add(tabla);
            }

            cursor.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Sin usuarios registrados", Toast.LENGTH_SHORT).show();
        }
        //COMPROBACION DE DATOS RECUPERADOS
        for (int i = 0; i < lista_fallos.size(); i++) {
            dato += lista_fallos.get(i).toString();
        }
        System.out.println(dato);
        //
        return dato;
    }

    private void resetearEstadisticas() {
        //crea la base de datos
        ConexionSqlLite conn = new ConexionSqlLite(getActivity(),
                "BaseDatosTarea4", null, 1);
        SQLiteDatabase db = conn.getWritableDatabase();
        try {
            // Ejecutar la consulta DELETE
            db.delete(Utilidades.TABLA_PARTIDAS, null, null);

            // Cerrar la conexión de la base de datos
            db.close();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Fallo al borrar partida.", Toast.LENGTH_SHORT).show();

        }
    }

    private void guardarDatosEstadisticos() {
        SharedPreferences estadisticas = getContext().getSharedPreferences
                ("estadisticas", Context.MODE_PRIVATE);
//tablaEst, fechaEsta, fallosEsta, usuarioEsta;
        SharedPreferences.Editor editor = estadisticas.edit();
        editor.putString("usuario", usuarioEsta);
        editor.putString("tabla", tablaEst);
        editor.putString("fecha", fechaEsta);
        editor.putString("fallos", fallosEsta);

        editor.commit();
    }
}