package com.cifpceuta.plans_app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanificarTareasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanificarTareasFragment extends Fragment  {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    public PlanificarTareasFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment planificarTareasFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanificarTareasFragment newInstance() {
        PlanificarTareasFragment fragment = new PlanificarTareasFragment();
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

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FloatingActionButton boton;
        RecyclerView recyclerView;
        ArrayList<Tarea> tareas = new ArrayList<>();


        View rootView = inflater.inflate(R.layout.fragment_planificar_tareas, container, false);
        boton=(FloatingActionButton)rootView.findViewById(R.id.botonAgregarTarea);
        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialogo dialog = new Dialogo();
                dialog.show(getChildFragmentManager(), "NoticeDialogFragment");


            }
        });
        recyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerView);

        AdapterTareasPracticas adapter=new AdapterTareasPracticas(tareas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));




        return rootView;
    }
}