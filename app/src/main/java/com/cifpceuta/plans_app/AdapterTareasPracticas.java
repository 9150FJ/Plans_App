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


    ArrayList<PlanificarPractica> practicas;

    ArrayList<Tarea> tareas;


    int orden=0;

    public AdapterTareasPracticas(){}

    public AdapterTareasPracticas(ArrayList<Tarea> tareas){
        this.tareas=tareas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.consultar_tareas,parent,false);
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
            cardView=itemView.findViewById(R.id.cardViewGeneralTarea);

            titulo=itemView.findViewById(R.id.tareafragmentTitulo);
            fechaInicio=itemView.findViewById(R.id.tareafragmentfecha);
            curso=itemView.findViewById(R.id.tareafragmentCurso);


        }
        void bindData(Tarea p){
            fechaInicio.setText(p.getFechaInicio());
            curso.setText(p.getCurso());
            titulo.setText(p.getTitulo());

        }

    }


}
