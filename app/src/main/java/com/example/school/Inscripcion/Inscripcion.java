package com.example.school.Inscripcion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.school.ConexionSQLiteHelper;
import com.example.school.entidades.Alumno;
import com.example.school.entidades.Grado;
import com.example.school.entidades.Curso;
import com.example.school.entidades.Seccion;
import com.example.school.R;
import com.example.school.utilidades.Utilidades;

import java.util.ArrayList;

public class Inscripcion extends AppCompatActivity {

    EditText et_id_asig_alum;
    Spinner curso, alumno, grado, seccion;

    ArrayList<String> listaCurso, listaGrado, listaSeccion, listaAlumno;

    ArrayList<Curso> CursoList;
    ArrayList<Grado> GradoList;
    ArrayList<Seccion> SeccionList;
    ArrayList<Alumno> AlumnoList;

    ConexionSQLiteHelper conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inscripcion);
        et_id_asig_alum = (EditText) findViewById(R.id.txtID_Asig_Alum);
        alumno = (Spinner) findViewById(R.id.txtID_ALUMNO);
        curso = (Spinner) findViewById(R.id.txtID_CURSO);
        grado= (Spinner) findViewById(R.id.txtID_GRADO);
        seccion = (Spinner) findViewById(R.id.txtID_SECCION);

        conn=new ConexionSQLiteHelper(getApplicationContext(), Utilidades.DB_NAME, null, Utilidades.DB_VERSION);

        consultarListaCurso();
        consultarListaGrado();
        consultarListaSeccion();
        consultarListaAlumno();

        ArrayAdapter<CharSequence> adaptado=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item, listaAlumno);
        alumno.setAdapter(adaptado);

        ArrayAdapter<CharSequence> adaptador= new ArrayAdapter
                (this, android.R.layout.simple_spinner_item, listaCurso);
        curso.setAdapter(adaptador);

        ArrayAdapter<CharSequence> adaptador1=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item, listaGrado);
        grado.setAdapter(adaptador1);

        ArrayAdapter<CharSequence> adaptador2=new ArrayAdapter
                (this,android.R.layout.simple_spinner_item, listaSeccion);
        seccion.setAdapter(adaptador2);

        alumno.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        curso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        grado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        seccion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long idl) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    private void registrarMascota(){
        SQLiteDatabase db=conn.getWritableDatabase();

        if (!et_id_asig_alum.getText().toString().isEmpty()) {
            ContentValues values = new ContentValues();

            int idCombo1 = (int) alumno.getSelectedItemId();
            int idCombo2 = (int) curso.getSelectedItemId();
            int idCombo3 = (int) seccion.getSelectedItemId();
            int idCombo4 = (int) grado.getSelectedItemId();

            if (idCombo1 != 0) {
                Log.i("TAMAÑO", AlumnoList.size()+"");
                Log.i("id combo", idCombo1+"");
                Log.i("id combo -1", (idCombo1-1)+"");
                int id_alum = AlumnoList.get(idCombo1-1).getId_alumno();
                Log.i("ID Alumno", id_alum+"");

                values.put(Utilidades.CAMPO_ID_ALUM, id_alum);

                Log.i("TAMAÑO", CursoList.size() + "");
                Log.i("id combo", idCombo2 + "");
                Log.i("id combo -1", (idCombo2 - 1)+ "");
                int id_curs = CursoList.get(idCombo2 -1).getId_curso();
                Log.i("ID seccion", id_curs + "");

                values.put(Utilidades.CAMPO_ID_CURS, id_curs);

                Log.i("TAMAÑO", SeccionList.size() + "");
                Log.i("id combo", idCombo3 + "");
                Log.i("id combo -1", (idCombo3 - 1) + "");
                int id_seccio = SeccionList.get(idCombo3 - 1).getId_seccion();
                Log.i("ID Sección", id_seccio + "");

                values.put(Utilidades.CAMPO_ID_SECCIO, id_seccio);

                Log.i("TAMAÑO", GradoList.size() + "");
                Log.i("id combo", idCombo4 + "");
                Log.i("id combo -1", (idCombo4 - 1) + "");
                int id_grad = GradoList.get(idCombo4 - 1).getId_grado();
                Log.i("ID Sección", id_grad + "");
                values.put(Utilidades.CAMPO_ID_GRAD, id_grad);

                values.put(Utilidades.CAMPO_ID_ASIG_ALUM, et_id_asig_alum.getText().toString());

                Long idResultante = db.insert(Utilidades.TABLA_ASIG_ALUM, Utilidades.CAMPO_ID_ASIG_ALUM, values);
                Toast.makeText(this, "El alumno se a inscrito correctamente", Toast.LENGTH_LONG).show();
                db.close();
            }
        }
    }

    private void consultarListaAlumno(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Alumno alumno=null;
        AlumnoList =new ArrayList<Alumno>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_ALUMNO,null);
        while (cursor.moveToNext()){
            alumno=new Alumno();
            alumno.setId_alumno(cursor.getInt(0));
            alumno.setNombre(cursor.getString(1));
            alumno.setApellido(cursor.getString(2));
            Log.i("carnet",alumno.getId_alumno().toString());
            Log.i("nombre",alumno.getNombre().toString());
            Log.i("apellido",alumno.getApellido().toString());
            AlumnoList.add(alumno);
        }
        obtenerListaAlumno();
    }

    private void obtenerListaAlumno() {
        listaAlumno = new ArrayList<String>();
        listaAlumno.add("Seleccione");
        for (int i = 0; i < AlumnoList.size(); i++) {
            listaAlumno.add(AlumnoList.get(i).getId_alumno() + " - " + AlumnoList.get(i).getNombre() + " - " + AlumnoList.get(i).getApellido());
        }
    }

    private void consultarListaCurso(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Curso curso=null;
        CursoList =new ArrayList<Curso>();

        Cursor cursor2=db.rawQuery("SELECT * FROM  "+ Utilidades.TABLA_CURSO,null);
        while (cursor2.moveToNext()){
            curso=new Curso();

            curso.setId_curso(cursor2.getInt(0));
            curso.setCurso(cursor2.getString(1));
            Log.i("id curso",curso.getId_curso().toString());
            Log.i("Curso",curso.getCurso().toString());
            CursoList.add(curso);
        }
        obtnerListaCurso();
    }

    private void obtnerListaCurso(){
        listaCurso = new ArrayList<String>();
        listaCurso.add("Seleccione");

        for(int i = 0; i< CursoList.size(); i++){
            listaCurso.add(CursoList.get(i).getId_curso()+" - "+ CursoList.get(i).getCurso());
        }
    }

    private void consultarListaGrado(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Grado grado=null;
        GradoList =new ArrayList<Grado>();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_GRADO,null);
        while (cursor.moveToNext()){
            grado=new Grado();
            grado.setId_grado(cursor.getInt(0));
            grado.setGrado(cursor.getString(1));
            Log.i("id_grado",grado.getId_grado().toString());
            Log.i("grado",grado.getGrado().toString());
            GradoList.add(grado);
        }
        obtenerListaGrado();
    }

    private void obtenerListaGrado() {
        listaGrado =new ArrayList<String>();
        listaGrado.add("Seleccione");

        for(int i = 0; i< GradoList.size(); i++){
            listaGrado.add(GradoList.get(i).getId_grado()+" - "+ GradoList.get(i).getGrado());
        }
    }

    private void consultarListaSeccion(){
        SQLiteDatabase db=conn.getReadableDatabase();
        Seccion seccion=null;
        SeccionList =new ArrayList<Seccion>();
        //select * from usuarios
        Cursor cursor=db.rawQuery("SELECT * FROM "+ Utilidades.TABLA_SECCION,null);
        while (cursor.moveToNext()){
            seccion=new Seccion();
            seccion.setId_seccion(cursor.getInt(0));
            seccion.setSeccion(cursor.getString(1));
            Log.i("id_seccion",seccion.getId_seccion().toString());
            Log.i("seccion",seccion.getSeccion().toString());
            SeccionList.add(seccion);
        }
        obtenerListaSeccion();
    }

    private void obtenerListaSeccion() {
        listaSeccion =new ArrayList<String>();
        listaSeccion.add("Seleccione");

        for(int i = 0; i< SeccionList.size(); i++){
            listaSeccion.add(SeccionList.get(i).getId_seccion()+" - "+ SeccionList.get(i).getSeccion());
        }
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnRegistroMascota : registrarMascota();
        }
    }


    public void Eliminar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={et_id_asig_alum.getText().toString()};

        db.delete(Utilidades.TABLA_ASIG_ALUM,Utilidades.CAMPO_ID_ASIG_ALUM+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Documento eliminado exitosamente",Toast.LENGTH_LONG).show();
        et_id_asig_alum.setText("");
        limpiar();
        db.close();
    }

    public void Modificar(View view){
        SQLiteDatabase db=conn.getWritableDatabase();
        String[] parametros={et_id_asig_alum.getText().toString()};

        if (!et_id_asig_alum.getText().toString().isEmpty()) {
            ContentValues values=new ContentValues();

            int idCombo1 = (int) alumno.getSelectedItemId();
            int idCombo2 = (int) curso.getSelectedItemId();
            int idCombo3 = (int) seccion.getSelectedItemId();
            int idCombo4 = (int) grado.getSelectedItemId();

            if (idCombo1 != 0) {
                Log.i("TAMAÑO", AlumnoList.size()+"");
                Log.i("id combo", idCombo1+"");
                Log.i("id combo -1", (idCombo1-1)+"");
                int id_alum = AlumnoList.get(idCombo1-1).getId_alumno();
                Log.i("ID Alumno", id_alum+"");
                values.put(Utilidades.CAMPO_ID_ALUM, id_alum);

                Log.i("TAMAÑO", CursoList.size() + "");
                Log.i("id combo", idCombo2 + "");
                Log.i("id combo -1", (idCombo2 - 1) + "");
                int id_curs = CursoList.get(idCombo2 - 1).getId_curso();
                Log.i("ID Sección", id_curs + "");
                values.put(Utilidades.CAMPO_ID_CURS, id_curs);


                Log.i("TAMAÑO", SeccionList.size() + "");
                Log.i("id combo", idCombo3 + "");
                Log.i("id combo -1", (idCombo3 - 1) + "");
                int id_seccio = SeccionList.get(idCombo3 - 1).getId_seccion();
                Log.i("ID Sección", id_seccio + "");
                values.put(Utilidades.CAMPO_ID_SECCIO, id_seccio);


                Log.i("TAMAÑO", GradoList.size() + "");
                Log.i("id combo", idCombo4 + "");
                Log.i("id combo -1", (idCombo4 - 1) + "");
                int id_grad = GradoList.get(idCombo4 - 1).getId_grado();
                Log.i("ID Sección", id_grad + "");
                values.put(Utilidades.CAMPO_ID_GRAD, id_grad);



                db.update(Utilidades.TABLA_ASIG_ALUM, values, Utilidades.CAMPO_ID_ASIG_ALUM+ "=?",parametros);
                Toast.makeText(this, "Inscripción registrada", Toast.LENGTH_LONG).show();
                db.close();
            }
        }
    }

    public void Buscar(View view){
        SQLiteDatabase db=conn.getReadableDatabase();
        String[] parametros={et_id_asig_alum.getText().toString()};
        try {
            //select nombre,telefono from usuario where codigo=?
            Cursor cursor=db.rawQuery("SELECT "+Utilidades.CAMPO_ID_ALUM+"," + Utilidades.CAMPO_ID_CURS+"," + Utilidades.CAMPO_ID_GRAD+ "," + Utilidades.CAMPO_ID_SECCIO+
                    " FROM "+Utilidades.TABLA_ASIG_ALUM+" WHERE "+Utilidades.CAMPO_ID_ASIG_ALUM+"=? ", parametros);

            cursor.moveToFirst();

            et_id_asig_alum.setText(cursor.getString(0));
            alumno.setId(cursor.getInt(1));
            curso.setId(cursor.getInt(2 ));
            grado.setId(cursor.getInt(3));
            seccion.setId(cursor.getInt(4));

        }catch (Exception e){
            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();
        }
    }

    private void limpiar(){
        et_id_asig_alum.setText("");
    }

    public void Lista(View view){
        Intent lista = new Intent(this, RecycleView_Asig_Alum.class);
        startActivity(lista);
    }

}