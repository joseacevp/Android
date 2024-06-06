package com.example.textvieweditview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button boton;
    EditText editText1,editText2;
    TextView textView1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        boton = findViewById(R.id.boton1);
        boton.setOnClickListener(this);
        editText1 = findViewById(R.id.editext1);
        editText2 = findViewById(R.id.editext2);
        textView1 = findViewById(R.id.textView1);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.boton1:
                Toast.makeText(getApplicationContext(),"prueba boton",Toast.LENGTH_LONG).show();
                optenerInfo();
                break;
        }
    }

    private void optenerInfo() {
       int informacion = Integer.parseInt(String.valueOf(editText1.getText()));
        textView1.setText(String.valueOf(informacion));
       
    }
}