package com.example.informa_tec.Modelo;

public class ModeloMaestro {
    private String nombre;
    private String apellidoP;
    private String getApellidoM;

    public ModeloMaestro(String nombre, String apellidoP, String getApellidoM) {
        this.nombre = nombre;
        this.apellidoP = apellidoP;
        this.getApellidoM = getApellidoM;
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
