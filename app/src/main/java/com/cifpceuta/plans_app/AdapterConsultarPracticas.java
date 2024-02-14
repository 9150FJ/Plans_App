package com.cifpceuta.plans_app;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.annotation.Nullable;

public class AdapterConsultarPracticas extends RecyclerView.Adapter<AdapterConsultarPracticas.ViewHolder> {


    ArrayList<PlanificarPractica> practicas;


    int orden=0;

    public AdapterConsultarPracticas(){}

    public AdapterConsultarPracticas(ArrayList<PlanificarPractica> practicas){
        this.practicas=practicas;
    }

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
        LinearLayout ly;
        TextView grupo, modulo, titulo, fechaInicio,fechaFin;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            ly = itemView.findViewById(R.id.linearGeneral);


            cardView=itemView.findViewById(R.id.x);
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
            if (restarFecha(fechaInicio.getText().toString(),fechaFin.getText().toString())<2){
                ly.setBackgroundColor(Color.rgb(189, 21, 21));

            } else if (restarFecha(fechaInicio.getText().toString(),fechaFin.getText().toString())<5) {
                ly.setBackgroundColor(Color.rgb(255, 130, 58));
            }

        }
        public int restarFecha(String fecha,String fechaFin){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate fechaInicio = LocalDate.parse(fecha, formatter);
            LocalDate fechaFinal = LocalDate.parse(fechaFin, formatter);
            fechaInicio.getDayOfWeek();
            //Period p = fechaInicio.until(fechaFinal).getDays();
            return fechaInicio.until(fechaFinal).getDays();
        }


    }


}
