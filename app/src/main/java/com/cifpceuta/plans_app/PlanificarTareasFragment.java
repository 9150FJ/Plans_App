package com.cifpceuta.plans_app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanificarTareasFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanificarTareasFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    //private static final String ARG_PARAM1 = "param1";
    //private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    //private String mParam1;
    //private String mParam2;

    FirebaseFirestore db;
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    ArrayList<Tarea> tareas;

    AdapterTareasPracticas adapter;



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
        FloatingActionButton boton;
        tareas = new ArrayList<>();

        View rootView = inflater.inflate(R.layout.fragment_planificar_tareas, container, false);
        boton = rootView.findViewById(R.id.botonAgregarTarea);
        recyclerView = rootView.findViewById(R.id.recyclerViewTareasExamen);
        adapter = new AdapterTareasPracticas(tareas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialogo();

            }
        });

        return rootView;

    }




    public void dialogo(){
        Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dialogo);
        dialog.getWindow().setLayout(1000,1000);
        Button botonAñadir = dialog.findViewById(R.id.botonAñadir);
        EditText titulo, fechaInicio;
        Spinner spinner;
        titulo = dialog.findViewById(R.id.tituloDialogo);
        fechaInicio = dialog.findViewById(R.id.fechaInicioDialogo);
        spinner = dialog.findViewById(R.id.spinnerDialogo);


        String[] cursos = {"1DAM", "2DAM"};
        ArrayAdapter<String> adapterCurso = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, cursos);
        spinner.setAdapter(adapterCurso);

        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth = FirebaseAuth.getInstance();
                db = FirebaseFirestore.getInstance();
                Map<String, Object> p = new HashMap<>();
                p.put("titulo", titulo.getText().toString());
                p.put("fecha_ini", fechaInicio.getText().toString());
                p.put("curso", spinner.getSelectedItem().toString());
                tareas.add(new Tarea(titulo.getText().toString(), fechaInicio.getText().toString(), spinner.getSelectedItem().toString()));

                db.collection("actExtra").add(p).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getContext(), "Se ha registrado..", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                        adapter.notifyDataSetChanged();
                    }

                });

            }
        });
        dialog.show();



    }








}


