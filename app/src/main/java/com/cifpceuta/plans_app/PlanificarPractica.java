package com.cifpceuta.plans_app;

public class PlanificarPractica {

    private String titulo;
    private String fechaInicio;
    private String fechaFinal;
    private String grupo;
    private String modulo;
    private String descripcion;

    public PlanificarPractica() {
    }



    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "PlanificarPractica{" +
                "titulo='" + titulo + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", fechaFinal='" + fechaFinal + '\'' +
                ", grupo='" + grupo + '\'' +
                ", modulo='" + modulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
