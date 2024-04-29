package com.example.viewmodel.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class GalleryViewModel extends ViewModel {

    private MutableLiveData<String> dato = new MutableLiveData<>();


    public LiveData<String> getDato() {
        return dato;
    }

    public void setDato(String value) {
        dato.setValue(value);
    }
    public void sumar(Integer numero){
        numero++;
        setDato(numero.toString());
    }
}