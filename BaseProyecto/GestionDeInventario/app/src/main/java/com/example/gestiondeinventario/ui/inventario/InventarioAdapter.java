package com.example.gestiondeinventario.ui.inventario;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.gestiondeinventario.R;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseMateriales;
import com.example.gestiondeinventario.ui.firebase.AccesoFirebaseImpl;
import com.example.gestiondeinventario.ui.firebase.Materiales;

import java.util.List;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.AdaptadorViewHolder> {
    //variables
    private AccesoFirebaseMateriales accesoFirebase;
    private Materiales materialSeleccionado;

    public InventarioAdapter(List<Materiales> listaMateriales) {
        this.listaMateriales = listaMateriales;
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
        ProgressBar progressBar;
        private AccesoFirebaseMateriales accesoFirebase;

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressBarItemInv);
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
            progressBar.setVisibility(View.VISIBLE);
            Glide.with(itemView.getContext())
                    .load(materiales.getFotoUri()) // URL de Firebase Storage
                    .placeholder(null) // Imagen de carga
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    }).into(foto);

            nombre.setText(materiales.getNombre());
            codigo.setText(materiales.getCodigo());
            localizacion.setText(materiales.getLocalizacion());
            uso.setText(materiales.getUso());
            cardView.setOnClickListener(v -> {
                Context context = v.getContext();
                materialSeleccionado = materiales;
                LayoutInflater layoutInflater = LayoutInflater.from(context);
                View dialogoDetalle = layoutInflater.inflate(R.layout.dialog_detalles_material, null);
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
                            if (ediIncreCantidad.getText().length() != 0) {
                                accesoFirebase.actualizarCantidadMateriales(ediIncreCantidad.getText().toString(), materiales.getCodigo(), context);
                            }
                            dialog.dismiss();
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
//                Toast.makeText(context, "SELECCIONADO: " + materiales.getNombre(), Toast.LENGTH_SHORT).show();
            });
        }
    }

}
