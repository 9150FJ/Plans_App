package com.cifpceuta.plans_app;

import android.annotation.SuppressLint;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
                    String userID = mAuth.getCurrentUser().getUid();
                    p.setUserID(userID);
                    registrarPracticaTabla(p); //añadiruser Id y planificarPractica
                }else {
                    Toast.makeText(rootView.getContext(), "Algo ha salido mal...", Toast.LENGTH_SHORT).show();
                }
            }
        });


        // Inflate the layout for this fragment
        return rootView;
    }


    public void registrarPracticaTabla(PlanificarPractica p){
        Map<String, Object> lista = new HashMap<>();
        lista.put("titulo",p.getTitulo());
        lista.put("fechaInicio",p.getFechaInicio());
        lista.put("fechaFin",p.getFechaFinal());
        lista.put("grupo",p.getGrupo());
        lista.put("modulo",p.getModulo());
        lista.put("descripcion",p.getDescripcion());
        lista.put("userid",p.getUserID());

        //db.collection("cities")
        //        .add(data)
        //         .addOnSuccessListener(new OnSuccessListener<DocumentReference>()
        if (p.getGrupo().equalsIgnoreCase("1º DAM")){

            db.collection("1DAM").add(lista).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    //Intent intent = new Intent(DatosRegistrarse_App.this, DatosSesion_App.class);
                    //Intent i = new Intent(PlanificarPracticaFragment.this,MiCuentaFragment.class);
                    //startActivity(i);
                    vaciarCampos();
                    Toast.makeText(getContext(), "Se ha registrado correctamente la práctica", Toast.LENGTH_SHORT).show();
                    Log.d("Registro practica: 1dam","Aceptado");

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("Registro practica 1dam","Fallado",e);
                }
            });;


        }else if (p.getGrupo().equalsIgnoreCase("2º DAM")){
            db.collection("2DAM").add(lista).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("Registro practica 2dam:","Aceptado");
                    Toast.makeText(getContext(), "Se ha registrado correctamente la práctica", Toast.LENGTH_SHORT).show();
                    vaciarCampos();

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.w("Registro practica 2dam","Fallado",e);
                }
            });;
        }



    }





    public boolean comprobarDatos(){
        if (tituloPractica.getText().toString().isEmpty() || fechaInicio.getText().toString().isEmpty() || fechaFin.getText().toString().isEmpty()){
            return false;
        }
        if (comprobarFecha(fechaInicio.getText().toString(),fechaFin.getText().toString())){
            return true;
        }
        return false;
    }





    public boolean comprobarFecha(String fecha, String fechaFin){
        System.out.println(fecha.length());
        if (fecha.length()==10){
            char[] c = fecha.toCharArray();
            if  (c[5]=='/'&&c[2]=='/'&&Character.isDigit(c[0])&&Character.isDigit(c[1])&&Character.isDigit(c[4])&&Character.isDigit(c[6])&&Character.isDigit(c[7])&&Character.isDigit(c[8])&&Character.isDigit(c[9])){
                String[] n=fecha.split("/");
                int par1=Integer.parseInt(n[0]);
                int par2=Integer.parseInt(n[1]);
                int par3=Integer.parseInt(n[2]);
                if (par1<0||par1>31){
                    return false;
                }
                if (par2<0||par2>12){
                    return false;
                }
                if (par3<LocalDateTime.now().getYear()){
                    return false;
                }
            }
        }
        if (fechaFin.length()==10){
            char[] d = fechaFin.toCharArray();
            if  (d[5]=='/'&&d[2]=='/'&&Character.isDigit(d[0])&&Character.isDigit(d[1])&&Character.isDigit(d[4])&&Character.isDigit(d[6])&&Character.isDigit(d[7])&&Character.isDigit(d[8])&&Character.isDigit(d[9])){
                String[] z=fechaFin.split("/");
                int par1=Integer.parseInt(z[0]);
                int par2=Integer.parseInt(z[1]);
                int par3=Integer.parseInt(z[2]);
                if (par1<0||par1>31){
                    return false;
                }
                if (par2<0||par2>12){
                    return false;
                }
                if (par3<LocalDateTime.now().getYear()){
                    return false;
                }

            }
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fechaInicio = LocalDate.parse(fecha, formatter);
        LocalDate fechaFinal = LocalDate.parse(fechaFin, formatter);
        if (fechaInicio.isBefore(fechaFinal)) {
            return true;
        }
        return false;
    }



    public void vaciarCampos(){
        tituloPractica.setText("");
        fechaInicio.setText("");
        fechaFin.setText("");
        tvDescripcion.setText("");
    }


}