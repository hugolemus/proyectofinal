package com.example.school.Alumnos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.school.R;
import com.example.school.entidades.Alumno;

import java.util.ArrayList;

public class AdapterAlumno extends RecyclerView.Adapter<AdapterAlumno.ViewHolderDatos> {
    ArrayList<Alumno> listAlumno;

    public AdapterAlumno(ArrayList<Alumno> listAlumno){this.listAlumno = listAlumno;}

    @NonNull

    @Override

    public AdapterAlumno.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_alumno, null, false);
        return new AdapterAlumno.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAlumno.ViewHolderDatos holder, int position) {
        holder.id_alumno.setText(listAlumno.get(position).getId_alumno().toString());
        holder.nombre.setText(listAlumno.get(position).getNombre().toString());
        holder.apellido.setText(listAlumno.get(position).getApellido().toString());
        holder.direccion.setText(listAlumno.get(position).getDireccion().toString());
        holder.telefono.setText(listAlumno.get(position).getTelefono().toString());
        holder.edad.setText(listAlumno.get(position).getEdad().toString());
    }

    @Override
    public int getItemCount() {
        return listAlumno.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView id_alumno, nombre, apellido, direccion, telefono, edad;
        public ViewHolderDatos(@NonNull View itemView){
            super(itemView);
            id_alumno = itemView.findViewById(R.id.txtid_alumno);
            nombre = itemView.findViewById(R.id.txtNombre);
            apellido = itemView.findViewById(R.id.txtApellido);
            direccion = itemView.findViewById(R.id.txtDireccion);
            telefono = itemView.findViewById(R.id.txtTelefono);
            edad = itemView.findViewById(R.id.txtEdad);

        }
    }
}