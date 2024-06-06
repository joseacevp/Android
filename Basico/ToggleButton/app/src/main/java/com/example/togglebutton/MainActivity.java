package com.example.togglebutton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ToggleButton tgb1,tgb2;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tgb1 = findViewById(R.id.toggleB1);
        tgb1.setOnClickListener(this);
        tgb2= findViewById(R.id.toggleB2);
        tgb2.setOnClickListener(this);
        textView1 = findViewById(R.id.etiqueta);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.toggleB1){
            if (tgb1.isChecked()){
                textView1.setText("Estado On ");
            }else
            {
                textView1.setText("Estado Off");
            }
        }
        if (view.getId()==R.id.toggleB2){
            if (tgb2.isChecked()){
                textView1.setText("Estado Activo");
            }else{
                textView1.setText("Estado Inactivo");
            }
        }
    }
}