package com.example.school.entidades;

import java.io.Serializable;
import java.util.Date;

public class Asig_Alum implements Serializable {

    private Integer id_asig_alum;
    private Date fecha;
    private Integer id_alumno;
    private Integer id_curso;
    private Integer id_grado;
    private Integer id_seccin;

    public Asig_Alum(Integer id_asig_alum, Date fecha, Integer id_alumno, Integer id_curso, Integer id_grado, Integer id_seccin) {
        this.id_asig_alum = id_asig_alum;
        this.fecha = fecha;
        this.id_alumno = id_alumno;
        this.id_curso = id_curso;
        this.id_grado = id_grado;
        this.id_seccin = id_seccin;
    }

    public Asig_Alum() {
    }

    public Integer getId_asig_alum() {
        return id_asig_alum;
    }

    public void setId_asig_alum(Integer id_asig_alum) {
        this.id_asig_alum = id_asig_alum;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Integer getId_alumno() {
        return id_alumno;
    }

    public void setId_alumno(Integer id_alumno) {
        this.id_alumno = id_alumno;
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public Integer getId_grado() {
        return id_grado;
    }

    public void setId_grado(Integer id_grado) {
        this.id_grado = id_grado;
    }

    public Integer getId_seccin() {
        return id_seccin;
    }

    public void setId_seccin(Integer id_seccin) {
        this.id_seccin = id_seccin;
    }
}
