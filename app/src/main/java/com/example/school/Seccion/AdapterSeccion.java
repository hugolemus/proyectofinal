package com.example.school.Seccion;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.view.View;
import com.example.school.R;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

import android.widget.TextView;

import com.example.school.entidades.Seccion;

public class AdapterSeccion extends RecyclerView.Adapter<AdapterSeccion.ViewHolderDatos> {

    ArrayList<Seccion> listSeccion;

    public AdapterSeccion(ArrayList<Seccion> listSeccion) { this.listSeccion = listSeccion;}

    @NonNull
    @Override
    public AdapterSeccion.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_seccion, null, false);
           return new AdapterSeccion.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterSeccion.ViewHolderDatos holder, int position) {
        holder.Id_Seccion.setText(listSeccion.get(position).getId_seccion().toString());
        holder.Seccion.setText(listSeccion.get(position).getSeccion().toString());
    }

    @Override
    public int getItemCount() {
        return listSeccion.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView Id_Seccion, Seccion;

        public ViewHolderDatos(@NonNull View itemView){
            super(itemView);
            Id_Seccion = itemView.findViewById(R.id.txtID_Seccion);
            Seccion = itemView.findViewById(R.id.txtSeccion);
        }
    }




}