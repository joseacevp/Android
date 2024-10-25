package com.example.entrenadormultiplicar.ui.entrenar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntrenarViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public EntrenarViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}