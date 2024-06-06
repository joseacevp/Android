package com.example.operacionesmatematicas;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText campoUno, campoDos;
    TextView etiquetaResultado;
    int primerNumero,segundoNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        campoUno = (EditText) findViewById(R.id.campoUno);
        campoDos = (EditText) findViewById(R.id.campoDos);
        etiquetaResultado = (TextView) findViewById(R.id.etiquetaResultado);

    }

 

    public void onClick(View view) {

        primerNumero = Integer.parseInt(campoUno.getText().toString());
        segundoNumero = Integer.parseInt(campoDos.getText().toString());
        switch (view.getId()){
            case R.id.botonSumar:
                sumar();
                break;
            case R.id.botonRestar:
                restar();
                break;
            case R.id.botonDividir:
                if(segundoNumero > 0 ){
                    dividir();
                }else{
                    Toast.makeText(this,"Advertencia",Toast.LENGTH_LONG).show();
                    etiquetaResultado.setText("Para la División el segundo número ha de ser mayor de Cero");
                }
                break;
            case R.id.botonMultiplicar:
                multiplicar();
                break;
        }
    }

    private void multiplicar() {


        int resultado = primerNumero * segundoNumero;
        etiquetaResultado.setText("El resultado de la Multiplicación es: "+resultado);
    }

    private void dividir() {
        int resultado = primerNumero / segundoNumero;
        etiquetaResultado.setText("El resultado de la División es: "+resultado);
    }

    private void restar() {
        int resultado = primerNumero - segundoNumero;
        etiquetaResultado.setText("El resultado de la Resta es: "+resultado);
    }

    private void sumar() {
        int resultado = primerNumero + segundoNumero;
        etiquetaResultado.setText("El resultado de la Suma es: "+resultado);
    }
}