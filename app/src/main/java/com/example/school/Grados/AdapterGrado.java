package com.example.school.Grados;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.school.entidades.Grado;
import com.example.school.R;

import java.util.ArrayList;

public class AdapterGrado extends RecyclerView.Adapter<AdapterGrado.ViewHolderDatos> {

        ArrayList<Grado> listGrado;

        public AdapterGrado(ArrayList<Grado> listGrado){this.listGrado = listGrado;}



    @NonNull
    @Override
    public AdapterGrado.ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_adapter_grado, null, false);
        return new AdapterGrado.ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGrado.ViewHolderDatos holder, int position) {
        holder.Id_Grado.setText(listGrado.get(position).getId_grado().toString());
        holder.Grado.setText(listGrado.get(position).getGrado().toString());
    }

    @Override
    public int getItemCount() {
        return listGrado.size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder{
            TextView Id_Grado, Grado;

            public ViewHolderDatos(@NonNull View itemView){
                super(itemView);
                Id_Grado = itemView.findViewById(R.id.txtID_Grado);
                Grado = itemView.findViewById(R.id.txtGrado);
            }
    }
}
