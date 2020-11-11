package com.example.school.Seccion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Toast;
import  com.example.school.entidades.Seccion;
import com.example.school.ConexionSQLiteHelper;
import com.example.school.R;
import com.example.school.utilidades.Utilidades;

import java.util.ArrayList;

public class RecycleView_Seccion extends AppCompatActivity {

    ArrayList<Seccion> listaSeccion;
    RecyclerView recyclerViewSeccion;
    ConexionSQLiteHelper conn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_view_seccion);

        try{
            conn=new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME,null, Utilidades.DB_VERSION);

            listaSeccion = new ArrayList<>();
            recyclerViewSeccion = findViewById(R.id.IdRecyclerSeccion);

            recyclerViewSeccion.setLayoutManager(new LinearLayoutManager(this));

            consultarListaProductos();

            AdapterSeccion adapter = new AdapterSeccion(listaSeccion);
            recyclerViewSeccion.setAdapter(adapter);
        }catch (Exception e){
            Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG ).show();
        }
    }
    private void consultarListaProductos(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Seccion seccion = null;

        Cursor cursor=db.rawQuery("Select * from " + Utilidades.TABLA_SECCION, null);

        while (cursor.moveToNext()){
            seccion = new Seccion();
            seccion.setId_seccion(cursor.getInt(0));
            seccion.setSeccion(cursor.getString(1));

            listaSeccion.add(seccion);
        }
    }
}