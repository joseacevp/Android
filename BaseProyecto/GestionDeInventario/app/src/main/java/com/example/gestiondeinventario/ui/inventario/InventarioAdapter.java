package com.example.gestiondeinventario.ui.inventario;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

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

        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
            foto = itemView.findViewById(R.id.imageViewItemInventario);
            nombre = itemView.findViewById(R.id.textViewNombreItemInv);
            codigo = itemView.findViewById(R.id.textViewCodItemInv);
            localizacion = itemView.findViewById(R.id.textViewLocaliItemInv);
            uso = itemView.findViewById(R.id.textViewUsoItemInv);
            cardView = itemView.findViewById(R.id.cardViewInventario);

        }

        public void bindAdaptador(Materiales materiales) {
            // Instanciar la clase que implementa la interfaz
//            accesoFirebase = new AccesoFirebaseImpl();
            foto.setImageResource(materiales.getFoto());
            nombre.setText(materiales.getNombre());
            codigo.setText(materiales.getCodigo());
            localizacion.setText(materiales.getLocalizacion());
            uso.setText(materiales.getUso());
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    materialSeleccionado = materiales;
                    Toast.makeText(context, "SELECCIONADO " + materiales.getFoto() + materiales.getNombre(), Toast.LENGTH_SHORT).show();
                    // inventarioViewModel.setMaterial(materialSeleccionado);
                    //accesoFirebase.guardarDato(materialSeleccionado);
                    // Volver a la actividad de inicio
//                    Intent intent = new Intent(context, Inicio.class);
//                    // Si quieres enviar datos a la actividad de inicio, puedes añadir extras al Intent
//                    intent.putExtra("entrenadorSeleccionado", entrenador.getNombre());
//                    context.startActivity(intent);
                }
            });
        }
    }
}
