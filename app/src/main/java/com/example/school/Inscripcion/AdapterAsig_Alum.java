package com.example.school.Inscripcion;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.school.R;
import com.example.school.entidades.Asig_Alum;


import java.util.ArrayList;

public class AdapterAsig_Alum extends RecyclerView.Adapter<AdapterAsig_Alum.ViewHolderDatos> {

    ArrayList<Asig_Alum> listAsig_Alum;

    public AdapterAsig_Alum(ArrayList<Asig_Alum> listAsig_Alum){
        this.listAsig_Alum= listAsig_Alum;
    }

    @NonNull
    @Override
    public AdapterAsig_Alum.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_asig_alum, null, false);
        return  new AdapterAsig_Alum.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAsig_Alum.ViewHolderDatos holder, int position) {
        holder.id_asig_alum.setText(listAsig_Alum.get(position).getId_asig_alum().toString());
        holder.id_alum.setText(listAsig_Alum.get(position).getId_alumno().toString());
        holder.id_curs.setText(listAsig_Alum.get(position).getId_curso().toString());
        holder.id_grad.setText(listAsig_Alum.get(position).getId_grado().toString());
        holder.id_seccio.setText(listAsig_Alum.get(position).getId_seccin().toString());
    }

    @Override
    public int getItemCount() {
        return listAsig_Alum.size();
    }

        public class ViewHolderDatos extends RecyclerView.ViewHolder{
        TextView id_asig_alum, id_alum, id_curs, id_grad, id_seccio;

        public ViewHolderDatos(@NonNull View itemView){
            super(itemView);
            id_asig_alum = itemView.findViewById(R.id.txtID_Asig_Alum);
            id_alum = itemView.findViewById(R.id.txtID_ALUMNO);
            id_curs = itemView.findViewById(R.id.txtID_CURSO);
            id_grad = itemView.findViewById(R.id.txtGRADO);
            id_seccio = itemView.findViewById(R.id.txtSECCION);

        }
    }
}