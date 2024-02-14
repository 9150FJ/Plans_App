package com.cifpceuta.plans_app;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class AdapterTareasPracticas extends RecyclerView.Adapter<AdapterTareasPracticas.ViewHolder> {



    ArrayList<Tarea> tareas;


    int orden=0;


    public AdapterTareasPracticas(ArrayList<Tarea> tareas){
        this.tareas=tareas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ver_tareas,parent,false);
        return new AdapterTareasPracticas.ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(tareas.get(position));

    }


    @Override
    public int getItemCount() {
        return tareas.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;

        LinearLayout ly;
        TextView titulo, fechaInicio, curso;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            //ly = itemView.findViewById(R.id.linearGeneral);
            cardView=itemView.findViewById(R.id.cardViewGeneralExamen);

            titulo=itemView.findViewById(R.id.tareafragmentTitulo);
            curso=itemView.findViewById(R.id.tareafragmentCurso);

            fechaInicio=itemView.findViewById(R.id.tareafragmentfecha);


        }
        void bindData(Tarea p){
            System.out.println(p.getTitulo());
            System.out.println(p.getCurso());
            System.out.println(p.getFechaInicio());
            if (fechaInicio != null) {
                fechaInicio.setText(p.getFechaInicio());
            }

            if (curso != null) {
                curso.setText(p.getCurso());
            }

            if (titulo != null) {
                titulo.setText(p.getTitulo());
            }

        }

    }


}
