package com.cifpceuta.plans_app;

import java.io.Serializable;

public class Estudiante implements Serializable {

    private String nombre;
    private String email;
    private String grupo;
    private String turno;
    public Estudiante(){}

    public Estudiante(String nombre, String email, String grupo, String turno) {
        this.nombre = nombre;
        this.email = email;
        this.grupo = grupo;
        this.turno = turno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
