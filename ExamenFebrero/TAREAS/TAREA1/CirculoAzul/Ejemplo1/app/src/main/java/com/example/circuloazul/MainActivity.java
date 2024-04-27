package com.example.circuloazul;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintLayoutStates;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //falta en el codigo original
        ConstraintLayout layout = findViewById(R.id.layout);// instancia del Layout creado en el archivo XML
        EjemploView lienzo = new EjemploView(this);// instancia de la clase EjemploView que
        // contien el lienzo o Canvas y el pinciel o Paint
        layout.addView(lienzo);//añadimos el diseño creado en la clase al Layout 
    }
    public class EjemploView extends View {

        public EjemploView(Context context) {
            super(context);
        }
        protected void onDraw(Canvas canvas){
            Paint pincel = new Paint();
            pincel.setColor(Color.BLUE);
            pincel.setStrokeWidth(8);
            pincel.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(150,150,100,pincel);
        }
    }
}