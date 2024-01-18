package com.cifpceuta.plans_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class SesionIniciada extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    FirebaseAuth mAuth;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;

    String email;

    String nombre,email2,grupo,turno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sesion_iniciada);
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        mAuth=FirebaseAuth.getInstance();
        String userID = mAuth.getCurrentUser().getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        DocumentReference docRef = db.collection("Estudiantes").document(userID);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()){
                         nombre = (String)document.get("nombre");
                         email2 = (String)document.get("email");
                         grupo = (String) document.get("grupo");
                         turno = (String) document.get("turno");
                    }else{
                        Log.d("Error", "No such document");

                    }

                }else{
                    Log.d("Error", "get failed with ", task.getException());

                }
            }
        });




        //textViewEmail=findViewById(R.id.);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        Intent i = this.getIntent();
        if (i!=null){
            email=i.getStringExtra("email");
            //textViewEmail.setText(email);
        }



        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);



        DefaultFragment defaultFragment = DefaultFragment.newInstance("Bienvenido "+email);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragPerfilEst, defaultFragment)
                    .commit();
        }

    }




    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.nav_account) {
            // Acción para la opción 1
            // Puedes abrir un nuevo fragmento, iniciar una nueva actividad, etc.
            MiCuentaFragment miCuentaFragment = MiCuentaFragment.newInstance(nombre,email2,turno,grupo);


            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragPerfilEst, miCuentaFragment)
                    .commit();
        }
        else if (itemId == R.id.nav_logout) {
            // Acción para la opción 2
            // Puedes realizar una acción diferente aquí
            // Por ejemplo, iniciar una nueva actividad
            Toast.makeText(this, "Boton", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
        }
        else if (itemId == R.id.plan_practica){

        }
        else if (itemId == R.id.plan_exam){

        }
        else if (itemId == R.id.nav_settings){

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
