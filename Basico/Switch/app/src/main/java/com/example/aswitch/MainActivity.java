package com.example.aswitch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    Switch switch1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.etiqueta);
        switch1 = findViewById(R.id.switch1);
        switch1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.switch1){
            if (switch1.isChecked()){
                textView.setText("Estado: Activado");
            }else {
                textView.setText("Estado: Inactivo");
            }
        }

    }
}