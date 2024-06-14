package com.example.firebaserecyclerviewfotos;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ModeloViewHolder>{
    private Context mContext;
    Modelo modelo ;
    private StorageReference mStorageReference;

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
        mStorageReference = FirebaseStorage.getInstance().getReference();
     modelo = arrayListModelos.get(position);
     holder.mDescripcion.setText(modelo.getDescripcion());
     holder.mNombre.setText(modelo.getNombre());
     //cargar forto desde FIREBASE
        // Cargar foto desde Firebase Storage
        if (modelo.getFoto() != null && !modelo.getFoto().isEmpty()) {
            StorageReference fotoRef = mStorageReference.child(modelo.getFoto());
            Glide.with(mContext)
                    .load(fotoRef)
                    .placeholder(R.drawable.ic_launcher_background) // Imagen de placeholder
                    .error(android.R.drawable.stat_notify_error) // Imagen de error
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            Toast.makeText(mContext, "Failed to load image", Toast.LENGTH_SHORT).show();
                            return false;
                        }
                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            return false;
                        }
                    })
                    .into(holder.mImagenModelo);
        } else {
            holder.mImagenModelo.setImageResource(R.drawable.ic_launcher_background);
        }

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
//            Intent intent = new Intent(mContext,VistaModelo.class);
//            //extras para pasar los datos del producto seleccionado
//            mContext.startActivity(intent);
            Toast.makeText(mContext, "Selecciono: "+ modelo.getNombre(), Toast.LENGTH_SHORT).show();
        }
    }
}
