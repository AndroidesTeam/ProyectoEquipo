package com.example.informa_tec.Modelo;

public class ModeloMaestro {
    private int id_maestro;
    private String nombre;
    private String apellidoP;
    private String getApellidoM;

    public ModeloMaestro(int id_maestro,String nombre, String apellidoP, String getApellidoM) {
        this.id_maestro=id_maestro;
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.getApellidoM = getApellidoM;
    }

    public int getId_maestro() {
        return id_maestro;
    }

    public void setId_maestro(int id_maestro) {
        this.id_maestro = id_maestro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoP() {
        return apellidoP;
    }

    public void setApellidoP(String apellidoP) {
        this.apellidoP = apellidoP;
    }

    public String getGetApellidoM() {
        return getApellidoM;
    }

    public void setGetApellidoM(String getApellidoM) {
        this.getApellidoM = getApellidoM;
    }
}
