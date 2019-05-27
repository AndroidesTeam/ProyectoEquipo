package com.example.informa_tec.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.informa_tec.Adapter.RVSemestreAdapter;
import com.example.informa_tec.Modelo.ModeloSemestre;
import com.example.informa_tec.R;
import java.util.ArrayList;
import java.util.List;

public class ControladorSemestre extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RVSemestreAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlador_semestre);

        List<ModeloSemestre> semestres = new ArrayList<>();

        semestres.add(new ModeloSemestre("1ro"));
        semestres.add(new ModeloSemestre("2do"));
        semestres.add(new ModeloSemestre("3ro"));
        semestres.add(new ModeloSemestre("4to"));
        semestres.add(new ModeloSemestre("5to"));
        semestres.add(new ModeloSemestre("6to"));
        semestres.add(new ModeloSemestre("7mo"));
        semestres.add(new ModeloSemestre("8vo"));
        semestres.add(new ModeloSemestre("9no"));
        recyclerView = findViewById(R.id.rv_semestre);

        adapter = new RVSemestreAdapter(this, semestres);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}