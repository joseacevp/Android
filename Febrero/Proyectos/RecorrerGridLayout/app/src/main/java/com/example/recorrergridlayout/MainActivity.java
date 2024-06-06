package com.example.recorrergridlayout;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anadirHijos(18);
        recorrerHijos();
    }

    private void anadirHijos(int numeroBotones) {
        GridLayout g = (GridLayout) findViewById(R.id.layout);
        Button b;
        for (int i = 0; i < numeroBotones; i++) {
            b = new Button(this);
            b.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            if (i != 17) {
                b.setText("Boton " + (i + 1));
            } else {
                b.setText("RESET");
            }
            b.setId(View.generateViewId());
            g.addView(b, i);
        }
    }

    private void recorrerHijos() {
        View v;
        GridLayout g = (GridLayout) findViewById(R.id.layout);
        for (int i = 0; i < g.getChildCount(); i++) {
            v = g.getChildAt(i);
            if (v instanceof Button) {
                Button b = (Button) v;
                b.setBackgroundColor(Color.rgb(i * 10, i * 50, i * 30));
                b.setTextColor(Color.WHITE);
                b.setOnClickListener(this);
            }
        }

    }


    @Override
    public void onClick(View view) {
        Button b;
        if (view instanceof Button) {
            b = (Button) view;
            if (b.getText().equals("RESET")) {
                recorrerHijos();
            } else {
                b.setBackgroundColor(Color.WHITE);
            }
        }
    }
}