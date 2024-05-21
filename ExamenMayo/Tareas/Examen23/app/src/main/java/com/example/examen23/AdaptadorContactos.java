package com.example.examen23;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.examen23.ui.contactos.ContactosViewModel;
import com.example.examen23.ui.detalles.DetallesFragment;

import java.util.ArrayList;

public class AdaptadorContactos extends RecyclerView.Adapter<AdaptadorContactos.ViewHolderContactos> {

    private ArrayList<Contacto> listaContactos;

    private Context context;
    public static final int SELECCIONA_TIPO_NOTIFICACION = 1;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(Contacto contacto);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }
    public AdaptadorContactos(ArrayList<Contacto> listaContactos, Context context) {
        this.listaContactos = listaContactos;
        this.context = context;
    }

    @NonNull
    @Override
    public AdaptadorContactos.ViewHolderContactos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.linea_contacto, parent, false);
        return new ViewHolderContactos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdaptadorContactos.ViewHolderContactos holder, int position) {
        holder.setAsignarDatos(listaContactos.get(position));
    }

    @Override
    public int getItemCount() {
        return listaContactos.size();
    }

    public class ViewHolderContactos extends RecyclerView.ViewHolder {
        //private String nombre,fecha,notificacion,foto,telefono;
        TextView nombre, fecha, notificacion, telefono;
        ImageView fotoContacto;


        public ViewHolderContactos(@NonNull View itemView) {
            super(itemView);

            nombre = itemView.findViewById(R.id.textoNombreContacto);
            fecha = itemView.findViewById(R.id.textoFechaContacto);
            notificacion = itemView.findViewById(R.id.textoTipoAvisoContacto);
            telefono = itemView.findViewById(R.id.textoTelefonoContacto);
            fotoContacto = itemView.findViewById(R.id.fotoContacto);

        }

        public void setAsignarDatos(Contacto contacto) {
            nombre.setText(contacto.getNombre());
            fecha.setText("Fecha de Nacimiento: " + contacto.getFecha());
            notificacion.setText("Aviso: " + contacto.getTipo());
            telefono.setText("Teléfono: " + contacto.getTelefono());

            if (contacto.getFoto() != null) {
                fotoContacto.setImageURI(Uri.parse(contacto.getFoto()));
            } else {
                fotoContacto.setImageResource(android.R.drawable.ic_menu_camera);
            }
            fotoContacto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(contacto);
                    }
                }
            });
        }
    }

}
