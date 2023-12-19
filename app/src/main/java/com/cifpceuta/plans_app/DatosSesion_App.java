package com.cifpceuta.plans_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DatosSesion_App extends AppCompatActivity {


    EditText usuario,contraseña;
    Button botonIniciarSesion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sesion_app);
        usuario=(EditText) findViewById(R.id.editTextUsuario);
        contraseña=(EditText) findViewById(R.id.editTextContraseña);
        String stringUsuario=usuario.getText().toString();
        String stringContraseña=contraseña.getText().toString();
        botonIniciarSesion=(Button) findViewById(R.id.botonIniciarSesion);
        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}