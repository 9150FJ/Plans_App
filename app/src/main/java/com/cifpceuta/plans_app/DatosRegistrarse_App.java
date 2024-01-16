package com.cifpceuta.plans_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class DatosRegistrarse_App extends AppCompatActivity {

    EditText usuario,email ,contraseña;
    Button botonRegistrarse;
    private FirebaseAuth mAuth;

    private FirebaseFirestore db;
    private Spinner spn;

    RadioButton mañana,tarde;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_registrarse_app);

        usuario= (EditText)findViewById(R.id.etNombre);
        email = (EditText) findViewById(R.id.etEmail);
        contraseña = (EditText) findViewById(R.id.editTextContraseñaRegistrarse);
        mañana=(RadioButton)findViewById(R.id.rbMañana);
        tarde=(RadioButton)findViewById(R.id.rbTarde);
        mañana.setChecked(true);
        botonRegistrarse = (Button) findViewById(R.id.botonRegistrarse);
        spn=(Spinner)findViewById(R.id.spinner);
        String[] cursos = {"1º DAM","2º DAM"};
        ArrayAdapter<String> adapterCurso = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,cursos);
        spn.setAdapter(adapterCurso);
        db=FirebaseFirestore.getInstance();
        botonRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Usuario:",usuario.getText().toString());
                Log.d("Contraseña:",contraseña.getText().toString());

                registro();

            }
        });


    }


    public void registro() {
        mAuth = FirebaseAuth.getInstance();
        String turno;
        if (mañana.isChecked()){
            turno="Mañana";
        }else {
            turno="Tarde";
        }




        mAuth.createUserWithEmailAndPassword(email.getText().toString(), contraseña.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Registro realizado correctamente!", Toast.LENGTH_LONG).show();
                    //registrarUsuarioTabla(new Estudiante(usuario.getText().toString(),email),);
                    // si el usuario se ha creado volvemos al Activity Principal para que se pueda logear
                    registrarUsuarioTabla(new Estudiante(usuario.getText().toString(),email.getText().toString(),spn.getSelectedItem().toString(),turno),mAuth.getCurrentUser().getUid());
                    Intent intent = new Intent(DatosRegistrarse_App.this, DatosSesion_App.class);
                    startActivity(intent);
                } else {

                    // En este punto algo ha fallado, lo notificaremos
                    Toast.makeText(getApplicationContext(), "El registro ha fallado!!" + " Intente mas tarde...", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


    public void registrarUsuarioTabla(Estudiante estudiante,String userID){
        Map<String, Object> lista = new HashMap<>();
        lista.put("nombre",estudiante.getNombre());
        lista.put("email",estudiante.getEmail());
        lista.put("grupo",estudiante.getGrupo());
        lista.put("turno",estudiante.getTurno());
        db.collection("Estudiantes").document(userID).set(lista).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Log.d("Registro:","Aceptado");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.w("Registro","Fallado",e);
            }
        });


    }
}