package com.example.juegomatematicas.ui.secectorentrenador;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EntrenadorViewModel extends ViewModel {
    private MutableLiveData<String> selectedFotoEntreString = new MutableLiveData<>();

    public void setSelectedFotoEntrString(String dateString) {
        selectedFotoEntreString.setValue(dateString);
    }

    public MutableLiveData<String> getSelectedFotoEntreString() {
        return selectedFotoEntreString;
    }
}
