package com.example.school.Inscripcion;

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
import com.example.school.Curso.AdapterCurso;
import com.example.school.R;
import com.example.school.entidades.Asig_Alum;
import com.example.school.entidades.Curso;
import com.example.school.utilidades.Utilidades;

import java.util.ArrayList;

public class RecycleView_Asig_Alum extends AppCompatActivity {

    ArrayList<Asig_Alum> listaAsig_Alum;
    RecyclerView recyclerViewAsig_Alum;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclew_view_asig_alum);

        try{
            conn=new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);

            listaAsig_Alum = new ArrayList<>();
            recyclerViewAsig_Alum = findViewById(R.id.IdRecyclerAsig_Alum);

            recyclerViewAsig_Alum.setLayoutManager(new LinearLayoutManager(this));

            consultarListaProductos();

            AdapterAsig_Alum adapter = new AdapterAsig_Alum(listaAsig_Alum);
            recyclerViewAsig_Alum.setAdapter(adapter);

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }

    }

    private void consultarListaProductos(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Asig_Alum asig_alum = null;
        Cursor cursor=db.rawQuery("Select * from " +Utilidades.TABLA_ASIG_ALUM, null);
        while (cursor.moveToNext()){
            asig_alum = new Asig_Alum();
            asig_alum.setId_asig_alum(cursor.getInt(0));
            asig_alum.setId_alumno(cursor.getInt(1));
            asig_alum.setId_curso(cursor.getInt(2));
            asig_alum.setId_grado(cursor.getInt(3));
            asig_alum.setId_seccin(cursor.getInt(4));
            listaAsig_Alum.add(asig_alum);
        }

    }

}