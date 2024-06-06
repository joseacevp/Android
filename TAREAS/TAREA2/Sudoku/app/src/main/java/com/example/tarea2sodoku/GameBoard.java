package com.example.tarea2sodoku;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.view.View;

import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.Toast;


import java.util.List;


public class GameBoard extends View {
    private final int boardColor;
    private int cellSize;
    private int selectedRow = -1;
    private int selectedCol = -1;
    private List<RemovedNumber> removedNumbers;
    private int inputNumber = 0;
    private int[][] sudokuBoard;
    private OnGameOverListener gameOverListener;
    private SudokuGenerator generator;
    private boolean[][] editableCells;
    private final Paint cellHighlightPaint = new Paint();
    private final Paint highlightPaint = new Paint();
    private final Paint squareHighlightPaint = new Paint();
    private final Paint boardPaint = new Paint();
    private final Paint textPaint = new Paint();
    private final Paint inputTextPaint = new Paint();

    public void dificultad(int casillasVacias) {//para cambiar la dificultad
        removedNumbers = generator.removeNumbers(casillasVacias);

    }

    private interface OnGameOverListener {
        void onGameOver();


    }

    //constructor de la clase que recibe por parametros el argumento attrs que define el estilo
    public GameBoard(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.GameBoard,
                0, 0);
        try {
            boardColor = a.getInteger(R.styleable.GameBoard_boardColor, 0);
        } finally {
            a.recycle();
        }

        highlightPaint.setColor(Color.parseColor("#88AAB1FF"));
        highlightPaint.setStyle(Paint.Style.FILL);

        cellHighlightPaint.setColor(Color.parseColor("#886200EE"));
        cellHighlightPaint.setStyle(Paint.Style.FILL);

        squareHighlightPaint.setColor(Color.parseColor("#88D1D7FF"));
        squareHighlightPaint.setStyle(Paint.Style.FILL);

        generator = new SudokuGenerator();
        sudokuBoard = generator.generate();
        removedNumbers = generator.removeNumbers(20);//determina la dificultad numero de casillar vacias

        editableCells = new boolean[9][9];
        for (RemovedNumber removedNumber : removedNumbers) {
            editableCells[removedNumber.row][removedNumber.col] = true;
        }
    }

    @Override
    protected void onMeasure(int widthMeasure, int heightMeasure) {
        super.onMeasure(widthMeasure, heightMeasure);

        int dimension = Math.min(this.getMeasuredWidth(), this.getMeasuredHeight());
        cellSize = dimension / 9;

        setMeasuredDimension(dimension, dimension);

    }

    @Override
    protected void onDraw(Canvas canvas) {

        boardPaint.setStyle(Paint.Style.STROKE);
        boardPaint.setStrokeWidth(16);//grueso de la liena esterior
        boardPaint.setColor(boardColor);
        boardPaint.setAntiAlias(true);

        textPaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(cellSize * 0.7f);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setAntiAlias(true);
        textPaint.setSubpixelText(true);
        textPaint.setLinearText(true);

        inputTextPaint.setStyle(Paint.Style.FILL);
        inputTextPaint.setTextSize(cellSize * 0.7f);
        inputTextPaint.setColor(Color.parseColor("#FFA500"));
        inputTextPaint.setTextAlign(Paint.Align.CENTER);
        inputTextPaint.setAntiAlias(true);
        inputTextPaint.setSubpixelText(true);
        inputTextPaint.setLinearText(true);

        canvas.drawRect(0, 0, getWidth(), getHeight(), boardPaint);

        if (selectedRow != -1 && selectedCol != -1) {
            canvas.drawRect(0, selectedRow * cellSize, getWidth(), (selectedRow + 1) * cellSize, highlightPaint);
            canvas.drawRect(selectedCol * cellSize, 0, (selectedCol + 1) * cellSize, getHeight(), highlightPaint);

            int areaStartRow = selectedRow / 3 * 3;
            int areaStartCol = selectedCol / 3 * 3;
            canvas.drawRect(areaStartCol * cellSize, areaStartRow * cellSize, (areaStartCol + 3) * cellSize, (areaStartRow + 3) * cellSize, squareHighlightPaint);

            canvas.drawRect(selectedCol * cellSize, selectedRow * cellSize, (selectedCol + 1) * cellSize, (selectedRow + 1) * cellSize, cellHighlightPaint);
        }

        drawBoard(canvas);
        drawNumbers(canvas, textPaint);
        drawInputNumbers(canvas, inputTextPaint);
    }

    private void drawBoard(Canvas canvas) {
        for (int i = 0; i < 9; i++) {
            if (i % 3 == 0) {
                drawThickLines(canvas);
            } else {
                drawThinLines(canvas);
            }
            canvas.drawLine(0, i * cellSize, getWidth(), i * cellSize, boardPaint);
            canvas.drawLine(i * cellSize, 0, i * cellSize, getHeight(), boardPaint);
        }
    }

    //clase que define las lineas que separan los cuadrados de 3 * 3 lienes gruesas
    private void drawThickLines(Canvas canvas) {
        boardPaint.setStyle(Paint.Style.STROKE);
        boardPaint.setStrokeWidth(8);
        boardPaint.setColor(boardColor);
    }

    //clase que define las lineas finas que separan los numeros
    private void drawThinLines(Canvas canvas) {
        boardPaint.setStyle(Paint.Style.STROKE);
        boardPaint.setStrokeWidth(4);
        boardPaint.setColor(boardColor);
    }

    //define las celdas donde se dibujaran los numeros negros
    private void drawNumbers(Canvas canvas, Paint textPaint) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int number = sudokuBoard[i][j];
                if (number != 0) {
                    float x = j * cellSize + cellSize / 2;
                    float y = i * cellSize + cellSize / 2 + (textPaint.getTextSize() / 2) - textPaint.descent();
                    canvas.drawText(String.valueOf(number), x, y, textPaint);
                }
            }
        }
    }

    //define el color de los numeros escritos segun el acierto o no
    private void drawInputNumbers(Canvas canvas, Paint paint) {
        for (int row = 0; row < 9; row++) {//fallo color negro en numeros indicados solución cambiar de 7 a 9
            for (int col = 0; col < 9; col++) {
                if (editableCells[row][col]) {
                    if (sudokuBoard[row][col] != 0) {
                        if (checkNumberPlacement(row, col, sudokuBoard[row][col])) {
                            paint.setColor(Color.parseColor("#FFA500"));
                        } else {
                            paint.setColor(Color.RED);
                        }
                        canvas.drawText(Integer.toString(sudokuBoard[row][col]), col * cellSize + cellSize / 2f, row * cellSize + cellSize / 2f + cellSize * 0.2f, paint);
                    }
                }
            }
        }
    }

    //metodo para registrar el evento de tocar la pantalla
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            int x = (int) (event.getX() / cellSize);
            int y = (int) (event.getY() / cellSize);

            if (x >= 0 && x < 9 && y >= 0 && y < 9) {
                selectedRow = y;
                selectedCol = x;
                if (inputNumber != 0) {
                    fillCell(inputNumber);
                    inputNumber = 0;
                }

                invalidate();

            }
        }
        return true;
    }

    //metodo introducir numero
    public void setInputNumber(int number) {
        inputNumber = number;
        if (selectedRow != -1 && selectedCol != -1) {
            fillCell(inputNumber);
            inputNumber = 0;
        }
    }

    private void fillCell(int number) {
        if (selectedRow != -1 && selectedCol != -1 && editableCells[selectedRow][selectedCol]) {
            sudokuBoard[selectedRow][selectedCol] = number;
            invalidate();

            if (isGameOver() && gameOverListener != null) {
                gameOverListener.onGameOver();
            }
        }
    }

    private boolean isGameOver() {//indica el final del juego
        if (isBoardFull()) {
            if (isBoardValid()) {
                System.out.println("Board is valid");
                //dialogo para notificar que has ganado
                AlertDialog.Builder constructor = new AlertDialog.Builder(getContext());
                constructor.setTitle(R.string.ganaste)
                        .setMessage("\n\n\n\n");
                AlertDialog dialog = constructor.create();
                dialog.show();
                return true;
            } else {
                System.out.println("Board is not valid");
                AlertDialog.Builder constructor = new AlertDialog.Builder(getContext());
                constructor.setTitle(R.string.perdiste)
                        .setMessage("El numero de aciertos fue: " + consutarAciertos());
                AlertDialog dialog = constructor.create();
                dialog.show();
                resetBoard(10);
                return false;
            }

        }
        return false;
    }

    private boolean isBoardFull() {//determina si esta completa la tabla
        for (int row = 0; row < generator.getBoardSize(); row++) {
            for (int col = 0; col < generator.getBoardSize(); col++) {
                if (getCell(row, col) == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    //El método recorre las casillas por columnas y filas y determina si se repite algún numero
    // si no es así determina que la tabla es valida y el Jugador Gana.
    private boolean isBoardValid() {//ganas
        for (int row = 0; row < generator.getBoardSize(); row++) {
            for (int col = 0; col < generator.getBoardSize(); col++) {
                int currentValue = getCell(row, col);
                setCell(row, col, 0);
                if (!generator.isSafe(row, col, currentValue)) {
                    setCell(row, col, currentValue);
                    return false;
                }
                setCell(row, col, currentValue);
            }
        }
        return true;
    }

    private int getCell(int row, int col) {
        return sudokuBoard[row][col];
    }

    private void setCell(int row, int col, int value) {
        sudokuBoard[row][col] = value;
    }


    //reiniciar tablero
    public void resetBoard(int n) {
        sudokuBoard = generator.generate();
        removedNumbers = generator.removeNumbers(n);
        updateEditableCells();
        invalidate();
    }

    public void updateEditableCells() {
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                editableCells[row][col] = false;
            }
        }
        for (RemovedNumber removedNumber : removedNumbers) {
            editableCells[removedNumber.row][removedNumber.col] = true;
        }
    }


    private boolean checkNumberPlacement(int row, int col, int number) {
        
        for (int i = 0; i < 9; i++) {
            if (sudokuBoard[row][i] == number && i != col) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (sudokuBoard[i][col] == number && i != row) {
                return false;
            }
        }

        int squareStartRow = (row / 3) * 3;
        int squareStartCol = (col / 3) * 3;
        for (int i = squareStartRow; i < squareStartRow + 3; i++) {
            for (int j = squareStartCol; j < squareStartCol + 3; j++) {
                if (sudokuBoard[i][j] == number && (i != row || j != col)) {
                    return false;
                }
            }
        }

        return true;
    }

    private int  consutarAciertos() {
        int aciertos=0;
        for (int row = 0; row < 9; row++) {//fallo color negro en numeros indicados solución cambiar de 7 a 9
            for (int col = 0; col < 9; col++) {
                if (editableCells[row][col]) {
                    if (sudokuBoard[row][col] != 0) {
                        if (checkNumberPlacement(row, col, sudokuBoard[row][col])) {
                           aciertos++;
                        }
                    }
                }
            }
        }
        return aciertos;
    }

}

