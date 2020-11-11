package com.example.school.Alumnos;

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

public class Alumnos extends AppCompatActivity {

    EditText edtIdAlumno, edtNombre, edtApellido, edtDireccion, edtTelefono, edtEdad;
    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alumnos);

        edtIdAlumno=(EditText) findViewById(R.id.txtID_Curso);
        edtNombre = (EditText) findViewById(R.id.edtNombre);
        edtApellido = (EditText) findViewById(R.id.edtApellido);
        edtDireccion = (EditText) findViewById(R.id.edtDireccion);
        edtTelefono = (EditText) findViewById(R.id.edtTelefono);
        edtEdad = (EditText) findViewById(R.id.edtEdad);

        conn = new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);
    }

    public void onClick(View view) {
        registrarAlumnos();
    }

    private void limpiar() {
        edtIdAlumno.setText("");
        edtNombre.setText("");
        edtApellido.setText("");
        edtDireccion.setText("");
        edtTelefono.setText("");
        edtEdad.setText("");

    }

    //METODO PARA REGISTRAR//
    private void registrarAlumnos() {
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);

        SQLiteDatabase db = conn.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put(Utilidades.CAMPO_IDA,edtIdAlumno.getText().toString());//
        values.put(Utilidades.CAMPO_NOMBRE, edtNombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDO, edtApellido.getText().toString());
        values.put(Utilidades.CAMPO_DIRECCION, edtDireccion.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, edtTelefono.getText().toString());
        values.put(Utilidades.CAMPO_EDAD, edtEdad.getText().toString());

        Long idResultante = db.insert(Utilidades.TABLA_ALUMNO, Utilidades.CAMPO_IDA, values);

        Toast.makeText(getApplicationContext(), "Id Registro: " + idResultante, Toast.LENGTH_SHORT).show();
        db.close();
        edtNombre.setText("");
        edtApellido.setText("");
        edtDireccion.setText("");
        edtTelefono.setText("");
        edtEdad.setText("");
    }


    //METODO PARA BUSCAR//
    public void Buscar(View view){
        ConexionSQLiteHelper conn = new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);
        SQLiteDatabase db= conn.getReadableDatabase();
        String[] parametros={edtIdAlumno.getText().toString()};

        try{
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_NOMBRE+"," + Utilidades.CAMPO_APELLIDO+ "," + Utilidades.CAMPO_DIRECCION+ "," + Utilidades.CAMPO_TELEFONO+ "," + Utilidades.CAMPO_EDAD+
                    " FROM "+Utilidades.TABLA_ALUMNO+" WHERE "+Utilidades.CAMPO_IDA+"=? ", parametros);

            cursor.moveToFirst();
            edtNombre.setText(cursor.getString(0));
            edtApellido.setText(cursor.getString(1));
            edtDireccion.setText(cursor.getString(2));
            edtTelefono.setText(cursor.getString(3));
            edtEdad.setText(cursor.getString(4));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "NO encontrado", Toast.LENGTH_LONG).show();
            limpiar();
        }

    }

    //METODO EDITAR
    public void Modificar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={edtIdAlumno.getText().toString()};
        ContentValues values=new ContentValues();

        values.put(Utilidades.CAMPO_NOMBRE, edtNombre.getText().toString());
        values.put(Utilidades.CAMPO_APELLIDO, edtApellido.getText().toString());
        values.put(Utilidades.CAMPO_DIRECCION, edtDireccion.getText().toString());
        values.put(Utilidades.CAMPO_TELEFONO, edtTelefono.getText().toString());
        values.put(Utilidades.CAMPO_EDAD, edtEdad.getText().toString());

        db.update(Utilidades.TABLA_ALUMNO,values,Utilidades.CAMPO_IDA+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se ha editado el documento", Toast.LENGTH_LONG).show();
        db.close();

    }

    //METODO ELIMINAR//
    public void Eliminar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={edtIdAlumno.getText().toString()};

        db.delete(Utilidades.TABLA_ALUMNO, Utilidades.CAMPO_IDA+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se elimino el Documento Correctamente", Toast.LENGTH_LONG).show();
        edtIdAlumno.setText("");
        limpiar();
        db.close();
    }


    //METODO PARA MOSTRAR//
    public void Lista(View view) {
        Intent lista = new Intent(this, recycle_view_alumno.class);
        startActivity(lista);
    }



}