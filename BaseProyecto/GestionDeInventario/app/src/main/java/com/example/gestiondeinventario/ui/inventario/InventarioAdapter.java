package com.example.gestiondeinventario.ui.inventario;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class InventarioAdapter extends RecyclerView.Adapter<InventarioAdapter.AdaptadorViewHolder> {
    //variables
    private InventarioViewModel inventarioViewModel;




    @NonNull
    @Override
    public InventarioAdapter.AdaptadorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull InventarioAdapter.AdaptadorViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class AdaptadorViewHolder extends RecyclerView.ViewHolder {
        public AdaptadorViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
