package com.example.informa_tec.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.informa_tec.Adapter.RVMaestroAdapter;
import com.example.informa_tec.Modelo.ModeloMaestro;
import com.example.informa_tec.R;
import java.util.ArrayList;
import java.util.List;

public class ControladorMaestro extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RVMaestroAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlador_maestro);

    List<ModeloMaestro> maestros = new ArrayList<>();

        maestros.add(new ModeloMaestro("Juan", "Murillo", "Garron"));
        maestros.add(new ModeloMaestro("Jose", "Mendez", "Guzman"));
        maestros.add(new ModeloMaestro("Julian", "Morales", "Gonzalez"));
        maestros.add(new ModeloMaestro("Jaime", "Mendoza", "Gomez"));
        maestros.add(new ModeloMaestro("Josue", "Mares", "Gutierrez"));
    recyclerView = findViewById(R.id.rv_materia);

    adapter = new RVMaestroAdapter(this, maestros);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
}
}
