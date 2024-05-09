package com.example.examenresolucion23.ui.mostrar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MostrarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MostrarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}