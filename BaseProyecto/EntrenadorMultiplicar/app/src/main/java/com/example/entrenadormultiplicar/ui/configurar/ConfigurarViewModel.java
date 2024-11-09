package com.example.entrenadormultiplicar.ui.configurar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ConfigurarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ConfigurarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}