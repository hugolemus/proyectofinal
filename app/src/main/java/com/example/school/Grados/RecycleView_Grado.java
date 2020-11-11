package com.example.school.Grados;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;

import com.example.school.ConexionSQLiteHelper;
import com.example.school.R;
import com.example.school.entidades.Grado;
import com.example.school.utilidades.Utilidades;

import java.util.ArrayList;

public class RecycleView_Grado extends AppCompatActivity {

    ArrayList<Grado> listaGrado;
    RecyclerView recyclerViewGrado;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_grado);
        try {
            conn=new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);

            listaGrado = new ArrayList<>();
            recyclerViewGrado = findViewById(R.id.IdRecyclerGrado);
            recyclerViewGrado.setLayoutManager(new LinearLayoutManager(this));
            consultarListaProductos();

            AdapterGrado adapter = new AdapterGrado(listaGrado);
            recyclerViewGrado.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        }
    }

    private void consultarListaProductos(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Grado grado=null;
        Cursor cursor=db.rawQuery("Select * from " +Utilidades.TABLA_GRADO, null);

        while(cursor.moveToNext()){
            grado = new Grado();
            grado.setId_grado(cursor.getInt(0));
            grado.setGrado(cursor.getString(1));

            listaGrado.add(grado);
        }

    }
}