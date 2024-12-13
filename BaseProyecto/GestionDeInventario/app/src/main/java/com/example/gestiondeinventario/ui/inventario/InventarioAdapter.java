package com.example.gestiondeinventario.ui.inventario;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.gestiondeinventario.MainActivity;
import com.example.gestiondeinventario.R;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebase;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseImpl;
import com.example.gestiondeinventario.ui.firebase.Materiales;

import java.util.List;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.AdaptadorViewHolder> {
    //variables
    private InventarioViewModel inventarioViewModel;

    private AccesoFirebase accesoFirebase;
    private Materiales materialSeleccionado;


    public InventarioAdapter(List<Materiales> listaMateriales, InventarioViewModel inventarioViewModel) {
        this.listaMateriales = listaMateriales;
        this.inventarioViewModel = inventarioViewModel;
    }

    public Materiales getMaterialSeleccionado() {
        return materialSeleccionado;
    }


    public void setMaterialSeleccionado(Materiales materialSeleccionado) {
        this.materialSeleccionado = materialSeleccionado;
    }

    private List<Materiales> listaMateriales;


    public List<Materiales> getListaMateriales() {
        return listaMateriales;
    }

    public void setListaMateriales(List<Materiales> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }



    @NonNull
    @Override
    public InventarioAdapter.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdaptadorViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_recycler_inventario, parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull InventarioAdapter.AdaptadorViewHolder holder, int position) {
        holder.bindAdaptador(listaMateriales.get(position));
    }

    @Override
    public int getItemCount() {
        return listaMateriales.size();
    }


    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        ImageView foto;
        TextView nombre, codigo, localizacion, uso;
        CardView cardView;
        private AccesoFirebase accesoFirebase;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.imageViewItemInventario);
            nombre = itemView.findViewById(R.id.textViewNombreItemInv);
            codigo = itemView.findViewById(R.id.textViewCodItemInv);
            localizacion = itemView.findViewById(R.id.textViewLocaliItemInv);
            uso = itemView.findViewById(R.id.textViewUsoItemInv);
            cardView = itemView.findViewById(R.id.cardViewInventario);
            accesoFirebase = new AccesoFirebaseImpl();
        }

        public void bindAdaptador(Materiales materiales) {

            // Cargar imagen desde Firebase Storage usando Glide
            Glide.with(itemView.getContext())
                    .load(materiales.getFotoUri()) // URL de Firebase Storage
                    .placeholder(R.drawable.ic_dashboard_black_24dp) // Imagen de carga
                    .into(foto);

            nombre.setText(materiales.getNombre());
            codigo.setText(materiales.getCodigo());
            localizacion.setText(materiales.getLocalizacion());
            uso.setText(materiales.getUso());


            cardView.setOnClickListener(v -> {
                Context context = v.getContext();
                materialSeleccionado = materiales;
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialogoDetalle = layoutInflater.inflate(R.layout.dialog_detalles_material,null);
                // Crea el diálogo
                // Encuentra las vistas dentro del diseño personalizado
                ImageView imageView = dialogoDetalle.findViewById(R.id.imageViewFotoDetalle);
                TextView dialogNombre = dialogoDetalle.findViewById(R.id.textViewNombreItemDet);
                TextView dialogCodigo = dialogoDetalle.findViewById(R.id.textViewCodItemDet);
                TextView dialogLocalizacion = dialogoDetalle.findViewById(R.id.textViewLocaliItemDet);
                TextView dialogUso = dialogoDetalle.findViewById(R.id.textViewUsoItemDet);
                TextView dialogCantidad = dialogoDetalle.findViewById(R.id.textViewCantItemDet);
                EditText ediIncreCantidad = dialogoDetalle.findViewById(R.id.editTextIncreCantDet);


                // Establece los valores en las vistas del diálogo
                // Cargar imagen desde Firebase Storage usando Glide
                Glide.with(itemView.getContext())
                        .load(materiales.getFotoUri()) // URL de Firebase Storage
                        .placeholder(R.drawable.ic_dashboard_black_24dp) // Imagen de carga
                        .into(imageView);
                dialogNombre.setText("Nombre: " + materiales.getNombre());
                dialogCodigo.setText("Código: " + materiales.getCodigo());
                dialogLocalizacion.setText("Localización: " + materiales.getLocalizacion());
                dialogUso.setText("Uso: " + materiales.getUso());
                dialogCantidad.setText("Cantidad disponible: " + materiales.getCantidad());


                // Configura y muestra el diálogo
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setView(dialogoDetalle)
                        .setPositiveButton("Aceptar", (dialog, which) -> {
                            // Acción al presionar "Aceptar" añade la cantidad de material a la base de datos
                            if(ediIncreCantidad.getText().length()!=0){
                                accesoFirebase.actualizarCantidad(ediIncreCantidad.getText().toString(),materiales.getCodigo(),context);
                            }


                            dialog.dismiss();
                        });

                AlertDialog dialog = builder.create();
                dialog.show();

                Toast.makeText(context, "SELECCIONADO: " + materiales.getNombre(), Toast.LENGTH_SHORT).show();
            });
        }
    }

}
