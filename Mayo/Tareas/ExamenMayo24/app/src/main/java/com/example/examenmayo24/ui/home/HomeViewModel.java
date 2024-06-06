package com.example.examenmayo24.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> selectedDateString = new MutableLiveData<>();

    public void setSelectedDateString(String dateString) {
        selectedDateString.setValue(dateString);
    }

    public MutableLiveData<String> getSelectedDateString() {
        return selectedDateString;
    }
}