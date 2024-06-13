package com.example.firebaserecyclerviewfotos;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ModeloViewHolder>{
    private Context mContext;

    private int layoutResource;
    private ArrayList<Modelo> arrayListModelos;

    public Adaptador(Context mContext, int layoutResource, ArrayList<Modelo> arrayListModelos) {
        this.mContext = mContext;
        this.layoutResource = layoutResource;
        this.arrayListModelos = arrayListModelos;
    }

    @NonNull
    @Override
    public ModeloViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(layoutResource,parent,false);

        return new ModeloViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (arrayListModelos.size()>0){
            return arrayListModelos.size();
        }
        return 0;
    }

    @Override
    public void onBindViewHolder(@NonNull ModeloViewHolder holder, int position) {
     Modelo modelo = arrayListModelos.get(position);
     holder.mDescripcion.setText(modelo.getDescripcion());
     holder.mNombre.setText(modelo.getNombre());
     holder.mImagenModelo.setImageResource(R.drawable.ic_launcher_background);
    }

    public class ModeloViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView mNombre,mDescripcion;
        ImageView mImagenModelo;
        public ModeloViewHolder(@NonNull View itemView) {
            super(itemView);
            //cada elemento es cliclable
            itemView.setOnClickListener(this);

            mNombre = itemView.findViewById(R.id.textViewNombre);
            mDescripcion = itemView.findViewById(R.id.textViewDescripcion);
            mImagenModelo = itemView.findViewById(R.id.imageView);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mContext,VistaModelo.class);
            //extras para pasar los datos del producto seleccionado
            mContext.startActivity(intent);
        }
    }
}
