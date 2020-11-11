package com.example.school.Curso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.school.utilidades.Utilidades;
import com.example.school.ConexionSQLiteHelper;

import com.example.school.R;

public class Curso extends AppCompatActivity {

    private EditText et_id_curso, et_curso;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_curso);
        et_id_curso = (EditText) findViewById(R.id.txtID_Curso);
        et_curso = (EditText) findViewById(R.id.txtCurso);

        conn = new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);
    }

    private void limpiar() {
        et_curso.setText("");

    }

    //METODO REGISTRAR
   public void Registrar(View view){

       SQLiteDatabase db=conn.getWritableDatabase();
       ContentValues values=new ContentValues();
    values.put(Utilidades.CAMPO_CURSO,et_curso.getText().toString());

       Long idResultante=db.insert(Utilidades.TABLA_CURSO,Utilidades.CAMPO_ID_CURSO,values);

       Toast.makeText(getApplicationContext(),"Id Registro: "+idResultante,Toast.LENGTH_SHORT).show();
       db.close();

       et_curso.setText("");
   }


   //METODO BUSCAR
    public void buscar(View view){
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={et_id_curso.getText().toString()};
        try{
        Cursor cursor=db.rawQuery("SELECT " +Utilidades.CAMPO_CURSO+
                " FROM "+Utilidades.TABLA_CURSO+ " WHERE "+Utilidades.CAMPO_ID_CURSO+"=? " , parametros);

        cursor.moveToNext();
        et_curso.setText(cursor.getString(0));

         }catch (Exception e){
            Toast.makeText(getApplicationContext(), "el documento no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    //METODO EDITAR
    public void Modificar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={et_id_curso.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_CURSO,et_curso.getText().toString());

        db.update(Utilidades.TABLA_CURSO,values, Utilidades.CAMPO_ID_CURSO+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se Edito Correctamente", Toast.LENGTH_LONG).show();;
        db.close();
    }

    //METODO ELIMINAR//
    public void Eliminar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {et_id_curso.getText().toString()};

        db.delete(Utilidades.TABLA_CURSO, Utilidades.CAMPO_ID_CURSO+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se Elimino Correctamente", Toast.LENGTH_LONG).show();
        et_id_curso.setText("");
        limpiar();
        db.close();
    }

   //METODO PARA LISTAR//
    public void Lista(View view){
        Intent lista = new Intent(this, RecycleView_Curso.class);
        startActivity(lista);
    }



}