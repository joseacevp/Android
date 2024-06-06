package com.example.appcumples.ui.home;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.appcumples.Contacto;
import com.example.appcumples.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    static final int SELECCIONAR_CONTACTO = 1;
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel ;
    private Contacto contactoRec = new Contacto();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //datos de los contactos del telefono almacenados en viewModel set añade los datos
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);
        //get consulta los datos de los contactos del telefono almacendos en viewModel
        homeViewModel.getContacto().observe(getViewLifecycleOwner(), new Observer<Contacto>() {
            @Override
            public void onChanged(Contacto contacto) {
                if (contacto != null) {
                    contactoRec = contacto;
                    // Aquí puedes usar el contacto como desees
                    binding.textoFechaDetalle.setText("Fecha de Nacimiento: " + contacto.getFecha());
                    binding.textoNombreDetalle.setText(contacto.getNombre());
                    if (contacto.getFoto() != null) {
                        binding.fotoDetalle.setImageURI(Uri.parse(contacto.getFoto()));
                    } else {
                        binding.fotoDetalle.setImageResource(android.R.drawable.ic_menu_camera);
                    }
                } else {
                    Toast.makeText(getContext(), "sin datos", Toast.LENGTH_SHORT).show();
                }


            }

        });
        binding.botonEditarDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("contacto recuperado:"+contactoRec.getUri());
                if (contactoRec != null && contactoRec.getUri() != null) {
                    Uri contactUri = contactoRec.getUri();
                    // Abrir el contacto en la aplicación de contactos
                    Intent intent = new Intent(Intent.ACTION_VIEW, contactUri);
                    startActivity(intent);
                } else {
                    Toast.makeText(getContext(), "No hay contacto seleccionado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        return root;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SELECCIONAR_CONTACTO) {
            if (resultCode == RESULT_OK) {
                // El usuario ha seleccionado un contacto
                // Puedes manejar el contacto seleccionado aquí
                Toast.makeText(getContext(), "Contacto seleccionado", Toast.LENGTH_SHORT).show();
                Uri contactUri = data.getData();
                //ver el contacto -> el intent recibe además,
                // el URI del contacto seleccionado
                startActivity(new Intent(Intent.ACTION_VIEW, contactUri));
            } else {
                // El usuario ha cancelado la selección
                Toast.makeText(getContext(), "Selección cancelada", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}