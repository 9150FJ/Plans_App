package com.cifpceuta.plans_app;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.ColorInt;
import androidx.annotation.DrawableRes;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarPracticasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarPracticasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    ArrayList<PlanificarPractica> lista;
    RecyclerView recyclerView;

    AdapterConsultarPracticas adapter;


    // TODO: Rename and change types of parameters

    public ConsultarPracticasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ConsultarPracticasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarPracticasFragment newInstance(ArrayList<PlanificarPractica> listas) {
        ConsultarPracticasFragment fragment = new ConsultarPracticasFragment();
        Bundle args = new Bundle();
        args.putSerializable("lista", listas);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("ARGUMENTOS TAMAÑO: "+getArguments().size());
        if (getArguments() != null) {
            lista = (ArrayList<PlanificarPractica>) getArguments().getSerializable("lista");
        }


    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_consultar_practicas, container, false);

        recyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerView);
        //ly.setBackgroundColor(Color.rgb(255, 130, 58));

        //System.out.println(lista.size());
        //System.out.println(lista.get(1).toString());
        adapter=new AdapterConsultarPracticas(lista);
        // Obtener el correo electrónico de los argumentos
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));



        if (getArguments() != null) {

        }


        return rootView;
    }
    public int restarFecha(String fecha,String fechaFin){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicio = LocalDate.parse(fecha, formatter);
        LocalDate fechaFinal = LocalDate.parse(fechaFin, formatter);



        return fechaInicio.until(fechaFinal).getDays();
    }
}