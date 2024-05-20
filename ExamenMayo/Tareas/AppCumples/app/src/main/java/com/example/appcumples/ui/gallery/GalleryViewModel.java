package com.example.appcumples.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appcumples.Contacto;

import java.util.ArrayList;


public class GalleryViewModel extends ViewModel {

    private MutableLiveData<ArrayList<Contacto>> listaContactosTelefono = new MutableLiveData<>();

    public void setListaContactosTelefono(ArrayList<Contacto> contactos) {
        listaContactosTelefono.setValue(contactos);
    }

    public LiveData<ArrayList<Contacto>> getListaContactosTelefono() {
        return listaContactosTelefono;
    }
}