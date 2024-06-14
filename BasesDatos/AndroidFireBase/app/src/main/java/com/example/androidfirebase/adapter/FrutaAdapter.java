package com.example.androidfirebase.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidfirebase.R;
import com.example.androidfirebase.modelo.Fruta;

import java.util.List;

public class FrutaAdapter extends RecyclerView.Adapter<FrutaAdapter.FrutaHolder> {

    List<Fruta> lista;
    int layout;
    Activity activity;
    public FrutaAdapter(List<Fruta> lista, int layout, Activity activity) {
        this.lista = lista;
        this.layout = layout;
        this.activity = activity;
    }

    public class FrutaHolder extends RecyclerView.ViewHolder {
        TextView txtId, txtNombre, txtPrecio;

        public FrutaHolder(@NonNull View itemView) {
            super(itemView);
            txtId = itemView.findViewById(R.id.item_Id);
            txtNombre = itemView.findViewById(R.id.item_Nombre);
            txtPrecio = itemView.findViewById(R.id.item_Precio);
        }
    }
    @NonNull
    @Override
    public FrutaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater
                .from(parent.getContext()).inflate(layout,parent,false);

        return new FrutaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FrutaHolder holder, int position) {
        Fruta fruta = lista.get(position);
        holder.txtId.setText("ID: "+fruta.getId());
        holder.txtNombre.setText("Nombre: "+fruta.getNombre());
        holder.txtPrecio.setText("Precio: "+String.valueOf(fruta.getPrecio()));

    }

    @Override
    public int getItemCount() {
        return lista.size();
    }


}
