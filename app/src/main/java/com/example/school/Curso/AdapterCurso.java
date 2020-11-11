package com.example.school.Curso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.school.entidades.Curso;
import com.example.school.R;

import java.util.ArrayList;


public class AdapterCurso extends RecyclerView.Adapter<AdapterCurso.ViewHolderDatos> {

    ArrayList<Curso> listCurso;

    public AdapterCurso(ArrayList<Curso> listCurso) { this.listCurso = listCurso; }


    @NonNull
    @Override
    public AdapterCurso.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_curso, null, false);
        return new AdapterCurso.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterCurso.ViewHolderDatos holder, int position) {
        holder.Id_Curso.setText(listCurso.get(position).getId_curso().toString());
        holder.Curso.setText(listCurso.get(position).getCurso().toString());
    }

    @Override
    public int getItemCount() {
        return listCurso.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {
        TextView Id_Curso, Curso;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            Id_Curso = itemView.findViewById(R.id.txtID_Curso);
            Curso = itemView.findViewById(R.id.txtCurso);
        }
    }

}