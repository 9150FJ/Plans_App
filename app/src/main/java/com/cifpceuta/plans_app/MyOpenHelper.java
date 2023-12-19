package com.cifpceuta.plans_app;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyOpenHelper extends SQLiteOpenHelper {


    private static final String tabla_usuario="CREATE TABLE usuario(id INTEGER PRIMARY KEY AUTOINCREMENT, usuario_nombre VARCHAR(35), contraseña VARCHAR(35))";
    private static final String DB_NAME = "app_planifica.db";

    private static final int DB_VERSION =1;

    public MyOpenHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tabla_usuario);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists usuario");
    }

    public void agregarDatos(SQLiteDatabase db, String consulta){
        db = this.getWritableDatabase();
        if (db!=null){
            db.execSQL(consulta);
        }
    }
}
