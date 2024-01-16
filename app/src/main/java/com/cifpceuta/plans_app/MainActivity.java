package com.cifpceuta.plans_app;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button botonRegistrar,botonIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MyOpenHelper dbHelper = new MyOpenHelper(this);
        //SQLiteDatabase db = dbHelper.getWritableDatabase();
        botonRegistrar=(Button) findViewById(R.id.registrar);
        botonIniciarSesion=(Button) findViewById(R.id.iniciarSesion);
        botonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentRegistrar();
            }
        });

        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentIniciarSesion();
            }
        });



    }

    public void intentRegistrar(){
        Intent i = new Intent(this,DatosRegistrarse_App.class);
        startActivity(i);
    }

    public void intentIniciarSesion(){
        Intent i = new Intent(this,DatosSesion_App.class);
        startActivity(i);
    }
}