package com.cifpceuta.plans_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

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
    public static ConsultarPracticasFragment newInstance(ArrayList<PlanificarPractica> lista) {
        ConsultarPracticasFragment fragment = new ConsultarPracticasFragment();
        Bundle args = new Bundle();
        args.putSerializable("lista", lista);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lista = (ArrayList<PlanificarPractica>) getArguments().getSerializable("lista");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_consultar_practicas, container, false);
        recyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerView);
        System.out.println(lista.size());
        //System.out.println(lista.get(1).toString());
        adapter=new AdapterConsultarPracticas(lista);
        // Obtener el correo electrónico de los argumentos
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        if (getArguments() != null) {

        }

        // Vincular el correo electrónico al TextView en el layout
        /*tvNombre = rootView.findViewById(R.id.tvNombreMiCuenta);
        tvCorreo = rootView.findViewById(R.id.tvEmailMiCuenta);
        tvTurno = rootView.findViewById(R.id.tvTurnoMiCuenta);
        tvGrupo = rootView.findViewById(R.id.tvGrupoMiCuenta);

        tvNombre.setText(nombre);
        tvCorreo.setText(email);
        tvTurno.setText(turno);
        tvGrupo.setText(grupo);*/

        // Inflate the layout for this fragment
        return rootView;
    }
}