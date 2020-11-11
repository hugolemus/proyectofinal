package com.example.school.entidades;

import java.io.Serializable;

public class Seccion implements Serializable {
    private    Integer id_seccion;
    private      String seccion;

    public Seccion(Integer id_seccion, String seccion) {
        this.id_seccion = id_seccion;
        this.seccion = seccion;
    }

    public Seccion() {
    }

    public Integer getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(Integer id_seccion) {
        this.id_seccion = id_seccion;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }
}
