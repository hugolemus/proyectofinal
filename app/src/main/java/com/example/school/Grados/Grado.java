package com.example.school.Grados;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.school.ConexionSQLiteHelper;
import com.example.school.R;
import com.example.school.utilidades.Utilidades;

public class Grado extends AppCompatActivity {

    EditText edtIdGrado, edtGrado;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grado);

        edtIdGrado=(EditText) findViewById(R.id.txtID_Grado) ;
        edtGrado = (EditText) findViewById(R.id.txtGrado);

        conn= new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);
    }

    public void onClick(View view){
        registrarGrados();
    }

    private void limpiar() {
        edtGrado.setText("");

    }

    // Método para registrar
    public void registrarGrados(){

        SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();
                values.put(Utilidades.CAMPO_GRADO, edtGrado.getText().toString());

        Long idResultante=db.insert(Utilidades.TABLA_GRADO,Utilidades.CAMPO_IDA,values);

        Toast.makeText(getApplicationContext(),"Registro No.: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
        edtGrado.setText("");

    }

    //METODO BUSCAR//
    public void Buscar(View view){
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={edtIdGrado.getText().toString()};

        try{
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_GRADO+
                    " FROM "+Utilidades.TABLA_GRADO+" WHERE "+Utilidades.CAMPO_IDG+"=?",parametros);

            cursor.moveToFirst();
            edtGrado.setText(cursor.getString(0));
        }catch (Exception e) {
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    //METODO EDITAR//
    public void Editar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={edtGrado.getText().toString()};
        ContentValues values=new ContentValues();
        values.put(Utilidades.CAMPO_GRADO, edtGrado.getText().toString());

        db.update(Utilidades.TABLA_GRADO,values, Utilidades.CAMPO_IDG+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se Edito el Documento Correctamente",Toast.LENGTH_LONG).show();
        db.close();
    }

    //METODO ELIMINAR//
        public void Eliminar(View view){
            SQLiteDatabase db=conn.getWritableDatabase();
            String[] parametros={edtIdGrado.getText().toString()};

            db.delete(Utilidades.TABLA_GRADO, Utilidades.CAMPO_IDG+"=?",parametros);
            Toast.makeText(getApplicationContext(), "Documento Eliminado Correctamente", Toast.LENGTH_LONG).show();
            edtIdGrado.setText("");
            limpiar();
            db.close();
        }









    public void Lista(View view){
        Intent lista = new Intent(this, RecycleView_Grado.class);
        startActivity(lista);
    }

}