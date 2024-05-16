package com.example.examen23.ui.detalles;

import static android.app.Activity.RESULT_OK;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.examen23.Contacto;
import com.example.examen23.R;
import com.example.examen23.databinding.FragmentDetallesBinding;
import com.example.examen23.ui.contactos.ContactosViewModel;

public class DetallesFragment extends Fragment {

    private DetallesViewModel mViewModel;
    static final int SELECCIONAR_CONTACTO = 1;
    private FragmentDetallesBinding binding;
    ContactosViewModel contactosViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = FragmentDetallesBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        contactosViewModel = new ViewModelProvider(requireActivity()).get(ContactosViewModel.class);

        Contacto contacto = contactosViewModel.getContacto();
        if (contacto != null) {
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

        binding.botonEditarDetalle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Uri contactUri = contacto.getData();
//                //ver el contacto -> el intent recibe además,
//                // el URI del contacto seleccionado
//                startActivity(new Intent(Intent.ACTION_VIEW, contactUri));
            }
        });

        return root;
    }

    public void abrirContactos() {
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, SELECCIONAR_CONTACTO);
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
}