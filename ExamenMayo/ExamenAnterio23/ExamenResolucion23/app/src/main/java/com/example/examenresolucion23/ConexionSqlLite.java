package com.example.examenresolucion23;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ConexionSqlLite extends SQLiteOpenHelper {
    public ConexionSqlLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_CONTACTOS);
        db.execSQL(Utilidades.CREAR_TABLA_ALEATORIOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS contactos");
        db.execSQL("DROP TABLE IF EXISTS aleatorios");

        db.execSQL(Utilidades.CREAR_TABLA_CONTACTOS);
        db.execSQL(Utilidades.CREAR_TABLA_ALEATORIOS);
    }
}
