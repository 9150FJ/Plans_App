package com.cifpceuta.plans_app;

public class Tarea {
    String titulo;
    String curso;
    String fechaInicio;


    public Tarea(String titulo, String curso, String fechaInicio) {
        this.titulo = titulo;
        this.curso = curso;
        this.fechaInicio = fechaInicio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }


    @Override
    public String toString() {
        return "Tarea{" +
                "titulo='" + titulo + '\'' +
                ", curso='" + curso + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                '}';
    }
}
