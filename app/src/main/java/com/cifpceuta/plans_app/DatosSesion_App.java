package com.cifpceuta.plans_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class DatosSesion_App extends AppCompatActivity {


    EditText email,contraseña;
    Button botonIniciarSesion;

    private FirebaseAuth mAuth;
// ...
// Initialize Firebase Auth
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_datos_sesion_app);
        email=(EditText) findViewById(R.id.editTextEmail);
        contraseña=(EditText) findViewById(R.id.editTextContraseña);

        botonIniciarSesion=(Button) findViewById(R.id.botonIniciarSesion);
        mAuth = FirebaseAuth.getInstance();


        botonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesion();
            }
        });

    }


    public void iniciarSesion(){

        mAuth.signInWithEmailAndPassword(email.getText().toString(), contraseña.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCustomToken:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            updateUI(SesionIniciada.class);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCustomToken:failure", task.getException());
                            Toast.makeText(DatosSesion_App.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

    }


    public void updateUI(Class a){
        Intent i =  new Intent(this,a);
        FirebaseUser currentUser = mAuth.getCurrentUser();
        i.putExtra("email",currentUser.getEmail());
        //i.putExtra("turno",)
        startActivity(i);
    }

}