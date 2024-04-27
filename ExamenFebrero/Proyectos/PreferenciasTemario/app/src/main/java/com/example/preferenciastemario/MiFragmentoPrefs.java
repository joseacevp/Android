package com.example.preferenciastemario;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

public class MiFragmentoPrefs extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencias,rootKey);
    }
}
