package com.example.examenresolucion23;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    private ArrayList<ContactoReal> listaContactos;
    private Context context;

    public AdapterDatos(ArrayList<ContactoReal> listaContactos, Context context) {
        this.listaContactos = listaContactos;
        this.context = context;
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView nombre, fecha, telefono;
        ImageView fotoContacto;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.texto_nombre);
            fecha = itemView.findViewById(R.id.texto_fecha);
            telefono = itemView.findViewById(R.id.texto_telefono);
            fotoContacto = itemView.findViewById(R.id.fotoContactoReal);

        }

        public void setAsignarDatos(ContactoReal contacto) {
            nombre.setText(contacto.getNombre());
            fecha.setText("Fecha de Nacimiento: " + contacto.getFecha());
            telefono.setText("Teléfono: " + contacto.getTelefono());
            if (contacto.getFotoUri() != null) {
                fotoContacto.setImageURI(Uri.parse(contacto.getFotoUri()));
            } else {
                fotoContacto.setImageResource(android.R.drawable.ic_menu_camera);
            }
            fotoContacto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "Selecciono: "+contacto.getNombre(), Toast.LENGTH_SHORT).show();
                    ConexionSqlLite conn = new ConexionSqlLite(context.getApplicationContext(),
                            "BaseDatos23", null, 1);
                    SQLiteDatabase db = conn.getWritableDatabase();
                    //String nombre, String telefono, String fecha, Uri fotoUri
                    try {
                        String insert = "INSERT INTO " + Utilidades.TABLA_CONTACTOS
                                + " ( "
                                + Utilidades.NOMBRE + ","
                                + Utilidades.TELEFONO + ","
                                + Utilidades.FOTO + ","
                                + Utilidades.FECHA
                                + ")"
                                + "VALUES ( '" + contacto.getNombre()+ "','"
                                + contacto.getTelefono() + "','"
                                + contacto.getFotoUri() + "','"
                                + contacto.getFecha() + "')";

                        db.execSQL(insert);
                        db.close();
                    } catch (Exception e) {
                        Toast.makeText(context.getApplicationContext(), "Fallo al registrar partida.", Toast.LENGTH_SHORT).show();

                    }

                }

            });
        }
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linea_contacto, parent, false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos holder, int position) {
        holder.setAsignarDatos(listaContactos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }


}

