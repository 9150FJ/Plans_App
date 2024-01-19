package com.cifpceuta.plans_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanificarPracticaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanificarPracticaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    public PlanificarPracticaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment PlanificarPracticaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanificarPracticaFragment newInstance() {
        PlanificarPracticaFragment fragment = new PlanificarPracticaFragment();
        Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Spinner spinnerModulo,spinnerGrupo;
        View rootView = inflater.inflate(R.layout.fragment_planificar_practica, container, false);



        spinnerModulo=rootView.findViewById(R.id.spinnerModuloFragmentPlanificarPractica);
        spinnerGrupo=rootView.findViewById(R.id.spinnerGrupoFragmentPlanificarPractica);
        String[] grupo = {"1º DAM","2º DAM"};
        String[] modulo = {"PMDM","PSP","EIE","SGE"};
        //ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,grupo);
        ArrayAdapter<String> adapterGrupo = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_dropdown_item,grupo);
        spinnerGrupo.setAdapter(adapterGrupo);

        ArrayAdapter<String> adapterModulo = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_dropdown_item,modulo);
        spinnerModulo.setAdapter(adapterModulo);

        // Inflate the layout for this fragment
        return rootView;
    }
}