package com.cifpceuta.plans_app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdapterGestionAlumnos extends RecyclerView.Adapter<AdapterGestionAlumnos.ViewHolder> {

    ArrayList<Estudiante> estudiantes;

    int orden=0;

    public AdapterGestionAlumnos(ArrayList<Estudiante> estudiantes){
        this.estudiantes=estudiantes;
    }

    @NonNull
    @Override
    public AdapterGestionAlumnos.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_estudiantes,parent,false);
        return new AdapterGestionAlumnos.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterGestionAlumnos.ViewHolder holder, int position) {
        holder.bindData(estudiantes.get(position));


    }

    public void setEstudiantes(ArrayList<Estudiante> estudiantes) {
        this.estudiantes = estudiantes;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return estudiantes.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;

        TextView nombre, email,inicial;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //ly = itemView.findViewById(R.id.linearGeneral);
            cardView=itemView.findViewById(R.id.cardEstudiantes);
            nombre=itemView.findViewById(R.id.tvAlumnoCard);
            email=itemView.findViewById(R.id.tvEmailCard);
            inicial=itemView.findViewById(R.id.tvInicialCard);



        }
        void bindData(Estudiante e){
            nombre.setText(e.getNombre());
            email.setText(e.getEmail());
            inicial.setText(String.valueOf(e.getNombre().charAt(0)).toUpperCase());



            this.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(itemView.getContext(), MostrarEstudianteActivity.class);
                    intent.putExtra("estudiante",e);
                    itemView.getContext().startActivity(intent);
                }
            });
        }
    }
}
