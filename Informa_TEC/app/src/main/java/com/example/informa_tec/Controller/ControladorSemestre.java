package com.example.informa_tec.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.informa_tec.Adapter.RVSemestreAdapter;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.Modelo.ModeloSemestre;
import com.example.informa_tec.R;
import java.util.ArrayList;
import java.util.List;

public class ControladorSemestre extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RVSemestreAdapter adapter;
    String usr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlador_semestre);
        List<ModeloSemestre> semestres = new ArrayList<>();

        usr=getIntent().getExtras().getString("usuario");
        Datos datosObtenidos =new Datos();
        datosObtenidos.setUsuario(usr);
        datosObtenidos.setId_usuario(getIntent().getExtras().getInt("id_usuario"));
        semestres.add(new ModeloSemestre("1"));
        semestres.add(new ModeloSemestre("2"));
        semestres.add(new ModeloSemestre("3"));
        semestres.add(new ModeloSemestre("4"));
        semestres.add(new ModeloSemestre("5"));
        semestres.add(new ModeloSemestre("6"));
        semestres.add(new ModeloSemestre("7"));
        semestres.add(new ModeloSemestre("8"));
        semestres.add(new ModeloSemestre("9"));
        recyclerView = findViewById(R.id.rv_semestre);

        adapter = new RVSemestreAdapter(this, semestres,datosObtenidos);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }
}