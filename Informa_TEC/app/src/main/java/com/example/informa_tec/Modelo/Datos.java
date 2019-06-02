package com.example.informa_tec.Modelo;

import java.io.Serializable;

public class Datos implements Serializable {

    private String usuario;
    private String semestre;
    private int materia;
    private int maestro;
    private int id_curso;
    private int id_usuario;

    public Datos(){

    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getId_curso() {
        return id_curso;
    }

    public void setId_curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSemestre() {
        return semestre;
    }

    public void setSemestre(String semestre) {
        this.semestre = semestre;
    }

    public int getMateria() {
        return materia;
    }

    public void setMateria(int materia) {
        this.materia = materia;
    }

    public int getMaestro() {
        return maestro;
    }

    public void setMaestro(int maestro) {
        this.maestro = maestro;
    }
}
