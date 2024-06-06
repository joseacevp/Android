package com.example.examen23.ui.contactos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.examen23.Contacto;

import java.util.List;

public class ContactosViewModel extends ViewModel {

    private Contacto contacto;

    public void setContacto(Contacto contacto) {
        this.contacto = contacto;
    }

    public Contacto getContacto() {
        return contacto;
    }
}