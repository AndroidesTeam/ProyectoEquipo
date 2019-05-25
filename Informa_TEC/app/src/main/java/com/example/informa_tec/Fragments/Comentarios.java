package com.example.informa_tec.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.informa_tec.Adapter.RVComentariosAdapter;
import com.example.informa_tec.Modelo.ModeloComentarios;
import com.example.informa_tec.R;

import java.util.ArrayList;
import java.util.List;

public class Comentarios extends Fragment {

    private RecyclerView recyclerView;
    private RVComentariosAdapter adapter;
    List<ModeloComentarios> comentarios;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_comentarios, container, false);
        recyclerView = vista.findViewById(R.id.rv_comentarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        llenarLista();
        adapter = new RVComentariosAdapter(getContext(), comentarios);
        recyclerView.setAdapter(adapter);
        return vista;
    }

    private void llenarLista() {
        comentarios = new ArrayList<>();
        comentarios.add(new ModeloComentarios("25/05/2019", "El profesor es muy bueno"));
        comentarios.add(new ModeloComentarios("22/05/2019", "Excelente profesor"));
        comentarios.add(new ModeloComentarios("10/05/2019", "Sin ofender a mi no me parece bueno, ya que es demasiado flexible con los alumnos"));
        comentarios.add(new ModeloComentarios("29/04/2019", "Bastante flexible, aunque en ocasiones los alumnos abusan de la misma"));
        comentarios.add(new ModeloComentarios("25/04/2019", "Muy buen maestro"));
        comentarios.add(new ModeloComentarios("13/04/2019", "Excelente profesor"));
    }
}