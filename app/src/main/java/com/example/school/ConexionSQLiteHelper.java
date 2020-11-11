package com.example.school;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.school.utilidades.Utilidades;

public class ConexionSQLiteHelper extends SQLiteOpenHelper {




    public ConexionSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Utilidades.CREAR_TABLA_ALUMNO);
        db.execSQL(Utilidades.CREAR_TABLA_CURSO);
        db.execSQL(Utilidades.CREAR_TABLA_GRADO);
        db.execSQL(Utilidades.CREAR_TABLA_SECCION);
        db.execSQL(Utilidades.CREAR_TABLA_ASIG_ALUM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAntigua, int versionNueva) {
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ALUMNO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_CURSO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_GRADO);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_SECCION);
        db.execSQL("DROP TABLE IF EXISTS "+Utilidades.TABLA_ASIG_ALUM);

        onCreate(db);

    }
}
