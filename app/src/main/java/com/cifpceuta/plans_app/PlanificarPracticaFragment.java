package com.cifpceuta.plans_app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PlanificarPracticaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanificarPracticaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private Button guardar;
    private TextView tituloPractica,fechaInicio,fechaFin,tvDescripcion;

    private FirebaseAuth mAuth;

    private FirebaseFirestore db;
    ArrayAdapter<String> adapterGrupo,adapterModulo;
    String[] grupo,modulo;
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
        mAuth = FirebaseAuth.getInstance();
        db=FirebaseFirestore.getInstance();



        if (getArguments() != null) {
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Spinner spinnerModulo,spinnerGrupo;
        View rootView = inflater.inflate(R.layout.fragment_planificar_practica, container, false);



        spinnerModulo=rootView.findViewById(R.id.spinnerModuloFragmentPlanificarPractica);
        spinnerGrupo=rootView.findViewById(R.id.spinnerGrupoFragmentPlanificarPractica);
        grupo = new String[]{"1º DAM", "2º DAM"};
        modulo = new String[]{"SI", "BD", "PROG", "LMSGI", "ED", "FOL"};



        //ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item,grupo);
        adapterGrupo = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_dropdown_item,grupo);
        spinnerGrupo.setAdapter(adapterGrupo);

        adapterModulo = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_dropdown_item,modulo);
        spinnerModulo.setAdapter(adapterModulo);


        spinnerGrupo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (spinnerGrupo.getSelectedItem().toString().equalsIgnoreCase("2º DAM")){
                    modulo = new String[]{"AD", "DI", "PMDM", "PSP","SGE","EIE"};
                    adapterModulo = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_dropdown_item,modulo);
                    spinnerModulo.setAdapter(adapterModulo);
                }else{
                    modulo = new String[]{"SI", "BD", "PROG", "LMSGI", "ED", "FOL"};
                    adapterModulo = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_dropdown_item,modulo);
                    spinnerModulo.setAdapter(adapterModulo);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

        guardar=rootView.findViewById(R.id.botonGuardar_planificar_practicaFragment);

        tituloPractica=rootView.findViewById(R.id.tvTituloPracticaFragmentPlanificarPractica);
        fechaInicio=rootView.findViewById(R.id.tvFechaInicioFragmentPlanificarPractica);
        fechaFin=rootView.findViewById(R.id.tvFechaFinalFragmentPlanificarPractica);
        tvDescripcion=rootView.findViewById(R.id.tvDescripcionFragmentPlanificarPractica);

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (comprobarDatos()){
                    PlanificarPractica p = new PlanificarPractica();
                    p.setTitulo(tituloPractica.getText().toString());
                    p.setGrupo(spinnerGrupo.getSelectedItem().toString());
                    p.setModulo(spinnerModulo.getSelectedItem().toString());
                    p.setFechaInicio(fechaInicio.getText().toString());
                    p.setFechaFinal(fechaFin.getText().toString());
                    p.setDescripcion(tvDescripcion.getText().toString());
                    //registrarPracticaTabla(); //añadiruser Id y planificarPractica



                }
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }


    public void registrarPracticaTabla(String userID, PlanificarPractica p){
        Map<String, Object> lista = new HashMap<>();
        lista.put("titulo",p.getTitulo());
        lista.put("fechaInicio",p.getFechaInicio());
        lista.put("fechaFin",p.getFechaFinal());
        lista.put("grupo",p.getGrupo());
        lista.put("modulo",p.getModulo());
        lista.put("descripcion",p.getDescripcion());

        db.collection("Practica").document(userID).set(lista).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("Registro practica:","Aceptado");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Registro practica","Fallado",e);
            }
        });


    }


    public boolean comprobarDatos(){
        if (tituloPractica.getText().toString().isEmpty() || fechaInicio.getText().toString().isEmpty() || fechaFin.getText().toString().isEmpty()){
            return false;
        }
        return true;
    }


}