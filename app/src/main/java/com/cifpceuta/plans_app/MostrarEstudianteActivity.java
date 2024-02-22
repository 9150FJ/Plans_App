package com.cifpceuta.plans_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MostrarEstudianteActivity extends AppCompatActivity {

    Estudiante estudiante;
    TextView nombre,email,turno,grupo;
    Button volver;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_estudiante);
        estudiante= (Estudiante) this.getIntent().getSerializableExtra("estudiante");

        nombre=findViewById(R.id.nombreEstudianteActivity);
        email=findViewById(R.id.emailEstudianteActivity);
        turno=findViewById(R.id.turnoEstudianteActivity);
        grupo=findViewById(R.id.grupoEstudianteActivity);
        volver=findViewById(R.id.botonVolver);

        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MostrarEstudianteActivity.this,SesionIniciada.class);
                boolean flag = true;
                intent.putExtra("flag",flag);
                startActivity(intent);

            }
        });

        nombre.setText(estudiante.getNombre());
        email.setText(estudiante.getEmail());
        turno.setText(estudiante.getTurno());
        grupo.setText(estudiante.getGrupo());
    }
}