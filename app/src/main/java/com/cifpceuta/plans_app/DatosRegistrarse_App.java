package com.cifpceuta.plans_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DatosRegistrarse_App extends AppCompatActivity {

    EditText usuario,contraseña;
    Button botonRegistrarse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_registrarse_app);
        usuario=(EditText) findViewById(R.id.editTextUsuarioRegistrarse);
        contraseña=(EditText) findViewById(R.id.editTextContraseñaRegistrarse);


        botonRegistrarse=(Button) findViewById(R.id.botonRegistrarse);
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreUsuario=usuario.getText().toString();
                String contraseñaUsuario=contraseña.getText().toString();
                if(nombreUsuario.length()>0&&contraseñaUsuario.length()>0){
                    MyOpenHelper op = new MyOpenHelper(getBaseContext());
                    ContentValues cv = new ContentValues();
                    cv.put("usuario_nombre",usuario.getText().toString());
                    cv.put("contraseña",contraseña.getText().toString());
                    long id = op.agregarDatos(cv);
                    Log.d("Lista", String.valueOf(id));
                    if (id<=0){
                        Toast.makeText(getBaseContext(),"Ha ocurrido un error",Toast.LENGTH_LONG).show();
                    }else{
                        //Iniciar sesion o volver hacia atrás
                    }

                }
            }

        });





    }
}