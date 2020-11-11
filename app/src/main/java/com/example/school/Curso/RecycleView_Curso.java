package com.example.school.Curso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.Toast;

import com.example.school.ConexionSQLiteHelper;
import com.example.school.entidades.Curso;
import com.example.school.utilidades.Utilidades;
import com.example.school.R;

import java.util.ArrayList;

public class RecycleView_Curso extends AppCompatActivity {

    ArrayList<Curso> listaCurso;
    RecyclerView recyclerViewCurso;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_curso);
        try{
            conn=new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);

            listaCurso = new ArrayList<>();
            recyclerViewCurso = findViewById(R.id.IdRecyclerCurso);

            recyclerViewCurso.setLayoutManager(new LinearLayoutManager(this));

            consultarListaProductos();

            AdapterCurso adapter = new AdapterCurso(listaCurso);
            recyclerViewCurso.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG ).show();
        }
    }

    private void consultarListaProductos(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Curso curso = null;

        Cursor cursor=db.rawQuery("Select * from " + Utilidades.TABLA_CURSO, null);

        while(cursor.moveToNext()) {
            curso = new Curso();
            curso.setId_curso(cursor.getInt(0));
            curso.setCurso(cursor.getString(1));

            listaCurso.add(curso);
        }

    }

}