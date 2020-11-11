package com.example.school.Alumnos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.school.ConexionSQLiteHelper;
import com.example.school.R;

import com.example.school.entidades.Alumno;
import com.example.school.utilidades.Utilidades;

import java.util.ArrayList;

public class recycle_view_alumno extends AppCompatActivity {
    ArrayList<Alumno> listaAlumno;
    RecyclerView recyclerViewAlumno;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_alumno);

        try {
            conn = new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);
            listaAlumno = new ArrayList<>();
            recyclerViewAlumno = findViewById(R.id.IdRecyclerAlumno);

            recyclerViewAlumno.setLayoutManager(new LinearLayoutManager(this));

            consultarListaProductos();

            AdapterAlumno adapter = new AdapterAlumno(listaAlumno);
            recyclerViewAlumno.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void consultarListaProductos(){
        SQLiteDatabase db= conn.getReadableDatabase();
        Alumno alumno=null;
        Cursor cursor=db.rawQuery("Select * from " +Utilidades.TABLA_ALUMNO, null);


        while (cursor.moveToNext()){
            alumno = new Alumno();
            alumno.setId_alumno(cursor.getInt(0));
            alumno.setNombre(cursor.getString(1));
            alumno.setApellido(cursor.getString(2));
            alumno.setDireccion(cursor.getString(3));
            alumno.setTelefono(cursor.getString(4));
            alumno.setEdad(cursor.getString(5));

            listaAlumno.add(alumno);
        }

    }
}