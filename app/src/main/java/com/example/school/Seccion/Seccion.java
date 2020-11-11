package com.example.school.Seccion;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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
import com.example.school.utilidades.Utilidades;

public class Seccion extends AppCompatActivity {

    EditText edtIdSeccion, edtSeccion;
     ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seccion);
        edtIdSeccion = (EditText) findViewById(R.id.edtIdSeccion);
        edtSeccion = (EditText) findViewById(R.id.edtSeccion);

        conn=new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);

    }

    public void onClick(View view){
        registrarSecciones();
    }


    private void limpiar() {
        edtSeccion.setText("");

    }
    //METODO PARA REGISTRAR//

    public void registrarSecciones(){
            SQLiteDatabase db=conn.getWritableDatabase();
        ContentValues values=new ContentValues();

        values.put(Utilidades.CAMPO_SECCION, edtSeccion.getText().toString());

        Long idResultante=db.insert(Utilidades.TABLA_SECCION,Utilidades.CAMPO_IDS,values);

        Toast.makeText(getApplicationContext(),"Registro No.: "+idResultante,Toast.LENGTH_SHORT).show();
        db.close();
        edtSeccion.setText("");
        }

   //METODO BUSCAR
    public void Buscar(View view){
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={edtIdSeccion.getText().toString()};

        try{
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_SECCION+
                    " FROM "+Utilidades.TABLA_SECCION+" WHERE "+Utilidades.CAMPO_IDS+"=?",parametros);
            cursor.moveToFirst();
            edtSeccion.setText(cursor.getString(0));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(), "El documento no existe", Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    //METODO EDITAR
    public void Modificar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={edtIdSeccion.getText().toString()};
        ContentValues values = new ContentValues();
        values.put(Utilidades.CAMPO_SECCION, edtSeccion.getText().toString());

        db.update(Utilidades.TABLA_SECCION,values, Utilidades.CAMPO_IDS+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se Edito Correctamente", Toast.LENGTH_LONG).show();;
        db.close();
    }

    //METODO ELIMINAR//
    public void Eliminar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros = {edtIdSeccion.getText().toString()};

        db.delete(Utilidades.TABLA_SECCION, Utilidades.CAMPO_IDS+"=?",parametros);
        Toast.makeText(getApplicationContext(), "Se Elimino Correctamente", Toast.LENGTH_LONG).show();
        edtIdSeccion.setText("");
         limpiar();
         db.close();
    }

    public void Lista(View view){
        Intent lista = new Intent(this, RecycleView_Seccion.class);
        startActivity(lista);
    }


}
