package com.example.gestiondeinventario.ui.trabajo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrabajoViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public TrabajoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}