package com.example.examenresolucion23;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorAleatorios extends RecyclerView.Adapter<AdaptadorAleatorios.ViewHolderDatos> {

    private ArrayList<Contacto> listaContactos;
    private Context context;

    public AdaptadorAleatorios(ArrayList<Contacto> listaContactos, Context context) {
        this.listaContactos = listaContactos;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linea_contactos_aleatorios, parent, false);
        return new AdaptadorAleatorios.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.setAsignarDatos(listaContactos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView id, nombre, apellidos, dni;
        ImageView fotoContacto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.id_f);
            nombre = itemView.findViewById(R.id.texto_nombre_f);
            apellidos = itemView.findViewById(R.id.texto_apellidos_f);
            dni = itemView.findViewById(R.id.dni_f);
            fotoContacto = itemView.findViewById(R.id.foto_f);

        }

        public void setAsignarDatos(Contacto contacto) {
            id.setText("id: " + contacto.getId());
            nombre.setText("Nombre: " + contacto.getNombre());
            apellidos.setText("Apellidos: " + contacto.getApellidos());
            dni.setText("DNI: " + contacto.getDni());
            fotoContacto.setImageResource(android.R.drawable.ic_menu_camera);

            fotoContacto.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View v) {

                                                    Toast.makeText(context, "Guardado: " + contacto.getNombre(), Toast.LENGTH_SHORT).show();
                                                    ConexionSqlLite conn = new ConexionSqlLite(context.getApplicationContext(),
                                                            "BaseDatos23", null, 1);
                                                    SQLiteDatabase db = conn.getWritableDatabase();
                                                    try {
                                                        String insert = "INSERT INTO " + Utilidades.TABLA_ALEATORIOS
                                                                + " ( "
                                                                + Utilidades.ID + ","
                                                                + Utilidades.NOMBREALE + ","
                                                                + Utilidades.APELLIDOS + ","
                                                                + Utilidades.DNI
                                                                + ")"
                                                                + "VALUES ( '" + contacto.getId() + "','"
                                                                + contacto.getNombre() + "','"
                                                                + contacto.getApellidos() + "','"
                                                                + contacto.getDni() + "')";

                                                        db.execSQL(insert);
                                                        db.close();
                                                    } catch (Exception e) {
                                                        Toast.makeText(context.getApplicationContext(), "Fallo al registrar partida.", Toast.LENGTH_SHORT).show();

                                                    }

                                                }
                                            }
            );
        }


    }


}

