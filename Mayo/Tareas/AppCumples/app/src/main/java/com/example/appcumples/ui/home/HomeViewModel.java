package com.example.appcumples.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.appcumples.Contacto;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<Contacto> contactoMutableLiveData = new MutableLiveData<>();

    public  void setContactoMutableLiveData(Contacto contacto){
        contactoMutableLiveData.setValue(contacto);
    }

    public LiveData<Contacto> getContacto(){
        return contactoMutableLiveData;
    }
}