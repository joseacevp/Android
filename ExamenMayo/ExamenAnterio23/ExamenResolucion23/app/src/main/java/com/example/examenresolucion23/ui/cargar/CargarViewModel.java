package com.example.examenresolucion23.ui.cargar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CargarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public CargarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}