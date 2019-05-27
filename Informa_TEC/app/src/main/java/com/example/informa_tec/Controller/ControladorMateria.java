package com.example.informa_tec.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.informa_tec.Adapter.RVMateriaAdapter;
import com.example.informa_tec.Modelo.ModeloMateria;
import com.example.informa_tec.R;
import java.util.ArrayList;
import java.util.List;

public class ControladorMateria extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RVMateriaAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlador_materia);

        List<ModeloMateria> materias = new ArrayList<>();

        materias.add(new ModeloMateria("App Moviles"));
        materias.add(new ModeloMateria("App empresariales"));
        materias.add(new ModeloMateria("Redes 4"));
        materias.add(new ModeloMateria("Inteligencia Artificial"));
        materias.add(new ModeloMateria("Web avanzada"));
        recyclerView = findViewById(R.id.rv_materia);

        adapter = new RVMateriaAdapter(this, materias);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}
