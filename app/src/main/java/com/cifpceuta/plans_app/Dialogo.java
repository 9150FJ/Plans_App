package com.cifpceuta.plans_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Dialogo extends DialogFragment {




        //Pasar argumentos

    FirebaseAuth mAuth;
    FirebaseFirestore db;
        public static Dialogo newInstance() {
            Dialogo dialogo = new Dialogo();
            Bundle args = new Bundle();
            //args.putSerializable("estudiante", estudiante);

            dialogo.setArguments(args);
            return dialogo;
        }





        @NonNull
        @Override
        public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {



            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            LayoutInflater inflater = requireActivity().getLayoutInflater();

            builder.setView(inflater.inflate(R.layout.dialogo, null));



            return builder.create();
        }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstances) {
        View view = inflater.inflate(R.layout.dialogo, container);
        Button botonAñadir=view.findViewById(R.id.botonAñadir);
        EditText titulo,fechaInicio;
        Spinner spinner;
        titulo=view.findViewById(R.id.tituloDialogo);
        fechaInicio=view.findViewById(R.id.fechaInicioDialogo);
        spinner = view.findViewById(R.id.spinnerDialogo);
        String[] cursos = {"1DAM","2DAM"};
        ArrayAdapter<String> adapterCurso = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item,cursos);
        spinner.setAdapter(adapterCurso);

        botonAñadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth=FirebaseAuth.getInstance();
                db=FirebaseFirestore.getInstance();
                Map<String,Object> p = new HashMap<>();
                p.put("titulo",titulo.getText().toString());
                p.put("fecha_ini",fechaInicio.getText().toString());
                p.put("curso",spinner.getSelectedItem().toString());


                db.collection("actExtra").add(p).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        Toast.makeText(getContext(), "Se ha registrado..", Toast.LENGTH_SHORT).show();

                    }
                });





            }
        });
        //spinner= (Spinner) view.findViewById(R.id.spinnerPrueba);
        //cargaSpinner();
        return view;
    }



}
