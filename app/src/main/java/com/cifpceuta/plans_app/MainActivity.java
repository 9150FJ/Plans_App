package com.cifpceuta.plans_app;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyOpenHelper dbHelper = new MyOpenHelper(this);

        SQLiteDatabase db = dbHelper.getWritableDatabase();


    }
}