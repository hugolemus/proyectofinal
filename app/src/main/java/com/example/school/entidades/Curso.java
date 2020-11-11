package com.example.school.entidades;

import android.content.Intent;

import java.io.Serializable;

public class Curso implements Serializable {
    private Integer id_curso;
    private String curso;

    public Curso(Integer id_curso, String curso) {
        this.id_curso = id_curso;
        this.curso = curso;
    }

    public Curso() {
    }

    public Integer getId_curso() {
        return id_curso;
    }

    public void setId_curso(Integer id_curso) {
        this.id_curso = id_curso;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
