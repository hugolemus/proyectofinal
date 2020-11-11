package com.example.school;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.example.school.Inscripcion.Inscripcion;
import com.example.school.Alumnos.Alumnos;
import com.example.school.Curso.Curso;
import com.example.school.Grados.Grado;
import com.example.school.Seccion.Seccion;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this, "bd_alumnos", null, 1);

    }


        public void onClick(View view){

            Intent miIntent= null;

            switch (view.getId()){
                case R.id.img_alumno:
                    miIntent= new Intent(MainActivity.this, Alumnos.class);
                    break;
                case R.id.img_grado:
                    miIntent= new Intent(MainActivity.this, Grado.class);
                    break;
                case R.id.img_inscripcion:
                    miIntent= new Intent(MainActivity.this, Inscripcion.class);
                    break;
                case R.id.img_curso:
                    miIntent= new Intent(MainActivity.this, Curso.class);
                    break;
                case R.id.img_seccion:
                    miIntent= new Intent(MainActivity.this, Seccion.class);

            }startActivity(miIntent);
        }


}
