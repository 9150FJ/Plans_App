package com.cifpceuta.plans_app;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConsultarPracticasPorSemanas#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConsultarPracticasPorSemanas extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER


    // TODO: Rename and change types of parameters
    ArrayList<PlanificarPractica> listaPracticas=new ArrayList<>();
    RecyclerView recyclerView;

    AdapterConsultarPracticas adapter;
    FirebaseFirestore db;
    FirebaseAuth mAuth;
    String grupo;
    TabLayout tabla;


    public ConsultarPracticasPorSemanas() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment ConsultarPracticasPorSemanas.
     */
    // TODO: Rename and change types and number of parameters
    public static ConsultarPracticasPorSemanas newInstance(String grupo) {
        ConsultarPracticasPorSemanas fragment = new ConsultarPracticasPorSemanas();
        Bundle args = new Bundle();
        args.putString("grupo", grupo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            grupo = getArguments().getString("grupo");
        }


    }




    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_consultar_practicas_por_semanas, container, false);

        db=FirebaseFirestore.getInstance();
        mAuth=FirebaseAuth.getInstance();
        tabla=rootView.findViewById(R.id.tablayout);
        imprimirTareas(rootView, tabla.getSelectedTabPosition());

        tabla.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                System.out.println("Posicion tab position:"+tab.getPosition());
                if (tab.getPosition()==0){
                    listaPracticas.clear();
                    imprimirTareas(rootView,tab.getPosition());
                } else if (tab.getPosition()==1) {
                    listaPracticas.clear();

                    imprimirTareas(rootView,tab.getPosition());

                } else if (tab.getPosition()==2) {
                    listaPracticas.clear();

                    imprimirTareas(rootView,tab.getPosition());

                }else if (tab.getPosition()==3) {
                    listaPracticas.clear();

                    imprimirTareas(rootView,tab.getPosition());

                }else if (tab.getPosition()==4) {
                    listaPracticas.clear();

                    imprimirTareas(rootView,tab.getPosition());

                }


            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                System.out.println("Tabla contador: "+tabla.getSelectedTabPosition());

            }
        });



        return rootView;
    }



    public void imprimirTareas(View rootView,int tab){
        db.collection(grupo).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                QuerySnapshot query = task.getResult();
                List<DocumentSnapshot> lista = query.getDocuments();
                for (int i=0;i<lista.size();i++){

                    System.out.println();
                    PlanificarPractica p = new PlanificarPractica();
                    p.setGrupo(lista.get(i).get("grupo").toString());
                    p.setModulo(lista.get(i).get("modulo").toString());
                    p.setTitulo(lista.get(i).get("titulo").toString());
                    p.setDescripcion(lista.get(i).get("descripcion").toString());
                    p.setUserID(lista.get(i).get("userid").toString());
                    p.setFechaInicio(lista.get(i).get("fechaInicio").toString());
                    p.setFechaFinal(lista.get(i).get("fechaFin").toString());
                    System.out.println("Fecha restada: "+p.restarFecha());
                    if (tab==0){
                        if(p.restarFecha()>=0&&p.restarFecha()<=7){
                            listaPracticas.add(p);
                        }
                    }else if (tab==1){
                        if(p.restarFecha()>=8&&p.restarFecha()<=14){
                            listaPracticas.add(p);
                        }
                    }else if (tab==2){
                        if(p.restarFecha()>=15&&p.restarFecha()<=21){
                            listaPracticas.add(p);
                        }

                    }else if (tab==3){
                        if(p.restarFecha()>=22&&p.restarFecha()<=28){
                            listaPracticas.add(p);
                        }

                    }else if (tab==4){
                        if(p.restarFecha()>=29&&p.restarFecha()<=35){
                            listaPracticas.add(p);
                        }

                    }
                    //System.out.println(listaPracticas.get(i).toString());
                    System.out.println(listaPracticas.size());
                    Log.i("contador",i+" ");
                    //Log.i("Lista",lista.toString());
                    //Log.i("Lista",listaPracticas.get(i).toString());

                }
                System.out.println(listaPracticas.size());
                recyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerViewSemana);
                adapter=new AdapterConsultarPracticas(listaPracticas);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(rootView.getContext()));
            }


        });
    }
}