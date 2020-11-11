package com.example.school.entidades;


import java.io.Serializable;

public class Grado implements Serializable {
     private    Integer id_grado;
    private      String grado;


    public Grado(Integer id_grado, String grado) {
        this.id_grado = id_grado;
        this.grado = grado;
    }

    public Grado() {
    }

    public Integer getId_grado() {
        return id_grado;
    }

    public void setId_grado(Integer id_grado) {
        this.id_grado = id_grado;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }
}

