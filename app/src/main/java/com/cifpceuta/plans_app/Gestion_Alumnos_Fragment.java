package com.cifpceuta.plans_app;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Gestion_Alumnos_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Gestion_Alumnos_Fragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    FirebaseFirestore db;

    FirebaseAuth mAuth;

    RecyclerView rv;
    Spinner spinner;
    ArrayAdapter<String> adapterGrupo;

    ArrayList<Estudiante> estudiantes1dam,estudiantes2dam;

    AdapterGestionAlumnos adapterGestionAlumnos;

    // TODO: Rename and change types of parameters


    public Gestion_Alumnos_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Gestion_Alumnos_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static Gestion_Alumnos_Fragment newInstance() {
        Gestion_Alumnos_Fragment fragment = new Gestion_Alumnos_Fragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, ///
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_gestion__alumnos_, container, false);
        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        estudiantes1dam=new ArrayList<>();
        estudiantes2dam=new ArrayList<>();
        rv=(RecyclerView) rootView.findViewById(R.id.recyclerviewFragmentGestionAlumnos);
        spinner=rootView.findViewById(R.id.spinnerFragmentGestionAlumnos);
        String[] grupo = new String[]{"1º DAM", "2º DAM"};
        adapterGrupo = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_spinner_dropdown_item,grupo);
        spinner.setAdapter(adapterGrupo);
        db.collection("Estudiantes").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                QuerySnapshot q = task.getResult();
                List<DocumentSnapshot> listas=q.getDocuments();

                for (int i=0;i<listas.size();i++){
                    System.out.println(listas.size());
                    if (listas.get(i).get("grupo").toString().equalsIgnoreCase("1º DAM")){
                        Estudiante e = new Estudiante();
                        e.setNombre(listas.get(i).get("nombre").toString());
                        e.setGrupo(listas.get(i).get("grupo").toString());
                        e.setEmail(listas.get(i).get("email").toString());
                        e.setTurno(listas.get(i).get("turno").toString());
                        System.out.println(e);
                        estudiantes1dam.add(e);
                    }else if (listas.get(i).get("grupo").toString().equalsIgnoreCase("2º DAM")){
                        Estudiante e = new Estudiante();
                        e.setNombre(listas.get(i).get("nombre").toString());
                        e.setGrupo(listas.get(i).get("grupo").toString());
                        e.setEmail(listas.get(i).get("email").toString());
                        e.setTurno(listas.get(i).get("turno").toString());
                        System.out.println(e);
                        estudiantes2dam.add(e);
                    }
                }
                adapterGestionAlumnos = new AdapterGestionAlumnos(estudiantes1dam);
                rv.setAdapter(adapterGestionAlumnos);
                rv.setLayoutManager(new LinearLayoutManager(rootView.getContext()));




                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if (position==0){
                            adapterGestionAlumnos.setEstudiantes(estudiantes1dam);
                        }
                        else if (position==1){
                            adapterGestionAlumnos.setEstudiantes(estudiantes2dam);

                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
            }


        });


        return rootView;
    }






}