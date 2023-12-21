package com.cifpceuta.plans_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DatosRegistrarse_App extends AppCompatActivity {

    EditText usuario,contraseña;
    Button botonRegistrarse;

    MyOpenHelper dbHelper;
    SQLiteDatabase db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_registrarse_app);
        usuario=(EditText) findViewById(R.id.editTextUsuarioRegistrarse);
        contraseña=(EditText) findViewById(R.id.editTextContraseñaRegistrarse);
        String stringUsuario=usuario.getText().toString();
        String stringContraseña=contraseña.getText().toString();
        botonRegistrarse=(Button) findViewById(R.id.botonRegistrarse);
        dbHelper = new MyOpenHelper(this);
        db = dbHelper.getWritableDatabase();
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                //values.put(usuario, title);
                //values.put(FeedEntry.COLUMN_NAME_SUBTITLE, subtitle);
            }
        });





    }
}