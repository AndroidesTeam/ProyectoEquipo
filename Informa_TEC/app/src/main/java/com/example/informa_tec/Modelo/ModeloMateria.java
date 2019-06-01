package com.example.informa_tec.Modelo;

public class ModeloMateria {
    private int id_materia;
  private String nombreMateria;

    public ModeloMateria(int id_materia,String nombreMateria) {
        this.id_materia=id_materia;
        this.nombreMateria = nombreMateria;
    }

    public int getId_materia() {
        return id_materia;
    }

    public void setId_materia(int id_materia) {
        this.id_materia = id_materia;
    }

    public String getNombreMateria() {
        return nombreMateria;
    }

    public void setNombreMateria(String nombreMateria) {
        this.nombreMateria = nombreMateria;
    }
}
