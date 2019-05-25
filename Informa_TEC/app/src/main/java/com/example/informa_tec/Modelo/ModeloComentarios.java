package com.example.informa_tec.Modelo;

public class ModeloComentarios {
    private String fecha;
    private String comentario;

    public ModeloComentarios(String fecha, String comentario) {
        this.fecha = fecha;
        this.comentario = comentario;
    }

    public String getFecha() {
        return fecha;
    }

    public String getComentario() {
        return comentario;
    }
}
