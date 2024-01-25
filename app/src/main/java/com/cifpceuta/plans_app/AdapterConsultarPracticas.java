package com.cifpceuta.plans_app;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import javax.annotation.Nullable;

public class AdapterConsultarPracticas extends RecyclerView.Adapter<AdapterConsultarPracticas.ViewHolder> {


    ArrayList<PlanificarPractica> practicas;


    int orden=0;

    public AdapterConsultarPracticas(){}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.consultar_tareas,parent,false);
        return new AdapterConsultarPracticas.ViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindData(practicas.get(position));
    }

    @Override
    public int getItemCount() {
        return practicas.size();
    }

    public AdapterConsultarPracticas(ArrayList<PlanificarPractica> practicas){
        this.practicas=practicas;
    }



    /*public View getView(int position, @Nullable View converView, @NonNull ViewGroup parent){
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.consultar_tareas,parent,false);
        PlanificarPractica p = practicas.get(position);
        TextView grupo,modulo,titulo,fechaInicio,fechaFin;

        grupo=(TextView) rowView.findViewById(R.id.consultarTareasFragmentGrupo);
        modulo=(TextView) rowView.findViewById(R.id.consultarTareasFragmentModulo);
        titulo=(TextView) rowView.findViewById(R.id.consultarTareasFragmentTitulo);
        fechaInicio=(TextView) rowView.findViewById(R.id.consultarTareasFragmentFechaInicio);
        fechaFin=(TextView) rowView.findViewById(R.id.consultarTareasFragmentFechaFin);


        grupo.setText(p.getGrupo());
        modulo.setText(p.getModulo());
        titulo.setText(p.getTitulo());
        fechaInicio.setText(p.getFechaInicio());
        fechaFin.setText(p.getFechaFinal());



        return rowView;    }*/

    public class ViewHolder extends RecyclerView.ViewHolder{
        CardView cardView;
        TextView grupo, modulo, titulo, fechaInicio,fechaFin;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            cardView=itemView.findViewById(R.id.cardViewGeneral);
            grupo=(TextView) itemView.findViewById(R.id.consultarTareasFragmentGrupo);
            modulo=(TextView) itemView.findViewById(R.id.consultarTareasFragmentModulo);
            titulo=(TextView) itemView.findViewById(R.id.consultarTareasFragmentTitulo);
            fechaInicio=(TextView) itemView.findViewById(R.id.consultarTareasFragmentFechaInicio);
            fechaFin=(TextView) itemView.findViewById(R.id.consultarTareasFragmentFechaFin);

        }
        void bindData(PlanificarPractica p){
            grupo.setText(p.getGrupo());
            modulo.setText(p.getModulo());
            titulo.setText(p.getTitulo());
            fechaInicio.setText(p.getFechaInicio());
            fechaFin.setText(p.getFechaFinal());
        }
    }


}
