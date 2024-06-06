package com.example.tableroajedred;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class ChessboardView extends View {
    //declaracion de constantes para las columnas y las filas
    private final int numRows = 8, numColums = 8;
    private  final Paint whitePaint = new Paint();//objeto Paint o pincel
    private final  Paint blackPaint = new Paint();
    private int squareSize;// area de trabajo



    public ChessboardView(Context context) {
        super(context);
        // la declaracion del pincel se raliza en el metodo onDraw no en el constructor
        // de la clase
    }


    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        whitePaint.setColor(Color.WHITE);

        blackPaint.setColor(Color.BLACK);

        squareSize = Math.min(getWidth(),getHeight()) / numColums;// calcula el area de trabajo
        // segun las dimensiones de la ventana alto y largo.

        for (int row = 0 ; row < numRows; row ++ ){
            for (int col = 0; col < numColums; col++){
                if ((row + col) % 2 == 0){
                    canvas.drawRect(col * squareSize, row * squareSize, (col + 1) * squareSize,( row + 1) * squareSize,whitePaint);
                    // dibuja con el objeto pincel un rectangulo en el area de la ventana.
                } else {
                    canvas.drawRect(col * squareSize , row * squareSize , ( col +1 ) * squareSize, (row + 1) *squareSize , blackPaint);
                }
            }
        }
    }
}
